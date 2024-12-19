package com.example.myadv.ui.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myadv.R
import com.example.myadv.ui.components.Bubble
import com.example.myadv.ui.components.PuzzleTopBar
import kotlinx.coroutines.delay

@Composable
fun ClearScreen(navController: NavController) {
    var showUnclearImage by remember { mutableStateOf(true) }
    var showBubble by remember { mutableStateOf(false) }
    val alphaAnimation = animateFloatAsState(
        targetValue = if (showUnclearImage) 1f else 0f,
        animationSpec = tween(durationMillis = 3000), label = ""
    )

    LaunchedEffect(Unit) {
        // 10초 동안 unclear 이미지 표시
        delay(1000)
        showUnclearImage = false

        // 애니메이션과 함께 clear 이미지로 전환 후 10초 대기
        delay(1000)

        // Bubble 컴포넌트 표시
        showBubble = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // PuzzleTopBar
        PuzzleTopBar(navController)

        // 퍼즐 이미지
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center // 이미지 중앙 정렬
        ) {
            if (showUnclearImage) {
                Image(
                    painter = painterResource(id = R.drawable.puzzle_image_1_unclear),
                    contentDescription = "Unclear Image",
                    modifier = Modifier
                        .size(600.dp)
                        .graphicsLayer(alpha = alphaAnimation.value)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.puzzle_image_1_clear),
                    contentDescription = "Clear Image",
                    modifier = Modifier.size(600.dp)
                )
            }
        }

        // Bubble 컴포넌트
        if (showBubble) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter // Bubble은 화면 하단 중앙
            ) {
                Bubble(
                    bubbleText = "이제 봐! 너의 노력 덕분에 이곳이 완전히 달라졌어! 쓰레기로 가득했던 풍경이 이렇게 맑고 깨끗하게 변했어. 정말 감동적이지 않아? 너처럼 열심히 도와주는 친구가 있기에 지구도 다시 미소를 되찾고 있어. 앞으로도 지구를 지키는 멋진 친구로 계속 활약해줘! 내가 정말 자랑스러워!",
                    quokkaImageRes = R.drawable.love_quokka,
                    onNextClick = { navController.navigate("home_screen") },
                    quokkaSize = 540.dp,
                    quokkaOffsetX = (-50).dp,
                    quokkaOffsetY = (-120).dp,
                    NameBoxOffsetY = (-300).dp
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
fun ClearScreenPreview() {
    val navController = rememberNavController()
    ClearScreen(navController)
}