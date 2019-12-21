package com.example.rickandmorty.view


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.App
import com.example.rickandmorty.data.AppModule
import com.example.rickandmorty.model.Characterr
import com.example.rickandmorty.viewmodel.CharacterListViewModel
import com.example.rickandmorty.viewmodel.ViewModelFactory
import javax.inject.Inject


class CharacterListFragment : Fragment() {

    private val TAG: String = "CharacterListFragment"


    private lateinit var recyclerView: RecyclerView


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var model: CharacterListViewModel


    override fun onAttach(@NonNull context: Context) {
        super.onAttach(context)

        App.applicationComponent.inject(this)


    }


    private lateinit var adapter: Adapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_character_list,
            container, false
        ).apply { tag = TAG }

        Log.d(TAG, "onCreateView")
        createViewModel()
        recyclerView = view.findViewById<RecyclerView>(R.id.character_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        // layoutManager = LinearLayoutManager(activity)
        adapter = Adapter()
        recyclerView.adapter = adapter
        //adapter.initScrollListener()

        val button = view.findViewById<Button>(R.id.sort_button)
        button.setOnClickListener { sortList() }


        return view
    }


    inner class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

        var dataSet: List<Characterr>? = null
            set(value) {
                field = value
                notifyDataSetChanged()
            }


        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // create a new view
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_character, parent, false)
            // set the view's size, margins, paddings and layout parameters
            return ViewHolder(view)
        }


        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.textView?.text = dataSet?.get(position)?.name ?: ""
            if (position == dataSet?.size!! - 1 && dataSet?.size!! != 493) {
                moreData()
            }


        }




        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataSet?.size ?: 0


        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            var textView: TextView? = null

            init {
                textView = view.findViewById(R.id.character_title)
                view.setOnClickListener {
                    val ARG_PERMISSION = "Character"
                    val bundle = Bundle()
                    bundle.putSerializable(ARG_PERMISSION, dataSet?.get(adapterPosition))
                    view.findNavController().navigate(R.id.characterItemFragment, bundle)
                }

            }
        }


    }


    val list = mutableListOf<Characterr>()

    private fun createViewModel() {
        Log.d(TAG, "createViewModel")

        //  model = ViewModelProviders.of(this).get(CharacterListViewModel::class.java)

        model = ViewModelProviders.of(this, viewModelFactory)[CharacterListViewModel::class.java]
        //viewModelFactory = ViewModelProviders.of(this, viewModelFactory)[ViewModelFactory::class.java]


        // Create the observer which updates the UI.
        val listObserver = Observer<List<Characterr>> { characters ->
            // Update the UI, in this case, a TextView.

            Log.d(TAG, characters.size.toString())
            // adapter.dataSet = characters
            if (characters != null) {
                list.addAll(characters)
                Log.d(TAG, "listAll ${list.size}")
                adapter.dataSet = list
            } else {
                adapter.dataSet = null
            }


        }
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.currentList.observe(this, listObserver)
        /* model.currentList.observe(this, Observer<List<Characterr>> {
             it?.let { adapter.dataSet = it }
         })*/

        val dateObserver = Observer<String> { data ->
        }
        model.currentTime.observe(this, dateObserver)
        // Log.d(TAG, characterList!!.result.size.toString())

    }

    private fun sortList() {
        Log.d(TAG, "sortList")
        val listSortObserver = Observer<List<Characterr>> { characters ->
            Log.d(TAG, characters.size.toString())
            adapter.dataSet = characters

        }
        model.currentListSort.observe(this, listSortObserver)
    }


    fun moreData() {
        model.moreData()
    }
}
