package com.example.rickandmorty.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Origin (
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null): Serializable