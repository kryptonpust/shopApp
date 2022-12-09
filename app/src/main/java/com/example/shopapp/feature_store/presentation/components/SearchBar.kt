package com.example.shopapp.feature_store.presentation.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SearchBar(mutableState: MutableState<TextFieldValue>,onSearchAction: (String)->Unit)
{
    val state = remember { mutableState }
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        modifier= Modifier
            .padding(10.dp,5.dp)
            .fillMaxWidth(),
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        placeholder={
                    Text(text = "কাঙ্ক্ষিত পণ্যটি খুঁজুন")
        },

        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
            onSearchAction(state.value.text)
        }),
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        onSearchAction("")
                        state.value =
                            TextFieldValue("")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp),
                        tint = Color.Red
                    )
                }
            }else{
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(24.dp)
                )
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.White
        )
    )
}

@Composable
@Preview
fun TestSearchUI()
{

    Surface() {
        val searchState = remember {
            mutableStateOf(TextFieldValue(""))
        }
        SearchBar(searchState, onSearchAction = {})
    }
}