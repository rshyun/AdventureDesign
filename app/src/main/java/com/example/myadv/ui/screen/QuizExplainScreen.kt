package com.example.myadv.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myadv.R
import com.example.myadv.ui.components.Bubble
import com.example.myadv.ui.components.PuzzleTopBar

@Composable
fun QuizExplainScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4)) // 전체 배경색
    ) {

        // TopBar를 사용하여 상단 구성
        PuzzleTopBar(navController)

        // 쿼카와 대화 상자 (Bubble 컴포넌트)
        Bubble(
            bubbleText = "우와! 퍼즐을 다 맞췄어! 이제 마지막 단계야! 이곳을 깨끗하게 만들고 싶다면 내가 내는 퀴즈를 맞혀줘! 퀴즈를 풀면, 더러웠던 환경이 반짝반짝 깨끗하게 바뀔 거야! 준비됐지? 한 번 가볼까?",
            quokkaImageRes = R.drawable.name_quokka, // 예시 쿼카 이미지
            onNextClick = { navController.navigate("quiz_screen") }, // 다음 화면으로 이동
            quokkaSize = 540.dp, // 쿼카 이미지 크기
            quokkaOffsetX = (-30).dp, // 쿼카 이미지 X축 위치
            quokkaOffsetY = (-90).dp, // 쿼카 이미지 Y축 위치
            NameBoxOffsetY = (-240).dp // 쿼카 이름 상자의 Y축 위치
        )
    }
}

// 프리뷰
@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
fun QuizExplainScreenPreview() {
    val navController = rememberNavController()
    QuizExplainScreen(navController)
}
