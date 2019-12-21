package com.example.rickandmorty.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty.R

class SplashScreenActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)
        Log.d("SplashScreenActivity", "onCreate")
        timer()
    }

   private fun timer(){
            Thread.sleep(2000)
           val intent =  Intent(this, CharacterListActivity::class.java)
            startActivity(intent)
            finish()
    }
}