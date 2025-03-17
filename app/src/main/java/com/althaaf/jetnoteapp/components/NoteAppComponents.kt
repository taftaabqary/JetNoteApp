package com.althaaf.jetnoteapp.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@Composable
fun NoteInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    imeAction: ImeAction = ImeAction.Done,
    keyboardActions: KeyboardActions = KeyboardActions(onDone = {}),
    onTextChange: (String) -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier,
        label = { Text(label) },
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction
        )
    )
}

@Composable
fun ButtonSave(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
    ) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) { Text(text) }
}