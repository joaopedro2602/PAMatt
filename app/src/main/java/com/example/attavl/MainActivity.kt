package com.example.attavl

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.attavl.model.Tarefa.Tarefa
import com.example.attavl.ui.theme.AttAvlTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreecontent(DrawerState(initialValue = DrawerValue.Closed))
        }
    }
}

@Composable
fun MainScreecontent(drawerState: DrawerState) {
    val scaffoldState = rememberScaffoldState(drawerState = drawerState)
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "TaskBarState")},
                navigationIcon = {
                    IconButton(onClick = {
                        CoroutineScope(Dispatchers.Default).launch {
                            scaffoldState.drawerState.open()
                        }
                         }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Drawer menu")
                    }

                }
            )
        },
        drawerBackgroundColor = Color.White,
        drawerGesturesEnabled = drawerState.isOpen,
        drawerContent = {
               Box(
                   modifier = Modifier
                       .background(Color.Magenta)
                       .height(16.dp)
               ){
                   Text(text = "Opções!!!")
               }
                Column() {
                    Text(text = "Opção de Menu 1")
                    Text(text = "Opção de Menu 2")
                    Text(text = "Opção de Menu 3")

                }
        },
        content = {
            paddingValues -> Log.i("PaddingValues", "$paddingValues")
            Column(modifier = Modifier
                .background(Color.Yellow)
                .fillMaxSize()) {
                MySearchField(modificador = Modifier.fillMaxWidth())
                listOf<Tarefa>(Tarefa("Estudar prova de calculo","Capitulo 1 do livro xyz",Date(),calendar.set(2023,)))
                MyTaskWidget()
                MyTaskWidget(
                    modificador = Modifier.fillMaxWidth(),
                    taskName = "Preparar Aula LazyList/LazyColumn",
                    taskDetails = "É bem melhor usar laztlist do que column",
                    deadEndDate = Date()
                )
                MyTaskWidget(
                    modificador = Modifier.fillMaxWidth(),
                    taskName = "Prova de matemática",
                    taskDetails = "Estudar capitulo 1 e 2",
                    deadEndDate = Date()
                )
                Text(text = "Tesk4")

            }
        },
        bottomBar = {
            BottomAppBar(content = { Text(text = "asdf")})
        }
    )
}
@Composable
fun MyTaskWidgetList(listaDeTarefas: List<Tarefa>){
    listaDeTarefas.forEach(action = { Log.i("#####%%%%%#####", "${it.nome}") })
}
@Composable
fun MySearchField (modificador: Modifier){
    TextField(
        value = "",
        onValueChange = {},
        modifier = modificador,
        placeholder = { Text(text = "Pesquisar tarefa")},
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search,
                 contentDescription = "Search Icon" )
        })
}
@Composable
fun MyTaskWidget (
    modificador: Modifier,
    taskName: String,
    taskDetails: String,
    deadEndDate: Date){
    val dateFormater = SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault())
    Row(modifier = modificador) {
        Column() {
            Icon(imageVector = Icons.Default.Notifications,
                contentDescription = "Icons a pendant task")
            Text(
                text = dateFormater.format(deadEndDate),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 12.sp
            )
        }

        Column(modifier = modificador
            .border(width = 1.dp, color = Color.Black)
            .padding(3.dp)
        ) {
            Text(
                text = taskName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic)
            Text(text = taskDetails,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                fontStyle =  FontStyle.Normal)
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AttAvlTheme {
        MainScreecontent(DrawerState(initialValue = DrawerValue.Closed))
    }
}