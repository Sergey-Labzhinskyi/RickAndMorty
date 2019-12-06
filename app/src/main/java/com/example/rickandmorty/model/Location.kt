package com.example.rickandmorty.model

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Location (
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null):Serializable
