package com.inmersoft.room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.inmersoft.room.data.source.local.entity.Word
import com.inmersoft.room.ui.theme.RoomTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            RoomTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen(wordViewModel: WordViewModel = hiltViewModel()) {

    val allWords = wordViewModel.allWords.collectAsState(initial = null)
    val textState = remember { mutableStateOf(TextFieldValue()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {

                val word = Word(textState.value.text)
                wordViewModel.insert(word = word)
                textState.value = TextFieldValue()
            })
            {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(
                        id = R.string.add_icon_text
                    )

                )
            }
        }
    ) {
        allWords.value?.let { listOfWord ->
            Column(modifier = Modifier.fillMaxSize()) {
                if (listOfWord.isEmpty())
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(8f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Sin palabras"
                        )
                    }
                else

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(8f)
                    ) {
                        item {
                            Text(text = "Words")
                        }
                        items(listOfWord) { currentWord ->
                            Text(text = "Words: ${currentWord.word}")
                        }
                    }


                TextField(
                    modifier = Modifier.weight(1f),
                    value = textState.value,
                    onValueChange = { newStateText ->
                        textState.value = newStateText
                    })
            }
        }
    }
}

