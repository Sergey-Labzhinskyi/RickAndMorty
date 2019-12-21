package com.example.rickandmorty.usecase

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.*
import android.os.Build
import android.util.Log
import com.example.rickandmorty.data.*
import com.example.rickandmorty.model.CharacterList
import com.example.rickandmorty.model.Characterr
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
  //  private var appDataBase: AppDataBase,
    //private var characterDao: CharacterDao,
    //var dataBaseHelper: DataBaseHelper,
    private val retrofit: Retrofit
) {

/*
    (
    private var appDataBase: AppDataBase = App.mAppDataBase,
    private var characterDao: CharacterDao = appDataBase.mCharacterDao()
    )*/

    private val TAG: String = "Repository"


    //private val retrofit =
     //   ICharacterService.getRetrofit().create(ICharacterService::class.java)


/*    private val _connectionState = PublishSubject.create<Boolean>()
    val connectionState: Observable<Boolean> get() = _connectionState
    init {
        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if(checkConnected(context)) {
                    _connectionState.onNext(true)
                } else {
                    _connectionState.onNext(false)
                }
            }
        }
        App.instance.registerReceiver(broadcastReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
    }*/


     val _connectionState = BehaviorSubject.create<Boolean>()
    val connectionState: Observable<Boolean> get() = _connectionState


    var state: Boolean = true

    init {
        Log.d(TAG, "init")

        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        Log.d(TAG, networkRequest.toString())

        val networkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onLost(network: Network?) {
                Log.d(TAG, "networkcallback called from onLost")
                _connectionState.onNext(false)
                state = false

            }

            override fun onUnavailable() {
                Log.d(TAG, "networkcallback called from onUnvailable")
            }

            override fun onLosing(network: Network?, maxMsToLive: Int) {
                Log.d(TAG, "networkcallback called from onLosing")
            }

            override fun onAvailable(network: Network?) {
                Log.d(TAG, "NetworkCallback network called from onAvailable ")
                _connectionState.onNext(true)
                state = true
            }
        }
        val connectivityManager =
            App.instance.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

    }



/*    fun getCharactersDB(): Observable<CharacterList> {
        Log.d(TAG, "getCharactersDB")
        return dataBaseHelper.getDataBase().getAllCharacter()
            .doOnSuccess { Log.d(TAG, it.size.toString()) }

            .doOnError { t -> Log.d(TAG, t.message.toString()) }

            .toObservable().flatMapIterable { it -> it }
            .doOnNext { Log.d(TAG, it.results.size.toString()) }
    }

    fun saveCharacters(list: List<Characterr>): Completable {
        Log.d(TAG, "saveCharacters ${list.size}")
        return dataBaseHelper.getDataBase().insert(list)
            .doOnComplete { Log.d(TAG, "doOnComplete") }
            .doOnError { t -> Log.d(TAG, t.message.toString()) }



    }*/

   /* fun characterDb(page: Int): Observable<CharacterList> {
        return Observable.just(null)
    }*/

    fun getCharactersNetwork(page: Int): Observable<CharacterList> {
        Log.d(TAG, "getCharactersNetwork $page")
        return retrofit.create(ICharacterService::class.java).getCharacter(page)
            //  .subscribeOn(Schedulers.io())
            //  .observeOn(AndroidSchedulers.mainThread())
            /* .doOnSuccess {
                 it
             }*/
            //  .doOnError { t:Throwable -> Log.d(TAG, t.message )}
            .toObservable()
    }
}
