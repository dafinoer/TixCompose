package com.dafinrs.tixcompose.data.models.locals

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.dafinrs.tixcompose.LocationUserMessage
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object LocationUserSerializer : Serializer<LocationUserMessage> {
    override val defaultValue: LocationUserMessage = LocationUserMessage.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): LocationUserMessage {
        try {
            return LocationUserMessage.parseFrom(input)
        } catch (error: InvalidProtocolBufferException) {
            throw CorruptionException("Error Read Proto file", error)
        }
    }

    override suspend fun writeTo(t: LocationUserMessage, output: OutputStream) {
        t.writeTo(output)
    }

}