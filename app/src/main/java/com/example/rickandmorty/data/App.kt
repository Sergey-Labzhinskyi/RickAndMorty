package com.example.rickandmorty.data

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room

class App : Application() {


    companion object {
        lateinit var instance: App
        lateinit var appContext: Context
        lateinit var mAppDataBase: AppDataBase
        lateinit var  appComponent: ApplicationComponent
        lateinit var applicationComponent: ApplicationComponent
        //var mAppDataBase: AppDataBase ? = null

        //appComponent = DaggerApplicationComponent.create()
    }



    override fun onCreate() {
        super.onCreate()
        instance = this
        mAppDataBase = Room.databaseBuilder(
            this, AppDataBase::class.java, "database"
        ).build()
        appContext = applicationContext
        applicationComponent = DaggerApplicationComponent.builder().appModule( AppModule()).build()

        Log.d("APP", "onCreate")
    }
}