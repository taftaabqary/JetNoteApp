package com.althaaf.jetnoteapp.data

import java.time.Instant
import java.util.Date
import java.util.UUID

data class Note(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val localTime: Date = Date.from(Instant.now())
)
