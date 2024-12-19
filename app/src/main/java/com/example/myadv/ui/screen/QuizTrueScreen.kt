package com.example.myadv.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myadv.R
import com.example.myadv.ui.components.Bubble
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myadv.ui.components.PuzzleTopBar

@Composable
fun QuizClearScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4)) // 전체 배경색
    ) {

        // TopBar를 사용하여 상단 구성
        PuzzleTopBar(navController)

        // 쿼카와 대화 상자 (Bubble 컴포넌트)
        Bubble(
            bubbleText = "맞아, 정답이야! 너 정말 똑똑하다. 어떻게 알았어? 태평양에 있는 쓰레기 섬은 무려 한국의 약 16배나 되는 면적을 지니고 있어. 생각 보다 엄청 크지? 친구가 정답을 맞춰준 덕분에 이곳이 곧 깨끗해질거야! 정말 고마워, 친구야!",
            quokkaImageRes = R.drawable.love_quokka, // 예시 쿼카 이미지
            onNextClick = { navController.navigate("clear_screen") },
            quokkaSize = 540.dp, // 쿼카 이미지 크기
            quokkaOffsetX = (-50).dp, // 쿼카 이미지 X축 위치
            quokkaOffsetY = (-120).dp, // 쿼카 이미지 Y축 위치
            NameBoxOffsetY = (-300).dp // 쿼카 이름 상자의 Y축 위치
        )
    }
}

@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
fun QuizTrueScreenPreview() {
    val navController = rememberNavController()
    QuizClearScreen(navController)
}