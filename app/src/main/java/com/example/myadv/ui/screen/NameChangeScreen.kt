package com.example.myadv.ui.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myadv.data.getQuokkaName
import com.example.myadv.data.setFirstLaunch
import com.example.myadv.data.setQuokkaName
import com.example.myadv.ui.components.TopBar
import kotlinx.coroutines.runBlocking
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import com.example.myadv.R

@Composable
fun NameChangeScreen(navController: NavController, context: Context) {
    var inputName by remember { mutableStateOf("") }
    var savedName by remember { mutableStateOf(context.getQuokkaName()) }

    val context = LocalContext.current
    val quokkaName = remember { mutableStateOf("???") }

    LaunchedEffect(Unit) {
        val savedName = runBlocking { context.getQuokkaName() }
        quokkaName.value = savedName
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(navController)

        // 지구본 이미지
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center // 중앙 정렬
        ) {
            Image(
                painter = painterResource(id = R.drawable.globe), // 지구본 이미지 리소스
                contentDescription = "Globe",
                modifier = Modifier.size(700.dp), // 크기 설정
                contentScale = ContentScale.Fit // 비율 유지하며 크기 맞춤
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(720.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.LightGray.copy(alpha = 0.9f))
                    .padding(vertical = 60.dp, horizontal = 28.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(34.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "퀴카의 이름을 바꿔보자!",
                        style = TextStyle(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.Black
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    TextField(
                        value = inputName,
                        onValueChange = { inputName = it },
                        placeholder = {
                            Text(
                                text = quokkaName.value,
                                style = TextStyle(
                                    fontSize = 32.sp,
                                    color = Color.Gray,
                                    textAlign = TextAlign.Center
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        modifier = Modifier
                            .width(500.dp)
                            .height(78.dp)
                            .clip(RoundedCornerShape(22.dp)),
                        textStyle = TextStyle(
                            fontSize = 32.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color.Black
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done // 키보드에서 "Done" 버튼을 표시
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                // 엔터키(또는 Done 버튼)가 눌렸을 때 저장 처리
                                if (inputName.isNotBlank()) {
                                    runBlocking {
                                        context.setQuokkaName(inputName)
                                        context.setFirstLaunch(false)
                                    }
                                    savedName = inputName
                                    inputName = ""
                                    navController.navigate("home_screen")
                                }
                            }
                        )
                    )

                    Button(
                        onClick = {
                            if (inputName.isNotBlank()) {
                                runBlocking {
                                    context.setQuokkaName(inputName)
                                    context.setFirstLaunch(false)
                                }
                                savedName = inputName
                                inputName = ""
                                navController.navigate("home_screen")
                            }
                        },
                        modifier = Modifier
                            .width(208.dp)
                            .height(68.dp),
                        shape = RoundedCornerShape(22.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0B2F9F)
                        )
                    ) {
                        Text(
                            text = "저장",
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}


@Preview(
    name = "Name change Screen Preview",
    showBackground = true,
    widthDp = 1280,
    heightDp = 800
)
@Composable
fun NameChangeScreenPreview() {
    val navController = rememberNavController() // 미리보기용 NavController
    val context = LocalContext.current
    NameChangeScreen(navController = navController, context = context)
}