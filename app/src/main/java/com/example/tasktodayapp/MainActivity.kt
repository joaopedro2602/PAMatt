package com.example.tasktodayapp

// Vek Histories

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktodayapp.model.Tarefa.Tarefa
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenContent(DrawerState(initialValue = DrawerValue.Closed))
        }
    }
}

@Composable
fun MainScreenContent(drawerState: DrawerState){
    val scaffoldState = rememberScaffoldState(drawerState = drawerState)
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = (Color(0xFFFFFF)),
                contentColor = Color.Black,
                title = { Text(text = "TaskTodayApp", color = Color(0xFF32383D))},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            tint = Color(0xFF32383D),
                            contentDescription = "Drawer Menu"
                        )
                    }
                }
            )
        },
        drawerBackgroundColor = (Color.White),
        drawerGesturesEnabled = drawerState.isOpen,
        drawerContent = {
            Box(
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .background(color = Color.White)
                    .height(50.dp)
                    .padding(5.dp)
            ){
                Text(text = "PERFIL", color = Color(0xFF32383D), fontWeight = FontWeight.Bold ,textAlign = TextAlign.Center, fontSize = 30.sp)
            }
            Column(modifier = Modifier.fillMaxWidth()) {

                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "João Pedro Cabral", modifier = Modifier.fillMaxWidth(), color = Color(0xFF32383D), fontSize = 20.sp)
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "RM: 22497", modifier = Modifier.fillMaxWidth(), color = Color(0xFF32383D), fontSize = 20.sp)
            }
        },
        content = {
                paddingValues -> Log.i("paddingValues", "$paddingValues")
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxSize()
            ) {
                MySearchField(modifier = Modifier.fillMaxWidth())

                val tAttQTS = Tarefa(
                    "Atividade de QTS",
                    "Fazer o teste unitário da aplicação JAVA",
                    Date(),
                    Date(),
                    status = 0.0
                )

                val tProvaPAM = Tarefa(
                    "Prova de PAM",
                    "Conteúdo Programático do Mês de Julho",
                    Date(),
                    Date(),
                    status = 0.0
                )

                val tSeminarioGEO = Tarefa(
                    "Seminário de Geografia",
                    "Tema: Geopolítica",
                    Date(),
                    Date(),
                    status = 0.0
                )

                val tProvaMAT = Tarefa(
                    "Prova de Matemática",
                    "Trigonometria Básica",
                    Date(),
                    Date(),
                    status = 0.0
                )

                val tAttPDTCC = Tarefa(
                    "Atividade PDTCC",
                    "Referencial Teórico do TCC",
                    Date(),
                    Date(),
                    status = 0.0
                )

                val tProvaPW = Tarefa(
                    "Prova de PW",
                    "Fazer um site com bootstrap",
                    Date(),
                    Date(),
                    status = 0.0
                )

                val minhaListaDeTarefas = listOf(tAttQTS, tAttPDTCC, tProvaMAT, tProvaPAM, tProvaPW, tSeminarioGEO)

                MyTaskWidgetList(minhaListaDeTarefas)
            }//Column
        },//content
        bottomBar = {
            BottomAppBar(

                backgroundColor = (Color.White),

                contentPadding = PaddingValues(7.dp),
                content = {
                    Text("João Pedro Cabral", fontWeight = FontWeight.Bold, color = Color(0xFF32383D))
                    Spacer(modifier = Modifier.width(130.dp))
                    Text("RM: 22497", fontWeight = FontWeight.Bold, color = Color(0xFF32383D))
                }
            )
        },

        isFloatingActionButtonDocked = false,
        floatingActionButton = { ExtendedFloatingActionButton(
            backgroundColor = (Color(0xFF32383D)),
            contentColor = Color.White,
            icon = {
                   Icon(
                       imageVector = Icons.Default.AddCircle,
                       contentDescription = "Adicionar Tarefa"
                   )
            },
            text = { Text("ADD")  },
            onClick = { /*TODO*/ })

        }
    ) //Scaffold
} //MainScreenContent

@Composable
fun MyTaskWidgetList(listaDeTarefas: List<Tarefa>){
    listaDeTarefas.forEach(
        action = { MyTaskWidget(modifier = Modifier.fillMaxWidth(), taredasASerMostrada = it) }
    )
} //MyTaskWidgetList

@Composable
fun MySearchField(modifier: Modifier){
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier,
        placeholder = { Text(text = "Pesquisar tarefas", color = Color(0xFF32383D))},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = Color(0xFF32383D),
                contentDescription = "Search Icon")
        }
    )
} //MySearchField

@SuppressLint("SuspiciousIndentation")
@Composable
fun MyTaskWidget(
    modifier: Modifier,
    taredasASerMostrada: Tarefa
){
    val dateFormatter = SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault())

        Column(modifier = modifier
            //.border(width = 0.5.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
            .padding(3.dp)
        ){
            Row(modifier = modifier.background(Color.White)) {
                Column(modifier = Modifier.width(150.dp).padding(10.dp)){
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        tint = Color(0xFF32383D),
                        contentDescription = "Icons of a pendent task")
                    Text(
                        text = dateFormatter.format(taredasASerMostrada.pzoFinal),
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                        color = Color(0xFF32383D),
                        fontSize = 12.sp
                    )
                }


                    Column {
                        Text(
                            text = taredasASerMostrada.nome,
                            fontSize = 20.sp,
                            color = Color(0xFF32383D),
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal
                        )

                        Text(
                            text = taredasASerMostrada.detalhes,
                            fontSize = 12.sp,
                            color = Color(0xFF32383D),
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Italic
                        )
                    }


            Spacer(modifier = Modifier.height(16.dp))
            }

    }
    Spacer(modifier = Modifier.height(16.dp))
} //MyTaskWidget


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreenContent(DrawerState(initialValue = DrawerValue.Closed))
}