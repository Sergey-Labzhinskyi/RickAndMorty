package com.example.rickandmorty.data

import android.util.Log
import com.example.rickandmorty.model.CharacterList
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ICharacterService {

    @GET("character/")
    fun getCharacter(@Query("page") page: Int) : Single<CharacterList>

    companion object {
        private const val BASE_URL: String = "https://rickandmortyapi.com/api/"


        fun getRetrofit() : Retrofit {
            Log.d("ICharacterService", "getRetrofit")

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)


                return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient.build())
                    .build()







        }
    }
}