package com.example.myadv.ui.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myadv.R
import com.example.myadv.ui.components.TopBar

@Composable
fun HomeScreen(navController: NavController) {
    // 각 객체의 애니메이션 상태 정의
    val quokkaOffset = remember { Animatable(0f) }
    val spaceshipOffset = remember { Animatable(0f) }
    val bookOffset = remember { Animatable(0f) }

    // 쿼카 애니메이션
    LaunchedEffect(Unit) {
        quokkaOffset.animateTo(
            targetValue = 25f, // 이동 범위
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 2000, easing = LinearEasing), // 이동 속도
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    // 우주선 애니메이션
    LaunchedEffect(Unit) {
        spaceshipOffset.animateTo(
            targetValue = 25f, // 이동 범위
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1800, easing = LinearEasing), // 이동 속도
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    // 책 애니메이션
    LaunchedEffect(Unit) {
        bookOffset.animateTo(
            targetValue = 25f, // 이동 범위
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 2500, easing = LinearEasing), // 이동 속도
                repeatMode = RepeatMode.Reverse
            )
        )
    }

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
                        navController.navigate("puzzle_screen/1")
                    }
            )
        }

        // 쿼카 이미지
        Image(
            painter = painterResource(id = R.drawable.home_quokka),
            contentDescription = "Quokka",
            modifier = Modifier
                .size(260.dp)
                .offset(x = 62.dp, y = (440.dp + quokkaOffset.value.dp))
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
                .offset(x = 80.dp, y = (100.dp + spaceshipOffset.value.dp))
                .clickable {
                    navController.navigate("spaceship_shop_screen")
                }
        )

        // 책 이미지
        Image(
            painter = painterResource(id = R.drawable.puzzle_book),
            contentDescription = "Puzzle Book",
            modifier = Modifier
                .size(220.dp)
                .offset(x = 1020.dp, y = (380.dp + bookOffset.value.dp))
                .clickable {
                    navController.navigate("puzzle_book_screen")
                }
        )
    }
}

// 미리보기
@Preview(
    name = "Home Screen Preview",
    showBackground = true,
    widthDp = 1280,
    heightDp = 800
)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}