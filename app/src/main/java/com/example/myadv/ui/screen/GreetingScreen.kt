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
fun GreetingScreen(navController: NavController) {
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
                modifier = Modifier.size(700.dp), // 크기 설정
                contentScale = ContentScale.Fit // 비율 유지하며 크기 맞춤
            )
        }

        // Bubble 사용
        Bubble(
            bubbleText = "안녕! 네가 지구에서 온 영웅이구나. 나는 우주를 떠돌아다니는 쿼카야! \n나에게 이름을 지어줄래? 그러면 우리는 친구가 될 수 있어! 함께 우주를 여행하자!",
            quokkaImageRes = R.drawable.name_quokka, // 쿼카 이미지 리소스
            onNextClick = { navController.navigate("naming_screen") }, // Next 버튼 클릭 시 동작
            quokkaSize = 540.dp, // 쿼카 이미지 크기
            quokkaOffsetX = (-30).dp, // 쿼카 X축 위치
            quokkaOffsetY = (-90).dp, // 쿼카 Y축 위치
            NameBoxOffsetY = (-240).dp // 쿼카 이름 상자의 Y축 위치 조정
        )
    }
}

@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
fun GreetingScreenPreview() {
    val navController = rememberNavController() // 미리보기용 NavController 생성
    GreetingScreen(navController = navController) // 미리보기 설정
}
