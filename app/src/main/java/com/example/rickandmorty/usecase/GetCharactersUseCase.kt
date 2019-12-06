package com.example.rickandmorty.usecase

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.rickandmorty.model.CharacterList
import com.example.rickandmorty.viewmodel.CharacterListViewModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.observable.ObservableBlockingSubscribe.subscribe
import io.reactivex.schedulers.Schedulers

class
GetCharactersUseCase {

    private val TAG: String = "GetCharactersUseCase"


    private val repository = Repository()


    fun getCharacters() : Observable<CharacterList>{
        Log.d(TAG, "getCharacters")
       return repository.getCharactersNetwork(1)
            // .map {list -> mutableLiveData.postValue(list) }
          //  .map { list -> characterListViewModel.currentList.postValue(list) }


            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
           .doOnError { t:Throwable -> Log.d(TAG, t.message ) }

    }


    }


