package com.example.rickandmorty.data

import android.app.Application
import android.content.Context
import androidx.room.Room
import android.util.Log

class App : Application() {

    companion object {
        lateinit var instance: App
        lateinit var appContext: Context
        lateinit var mAppDataBase: AppDataBase
         //var mAppDataBase: AppDataBase ? = null
        init {
            Log.d("APP", "companion object")
        }

    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        mAppDataBase = Room.databaseBuilder(
            this, AppDataBase::class.java, "database"
        ).build()
        appContext = applicationContext
        Log.d("APP", "onCreate")
    }






}