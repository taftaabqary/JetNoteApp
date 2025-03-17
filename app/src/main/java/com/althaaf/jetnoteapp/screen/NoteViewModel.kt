package com.althaaf.jetnoteapp.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.althaaf.jetnoteapp.data.Note
import com.althaaf.jetnoteapp.model.NotesDataSource

class NoteViewModel: ViewModel() {
    private val listNote = mutableStateListOf<Note>()

    init {
        listNote.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note: Note) {
        listNote.add(note)
    }

    fun removeNote(note: Note) {
        listNote.remove(note)
    }

    fun getAllNotes(): List<Note> {
        return listNote
    }
}