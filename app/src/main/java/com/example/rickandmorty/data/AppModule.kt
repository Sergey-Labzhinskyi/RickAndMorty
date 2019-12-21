package com.example.rickandmorty.data

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.rickandmorty.viewmodel.ViewModelModule
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule{


    @Singleton
    @Provides
    fun getRetrofit() : Retrofit {

        Log.d("ICharacterService", "getRetrofit")

        val BASE_URL: String = "https://rickandmortyapi.com/api/"

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getOkHttp().build())
            .build()
    }


    @Singleton
    @Provides
    fun getOkHttp() : OkHttpClient.Builder {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
    }

    @Singleton
    @Provides
    fun getDataBase(): CharacterDao{

        val appDataBase: AppDataBase = App.mAppDataBase
        return appDataBase.mCharacterDao()

    }

}