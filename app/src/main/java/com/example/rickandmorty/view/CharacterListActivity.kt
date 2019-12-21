package com.example.rickandmorty.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.example.rickandmorty.R
import android.app.ActivityManager as ActivityManager1

class CharacterListActivity : AppCompatActivity(){

    private val TAG: String = "CharacterListActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        var a = ActivityManager1.MemoryInfo()

        Log.d("fdfdf", a.toString())

    }
}
