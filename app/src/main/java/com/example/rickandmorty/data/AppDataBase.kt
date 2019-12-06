package com.example.rickandmorty.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.model.CharacterList
import com.example.rickandmorty.model.Characterr

@Database(entities = [Characterr::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun mCharacterDao(): CharacterDao
}