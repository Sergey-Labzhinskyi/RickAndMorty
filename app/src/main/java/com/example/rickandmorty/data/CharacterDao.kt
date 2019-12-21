package com.example.rickandmorty.data

import androidx.room.*
import com.example.rickandmorty.model.CharacterList
import com.example.rickandmorty.model.Characterr
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface CharacterDao {

    @Transaction
    @Query("SELECT * FROM info")
    fun getAllCharacter(): Single<List<CharacterList>>



 @Transaction
    @Insert
    fun insert(list: List<Characterr>): Completable

}