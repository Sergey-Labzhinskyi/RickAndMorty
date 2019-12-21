package com.example.rickandmorty.viewmodel


import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.*
import com.example.rickandmorty.model.CharacterList
import com.example.rickandmorty.model.Characterr
import com.example.rickandmorty.usecase.GetCharactersUseCase
import com.example.rickandmorty.usecase.Repository
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import kotlin.collections.toList as toList1

class CharacterListViewModel @Inject constructor( val getCharactersUseCase: GetCharactersUseCase): ViewModel() {


     val TAG: String = "CharacterListViewModel"

    lateinit  var d: Disposable
    private var pageNext: Int = 1


    val list = mutableListOf<Characterr>()


    val currentList: LiveData<List<Characterr>> get() = _currentList
     private val _currentList: MutableLiveData<List<Characterr>> = object : MutableLiveData<List<Characterr>>() {
         override fun onActive() {
             Log.d(TAG, "onActive")
             d = getCharactersUseCase.getCharacters(pageNext)!!
                 //.doOnError { t: Throwable -> Log.d(TAG, t.message) }
                 .subscribe({
                     list.addAll(it.results)
                     pageNext = it.info.next!!.replace("\\D+".toRegex(), "").toInt()
                     Log.d(TAG, "count ${it.info.count}")
                     value = list
                 },
                     {t: Throwable -> Log.d(TAG, t.message)
                     })
         }
     }
    val currentListSort: LiveData<List<Characterr>> get() = _currentListSort
  private   val _currentListSort: MutableLiveData<List<Characterr>> = object : MutableLiveData<List<Characterr>>() {
        override fun onActive() {
            sortList()
                value = list
        }


    }


    val currentTime: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    @SuppressLint("CheckResult")
    fun moreData(){

        val onnext = { characters: CharacterList ->
            list.addAll(characters.results!!)
            _currentList.value = list
            Log.d(TAG, "moreData")
            Log.d(TAG,  "count ${characters.info.count}" )
            pageNext = characters.info?.next!!.replace("\\D+".toRegex(),"").toInt()


        }
        val onerror = {t: Throwable ->

        }

        getCharactersUseCase.getCharacters(pageNext)?.subscribe(onnext, onerror)




    }

   private  fun sortList(){
        Log.d(TAG, "sortList")
        list.sort()
    }





    override fun onCleared() {
        super.onCleared()
        d.dispose()
    }
}