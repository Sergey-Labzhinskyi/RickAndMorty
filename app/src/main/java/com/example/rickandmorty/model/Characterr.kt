package com.example.rickandmorty.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
@Entity
data class Characterr(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("species")
    val species: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("origin")
    @Embedded(prefix = "origin")
    val mOrigin: Origin? = null,
    @SerializedName("location")
    @Embedded(prefix = "location")
    val mLocation: Location?,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("episode")
   // @Ignore
    val episode: List<String>? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("created")
    val created: String? = null
) : Serializable



