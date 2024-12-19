package com.example.myadv.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
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
fun ExplainScreen(navController: NavController) {
    // 전체 화면 구성
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // TopBar 사용
        TopBar(navController)

        // 지구본 이미지
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center // 중앙 정렬
        ) {
            Image(
                painter = painterResource(id = R.drawable.globe), // 지구본 이미지 리소스
                contentDescription = "Globe",
                contentScale = ContentScale.Fit, // 비율 유지하며 크기 맞춤
                modifier = Modifier
                    .size(700.dp) // 크기 설정
                    .clickable {
                        navController.navigate("quiz_explain_screen")
                    }
            )
        }

        // 쿼카 이미지
        Image(
            painter = painterResource(id = R.drawable.home_quokka),
            contentDescription = "Quokka",
            modifier = Modifier
                .size(260.dp)
                .offset(x = 62.dp, y = 440.dp)
                .clickable {
                    navController.navigate("quokka_screen")
                }
        )

        // 우주선 이미지
        Image(
            painter = painterResource(id = R.drawable.spaceship_shop),
            contentDescription = "Spaceship",
            modifier = Modifier
                .size(240.dp)
                .offset(x = 80.dp, y = 100.dp)
        )

        // 책 이미지
        Image(
            painter = painterResource(id = R.drawable.puzzle_book),
            contentDescription = "Puzzle Book",
            modifier = Modifier
                .size(220.dp)
                .offset(x = 1020.dp, y = 380.dp)
        )
    }

    // Bubble 컴포넌트 사용
    Bubble(
        bubbleText = "나중에 지구 오른쪽에 책이 보일거야! 그건 친구가 맞춘 퍼즐들을 확인할 수 있는 도감이야. 도감의 모든 퍼즐을 클리어해보자! 아! 그리고 지구 왼쪽에 있는 우주선은 아이템을 살 수 있는 상점이야. 단계를 클리어할 때마다 주기도 하고 친구가 살 수도 있어! 아이템으로 날 꾸며줄래?",
        quokkaImageRes = R.drawable.explain_quokka, // 쿼카 이미지 리소스
        onNextClick = { navController.navigate("home_screen") }, // 다음 화면으로 이동
        quokkaSize = 540.dp, // 쿼카 이미지 크기
        quokkaOffsetX = (-40).dp, // 쿼카 X축 위치
        quokkaOffsetY = (-120).dp, // 쿼카 Y축 위치
        NameBoxOffsetY = (-300).dp // 쿼카 이름 상자의 Y축 위치 조정
    )
}

// 미리보기
@Preview(
    name = "Explain Screen Preview",
    showBackground = true,
    widthDp = 1280,
    heightDp = 800
)
@Composable
fun ExplainScreenPreview() {
    val navController = rememberNavController()
    ExplainScreen(navController)
}