package com.althaaf.jetnoteapp.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.althaaf.jetnoteapp.data.Note
import com.althaaf.jetnoteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {
    private var _note: MutableStateFlow<List<Note>> = MutableStateFlow(emptyList())
    val note = _note.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.getAllNotes()
                .distinctUntilChanged()
                .collect { data ->
                    if (data.isEmpty()) {
                        Log.d("NoteViewModel", "Data kosong")
                    } else {
                        _note.value = data
                    }
                }
        }
    }

     fun addNote(note: Note) {
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    }

    fun removeNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }
}