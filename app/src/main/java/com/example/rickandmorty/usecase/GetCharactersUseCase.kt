package com.example.rickandmorty.usecase

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.data.App
import com.example.rickandmorty.model.CharacterList
import com.example.rickandmorty.view.ToastUtil
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: Repository) {

    private val TAG: String = "GetCharactersUseCase"


    var isConnect: Boolean = false



    @SuppressLint("CheckResult")
    fun getCharacters(page: Int) : Observable<CharacterList>? {
        /*repository.characterDb(0).flatMap {
            if (it == null) {
                repository.connectionState.flatMap {
                    if (it)  repository.getCharactersNetwork(page)
                    else Observable.just(null)
                }
            } else {
                Observable.just(it)
            }
        }

        var characterList2: Observable<CharacterList>*/
        Log.d(TAG, "getCharacters $page")



        return   repository.connectionState.flatMap {
                if (it)  repository.getCharactersNetwork(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                else Observable.just(null)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }





//        repository.connectionState.flatMap {
//            if (it) repository.getCharactersNetwork(page)
//            else throw Exception("no connection")
//        }

    /*  return repository.characterList
          ?.subscribeOn(Schedulers.io())
          ?.observeOn(AndroidSchedulers.mainThread())!!*/



/*

       var characterList: Observable<CharacterList>? = null
        repository.connectionState.subscribe ({
            Log.d(TAG, it.toString())
            characterList = if (it){

                repository.getCharactersNetwork(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }else{
                null
            }
        },
            {t: Throwable -> Log.d(TAG, t.message)})
        return characterList
*/




      /*  if (repository.state){
            return  repository.getCharactersNetwork(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
             .doOnError { t:Throwable -> Log.d(TAG, t.message ) }
        }else{
            ToastUtil.showLong("Нет подключения", App.appContext)
            return null
        }*/




//repository.connectionState
        // repository.register()

        /* return repository.getCharactersNetwork(page)
              // .map {list -> mutableLiveData.postValue(list) }
            //  .map { list -> characterListViewModel.currentList.postValue(list) }
              .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .doOnError { t:Throwable -> Log.d(TAG, t.message ) }*/

    }


}
