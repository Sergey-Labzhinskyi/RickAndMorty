package com.example.rickandmorty.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Info(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("pages")
    val pages: Int? = null,
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("prev")
    val prev: String? = null
) : Serializable





