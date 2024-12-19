package com.example.myadv.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myadv.R

@Composable
fun TopBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // 배경 이미지 (space_background)
        Image(
            painter = painterResource(id = R.drawable.space_background), // 배경 이미지 리소스
            contentDescription = "Space Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // 이미지를 화면 전체 크기에 맞게 조정
        )

        // 상단 바
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 24.dp, top = 24.dp),
            horizontalArrangement = Arrangement.End, // 오른쪽 정렬
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_settings), // 설정 아이콘 리소스
                contentDescription = "Settings", // 아이콘 설명
                tint = Color.White, // 아이콘 색상
                modifier = Modifier
                    .size(30.dp) // 아이콘 크기
                    .clickable {
                        navController.navigate("home_setting_screen") // home_setting_screen으로 전환
                    }
            )
        }
    }
}

// 프리뷰
@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
fun TopBarPreview() {
    val navController = rememberNavController() // 미리보기용 NavController
    TopBar(navController = navController)
}