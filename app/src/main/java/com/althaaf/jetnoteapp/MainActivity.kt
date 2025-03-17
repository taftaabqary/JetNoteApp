package com.althaaf.jetnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.althaaf.jetnoteapp.data.Note
import com.althaaf.jetnoteapp.screen.NoteScreen
import com.althaaf.jetnoteapp.ui.theme.JetNoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp {
                val mutableListNote = remember {
                    mutableStateListOf<Note>()
                }
                NoteScreen(
                    modifier = Modifier,
                    listNote = mutableListNote,
                    onAddNote = {
                        mutableListNote.add(it)
                    },
                    onRemoveNote = {
                        mutableListNote.remove(it)
                    }
                )
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    JetNoteAppTheme {
        content()
    }
}

