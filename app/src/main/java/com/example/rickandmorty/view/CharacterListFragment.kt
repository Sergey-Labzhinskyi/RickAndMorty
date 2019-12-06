package com.example.rickandmorty.view


import android.content.ContentValues.TAG
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
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.model.Characterr
import com.example.rickandmorty.model.CharacterList
import com.example.rickandmorty.model.Info
import com.example.rickandmorty.viewmodel.CharacterListViewModel
import androidx.lifecycle.Observer
import androidx.navigation.findNavController


/**
 * A simple [Fragment] subclass.
 */
class CharacterListFragment : Fragment() {


    //private lateinit var characterAdapter: CharacterAdapter
    private val TAG: String = "CharacterListFragment"
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var callbackOnClickList: CallbackOnClickList
    //private lateinit var view: View
    private lateinit var stringDate: String
    private lateinit var button: Button
    private lateinit var model: CharacterListViewModel



    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager //viewManager
    private lateinit var charactersList: List<Characterr>
    private lateinit var info: Info
  //  private lateinit var charactersList: List<Characterr>







     interface CallbackOnClickList {
        fun onClickedItem(position: Int)
        fun onClickedButtonSort()
    }


    companion object{
      //  fun newInstance(characterList: CharacterList, date: String) : CharacterListFragment{
        fun newInstance() : CharacterListFragment{
          Log.d(TAG, "newInstance")
            val characterListFragment = CharacterListFragment()
            val bundle = Bundle()
            val ARG_PERMISSION = "Characters"
           // val DATE = "Date"
          //  bundle.putSerializable(ARG_PERMISSION, ArrayList(characters))
          //  bundle.putString(DATE, date)
          //  characterListFragment.arguments
            return characterListFragment
        }
    }


    override fun onAttach(@NonNull context: Context) {
        super.onAttach(context)
        try {
            callbackOnClickList = context as CallbackOnClickList
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement Callback")
        }
        createViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      /*  if (arguments != null && arguments!!.containsKey("Characters")) {
            characters = arrayListOf()
         //   characters = arguments.getSerializable("Characters")
           // info.result = arguments!!.getSerializable("Characters")
            stringDate = arguments?.getString("Characters").toString()
        } else{
            throw  IllegalArgumentException("Must be created through newInstance(...)")
        }*/




    }

    private lateinit var adapter: Adapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character_list,
            container, false).apply { tag = TAG}

        recyclerView = view.findViewById<RecyclerView>(R.id.character_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = Adapter()
        recyclerView.adapter = adapter


        return view
    }


    class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>()  {



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
           // this.position = position
            holder.textView?.text = dataSet?.get(position)?.name ?: ""

        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataSet?.size ?: 0




       inner class ViewHolder( view: View) : RecyclerView.ViewHolder(view){

            var textView: TextView? = null
            init {
                textView = view.findViewById(R.id.character_title)
                view.setOnClickListener {
                   f (adapterPosition)
                    val ARG_PERMISSION = "Character"
                    val bundle = Bundle()
                    bundle.putSerializable(ARG_PERMISSION, dataSet?.get(adapterPosition))
                    view.findNavController().navigate(R.id.characterItemFragment, bundle)

                }

            }

      fun f(position: Int){
          Log.d(TAG, position.toString())


      }


        }


    }

    fun createViewModel(){
        Log.d(TAG, "createViewModel")
        model = ViewModelProviders.of(this).get(CharacterListViewModel::class.java)
        // Create the observer which updates the UI.
        val listObserver = Observer<List<Characterr>> { characters ->
            // Update the UI, in this case, a TextView.

            Log.d(TAG, characters.size.toString())
            adapter.dataSet = characters

        }
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.currentList.observe(this, listObserver)

        val dateObserver = Observer<String> {data ->
            //createFragment(characterList, data)
        }
        model.currentTime.observe(this, dateObserver)
        // Log.d(TAG, characterList!!.result.size.toString())

    }
}
