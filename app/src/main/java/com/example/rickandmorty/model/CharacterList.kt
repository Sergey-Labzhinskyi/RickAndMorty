package com.example.rickandmorty.model

import androidx.room.*
import com.google.gson.annotations.SerializedName


data class CharacterList(
    @SerializedName("info")
    @Embedded
    val info: Info,
    @Relation(
        parentColumn = "idpage",
        entityColumn = "id"
    )
    @SerializedName("results")
    var results: List<Characterr>
)