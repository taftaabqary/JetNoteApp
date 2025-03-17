package com.althaaf.jetnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.althaaf.jetnoteapp.screen.NoteScreen
import com.althaaf.jetnoteapp.screen.NoteViewModel
import com.althaaf.jetnoteapp.ui.theme.JetNoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteApp()
        }
    }
}

@Composable
fun NoteApp(modifier: Modifier = Modifier, noteViewModel: NoteViewModel = viewModel()) {
    JetNoteAppTheme {
        NoteScreen(
            modifier = modifier,
            listNote = noteViewModel.getAllNotes(),
            onAddNote = { noteViewModel.addNote(it) },
            onRemoveNote = { noteViewModel.removeNote(it) }
        )
    }
}

