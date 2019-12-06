package com.example.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.model.Characterr
import com.example.rickandmorty.usecase.GetCharactersUseCase
import io.reactivex.disposables.Disposable

class CharacterListViewModel : ViewModel() {

    private var getCharactersUseCase: GetCharactersUseCase = GetCharactersUseCase()

    private val TAG: String = "CharacterListViewModel"

    lateinit  var d: Disposable


    val currentList: LiveData<List<Characterr>> get() = _currentList
    private val _currentList: MutableLiveData<List<Characterr>> = object : MutableLiveData<List<Characterr>>() {
        override fun onActive() {
            d = getCharactersUseCase.getCharacters().subscribe { value = it.results }
        }
    }
    val currentTime: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


    override fun onCleared() {
        super.onCleared()
        d.dispose()
    }
}