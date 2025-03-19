package com.althaaf.jetnoteapp.repository

import com.althaaf.jetnoteapp.data.Note
import com.althaaf.jetnoteapp.data.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().flowOn(Dispatchers.IO).conflate()
    }

    suspend fun getNoteById(id: String): Note {
        return noteDao.getNoteById(id)
    }

    suspend fun insertNote(note: Note) {
        return noteDao.insertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        return noteDao.deleteNote(note)
    }

    suspend fun updateNote(note: Note) {
        return noteDao.updateNote(note)
    }

    suspend fun deleteAllNotes() {
        return noteDao.deleteAll()
    }
}