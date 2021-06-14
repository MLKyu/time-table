package com.alansoft.timetable.data

import com.google.gson.*
import java.lang.reflect.Type


/**
 * Created by LEE MIN KYU on 2021/06/14
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
class ListTypeAdapter : JsonSerializer<List<String>>,
    JsonDeserializer<List<String>> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext
    ): List<String> {
        val gsonBuilder = GsonBuilder()
        val gson: Gson = gsonBuilder.create()
        val result = mutableListOf<String>()
        if (json.isJsonArray) {
            val list = mutableListOf<String>()
            for (element in json.asJsonArray) {
                val dayOfWeek: String = gson.fromJson(element, String::class.java)
                list.add(dayOfWeek)
            }
            result.addAll(list)
        }
        return result
    }

    override fun serialize(
        src: List<String>?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement? {
        return null
    }
}