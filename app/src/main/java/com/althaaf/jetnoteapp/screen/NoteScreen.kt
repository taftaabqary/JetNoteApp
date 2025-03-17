package com.althaaf.jetnoteapp.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.althaaf.jetnoteapp.components.ButtonSave
import com.althaaf.jetnoteapp.components.NoteInputText
import com.althaaf.jetnoteapp.data.Note
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    modifier: Modifier = Modifier,
    listNote: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var titleNote by remember {
        mutableStateOf("")
    }

    var descriptionNote by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Text("Note App")
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notification icon"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFDADFE3))
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NoteInputText(
                modifier = Modifier.padding(vertical = 8.dp),
                text = titleNote,
                label = "Title",
            ) {
                if(it.all { char ->
                    char.isLetter() || char.isWhitespace()
                    })
                    titleNote = it
            }

            NoteInputText(
                modifier = Modifier.padding(vertical = 8.dp),
                text = descriptionNote,
                label = "Description",
            ) {
                if (it.all { char ->
                        char.isLetter() || char.isWhitespace()
                    }) descriptionNote = it
            }
            ButtonSave(
                text = "Save Note",
            ) {
                if(titleNote.isNotEmpty() && descriptionNote.isNotEmpty())
                    onAddNote(Note(title = titleNote, description = descriptionNote))
                    Toast.makeText(context, "Success add note data", Toast.LENGTH_SHORT).show()
                    titleNote = ""
                    descriptionNote = ""
            }

            HorizontalDivider(modifier = Modifier.padding(10.dp))
            LazyColumn {
                items(listNote, key = {
                    it.id
                }) {
                    ItemRow(note = it) {
                        onRemoveNote(it)
                    }
                }
            }

        }
    }
}

@Composable
fun ItemRow(modifier: Modifier = Modifier, note: Note, onClickNote: () -> Unit) {
    Surface(
        modifier = modifier.padding(bottom = 8.dp, top = 0.dp, start = 8.dp, end = 8.dp).fillMaxWidth()
            .clickable { onClickNote() },
        color = Color(0xFFDFE6EB),
        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 18.dp, bottomStart = 18.dp, bottomEnd = 0.dp)
    ) {
        Column(modifier.padding(8.dp)) {
            Text(note.title)
            Text(note.description)
            Text(SimpleDateFormat("EEE, d MMM hh:mm aaa", Locale.getDefault()).format(note.localTime))
        }
    }
}