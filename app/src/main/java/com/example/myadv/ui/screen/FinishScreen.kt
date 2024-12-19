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
fun FinishScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4)) // 전체 배경색
    ) {

        // TopBar를 사용하여 상단 구성
        PuzzleTopBar(navController)

        // 쿼카와 대화 상자 (Bubble 컴포넌트)
        Bubble(
            bubbleText = "이제 봐! 너의 노력 덕분에 이곳이 완전히 달라졌어! 쓰레기로 가득했던 풍경이 이렇게 맑고 깨끗하게 변했어. 정말 감동적이지 않아? 너처럼 열심히 도와주는 친구가 있기에 지구도 다시 미소를 되찾고 있어. 앞으로도 지구를 지키는 멋진 친구로 계속 활약해줘! 내가 정말 자랑스러워!",
            quokkaImageRes = R.drawable.love_quokka, // 예시 쿼카 이미지
            onNextClick = { navController.navigate("home_screen") },
            quokkaSize = 540.dp, // 쿼카 이미지 크기
            quokkaOffsetX = (-50).dp, // 쿼카 이미지 X축 위치
            quokkaOffsetY = (-120).dp, // 쿼카 이미지 Y축 위치
            NameBoxOffsetY = (-300).dp // 쿼카 이름 상자의 Y축 위치
        )
    }
}

@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
fun FinishScreenPreview() {
    val navController = rememberNavController()
    FinishScreen(navController)
}