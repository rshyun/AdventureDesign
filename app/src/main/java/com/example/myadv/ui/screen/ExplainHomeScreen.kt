package com.example.myadv.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myadv.R
import com.example.myadv.ui.components.Bubble
import com.example.myadv.ui.components.TopBar

@Composable
fun ExplainHomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // TopBar를 사용하여 상단 구성
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

        // Bubble을 사용하여 중앙 하단의 대화 상자 및 쿼카 이미지 구성
        Bubble(
            bubbleText = "큰일이야... 우리의 지구가 점점 병들고 있어. 쓰레기가 쌓이고, 자연이 아파해... 하지만 걱정하지 마! 우리가 힘을 합치면 지구를 다시 깨끗하고 건강하게 만들 수 있어. 여기 있는 퍼즐을 하나씩 풀고, 문제를 해결해서 지구를 구하는 모험을 시작해볼래? 내가 옆에서 도와줄게! 가볼까?",
            quokkaImageRes = R.drawable.sad_quokka,
            onNextClick = { navController.navigate("explain_screen") }, // Next 버튼 클릭 시 동작
            quokkaSize = 540.dp, // 쿼카 이미지 크기 설정
            quokkaOffsetX = (-45).dp, // 쿼카 이미지 X축 위치 조정
            quokkaOffsetY = (-120).dp, // 쿼카 이미지 Y축 위치 조정
            NameBoxOffsetY = (-300).dp // 쿼카 이름 상자의 Y축 위치 조정
        )
    }
}

@Preview(
    name = "Explain Home Screen Preview",
    showBackground = true,
    widthDp = 1280,
    heightDp = 800
)
@Composable
fun ExplainHomeScreenPreview() {
    val navController = rememberNavController()
    ExplainHomeScreen(navController)
}