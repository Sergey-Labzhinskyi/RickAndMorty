package com.example.rickandmorty.usecase

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.rickandmorty.data.App
import com.example.rickandmorty.data.AppDataBase
import com.example.rickandmorty.data.CharacterDao
import com.example.rickandmorty.data.ICharacterService
import com.example.rickandmorty.model.Characterr
import com.example.rickandmorty.model.CharacterList
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject


class Repository(
    private var appDataBase: AppDataBase = App.mAppDataBase,
    private var characterDao: CharacterDao = appDataBase.mCharacterDao()
) {

    private val TAG: String = "Repository"


    private val _connectionState = PublishSubject.create<Boolean>()
    val connectionState: Observable<Boolean> get() = _connectionState

    init {
        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if(true) {
                    _connectionState.onNext(true)
                } else {
                    _connectionState.onNext(false)
                }
            }
        }
        App.instance.registerReceiver(broadcastReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
    }



    @RequiresApi(Build.VERSION_CODES.M)
    fun checkConnected(): Boolean {
        val connectivityManager =
            App.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)

    }


    fun getCharactersDB(): Observable<List<Characterr>> {
        return characterDao.getAllCharacter()
            .toObservable()
    }

    fun saveCharacters(list: List<Characterr>) : Completable {
      return  characterDao.insert(list)


    }


    private val retrofit =
        ICharacterService.getRetrofit().create(ICharacterService::class.java)

    fun getCharactersNetwork(page: Int): Observable<CharacterList> {
        Log.d(TAG, "getCharactersNetwork")
        return retrofit
            .getCharacter(page)
          //  .subscribeOn(Schedulers.io())
          //  .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                it
            }
            .doOnError { t:Throwable -> Log.d(TAG, t.message )}
            .toObservable()

    }

}