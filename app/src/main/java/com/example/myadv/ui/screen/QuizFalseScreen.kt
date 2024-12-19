package com.example.myadv.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myadv.R
import com.example.myadv.ui.components.Bubble
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myadv.ui.components.PuzzleTopBar

@Composable
fun QuizFalseScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4)) // 전체 배경색
    ) {

        // TopBar를 사용하여 상단 구성
        PuzzleTopBar(navController)


        Bubble(
            bubbleText = "\n아쉽지만 정답이 아니야... 친구야... 우리 다시 한 번 생각해볼까?\n",
            quokkaImageRes = R.drawable.sad_quokka, // 예시 쿼카 이미지
            onNextClick = {navController.navigate("quiz_screen")}, // 클릭 시 동작
            quokkaSize = 540.dp, // 쿼카 이미지 크기 설정
            quokkaOffsetX = (-55).dp, // 쿼카 이미지 X축 위치 조정
            quokkaOffsetY = (-120).dp, // 쿼카 이미지 Y축 위치 조정
            NameBoxOffsetY = (-300).dp // 쿼카 이름 상자의 Y축 위치 조정
        )
    }
}

@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
fun QuizFalseScreenPreview() {
    val navController = rememberNavController()
    QuizFalseScreen(navController)
}