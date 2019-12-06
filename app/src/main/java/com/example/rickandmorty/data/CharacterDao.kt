package com.example.rickandmorty.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.rickandmorty.model.Characterr
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface CharacterDao {

    @Query("SELECT * FROM characterr")
    fun getAllCharacter(): Single<List<Characterr>>

    @Insert
    fun insert(list: List<Characterr>): Completable

}