package com.althaaf.jetnoteapp.utils

import androidx.room.TypeConverter
import java.util.UUID

class IdConverter {
    @TypeConverter
    fun stringFromIdInput(uuid: UUID): String {
        return uuid.toString()
    }

    fun uuidFromStringEntity(id: String): UUID {
        return UUID.fromString(id)
    }
}