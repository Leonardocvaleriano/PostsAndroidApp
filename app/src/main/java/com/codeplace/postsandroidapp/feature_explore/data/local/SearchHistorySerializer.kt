package com.codeplace.postsandroidapp.feature_explore.data.local

import androidx.datastore.core.Serializer
import com.codeplace.postsandroidapp.feature_explore.data.local.entity.SearchHistoryEntity
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object SearchHistorySerializer : Serializer<SearchHistoryEntity> {
    override val defaultValue: SearchHistoryEntity
        get() = SearchHistoryEntity()

    override suspend fun readFrom(input: InputStream): SearchHistoryEntity {
        return try {
            Json.decodeFromString(
                deserializer = SearchHistoryEntity.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(
        t: SearchHistoryEntity,
        output: OutputStream
    ) {
      output.write(
          Json.encodeToString(
              serializer = SearchHistoryEntity.serializer(),
              value = t
          ).encodeToByteArray()
      )
    }
}