package com.jeffery.lerestaurant.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.*

@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun fromList(list: List<Any>): String {
        return list.toString()
    }

    @TypeConverter
    fun toList(string: String): List<Any> {
        return string.toList()
    }
}