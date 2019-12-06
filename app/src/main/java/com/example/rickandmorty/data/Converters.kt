package com.example.rickandmorty.data

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {


    /*@TypeConverter
    fun fromOrigin(origin: Origin): String {
        val gson = Gson()
        return gson.toJson(origin)
    }

    @TypeConverter
    fun fromStringOrigin(value: String): Origin {
        val type: Type = object : TypeToken<Origin>() {}.type
        return Gson().fromJson(value, type)
    }
*/

    @TypeConverter
    fun fromListEpisode(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringEpisode(value: String): List<String> {
        val listType: Type =
            object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

   /* @TypeConverter
    fun fromLocation(location: Location): String {
        val gson = Gson()
        return gson.toJson(location)
    }

    @TypeConverter
    fun fromStringLocation(value: String): Location {
        val type: Type = object : TypeToken<Location>() {}.type
        return Gson().fromJson(value, type)
    }*/
}