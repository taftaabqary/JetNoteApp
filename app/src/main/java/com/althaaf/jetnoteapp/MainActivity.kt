package com.althaaf.jetnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.althaaf.jetnoteapp.screen.NoteScreen
import com.althaaf.jetnoteapp.screen.NoteViewModel
import com.althaaf.jetnoteapp.ui.theme.JetNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val noteViewModel: NoteViewModel by viewModels()
            NoteApp(noteViewModel = noteViewModel)
        }
    }
}

@Composable
fun NoteApp(modifier: Modifier = Modifier, noteViewModel: NoteViewModel) {
    JetNoteAppTheme {
        NoteScreen(
            modifier = modifier,
            listNote = noteViewModel.note.collectAsState().value,
            onAddNote = { noteViewModel.addNote(it) },
            onRemoveNote = { noteViewModel.removeNote(it) }
        )
    }
}

