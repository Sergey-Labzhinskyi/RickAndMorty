package com.example.rickandmorty.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.model.CharacterList
import com.example.rickandmorty.model.Characterr
import com.example.rickandmorty.model.Info
import dagger.Module
import dagger.Provides

@Database(entities = [Characterr::class, Info::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)

abstract class AppDataBase : RoomDatabase() {
    abstract fun mCharacterDao(): CharacterDao
}