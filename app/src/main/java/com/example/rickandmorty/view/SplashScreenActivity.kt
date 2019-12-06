package com.example.rickandmorty.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timer()
    }

   private fun timer(){
            Thread.sleep(2000)
           val intent =  Intent(this, CharacterListActivity::class.java)
            startActivity(intent)
            finish()
    }
}