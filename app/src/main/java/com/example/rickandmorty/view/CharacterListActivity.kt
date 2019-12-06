package com.example.rickandmorty.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.rickandmorty.R
import com.example.rickandmorty.model.CharacterList
import com.example.rickandmorty.model.Characterr
import com.example.rickandmorty.usecase.Repository

class CharacterListActivity : AppCompatActivity(), CharacterListFragment.CallbackOnClickList {

    private val TAG: String = "CharacterListActivity"
    private var stringDate: String =  "-"
  //  private lateinit var model: CharacterListViewModel
    private  var characterList: CharacterList? = null
    private  var cd: List<Characterr> = ArrayList()

     var  repository: Repository = Repository()
    private lateinit var navController: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // createFragment()





    }






   /* fun createViewModel(){
        Log.d(TAG, "createViewModel")
        model = ViewModelProviders.of(this).get(CharacterListViewModel::class.java)

        // Create the observer which updates the UI.
        val listObserver = Observer<List<Characterr>> { characters ->
            // Update the UI, in this case, a TextView.
           //this.characterList = characterlist
            createFragment(characters)
        }
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.currentList.observe(this, listObserver)

        val dateObserver = Observer<String> {data ->
            //createFragment(characterList, data)
        }
        model.currentTime.observe(this, dateObserver)
      // Log.d(TAG, characterList!!.result.size.toString())

    }*/

   /* fun createFragment(){
        Log.d(TAG, "createFragment")
       *//* if (characterList!!.result.isNotEmpty()){
            stringDate = date
        }*//*

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
      //  var characterListFragment = CharacterListFragment.newInstance(characterList, stringDate)
        val characterListFragment = CharacterListFragment.newInstance()
        fragmentTransaction.add(R.id.fragment_container, characterListFragment)
        fragmentTransaction.commit()

    }*/

    override fun onClickedItem(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClickedButtonSort() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
