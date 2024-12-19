package com.example.myadv.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myadv.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PuzzleTopBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // 상단 바
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp), // 좌우 패딩과 상단 패딩
            horizontalArrangement = Arrangement.SpaceBetween, // 왼쪽 끝과 오른쪽 끝에 배치
            verticalAlignment = Alignment.CenterVertically // 세로 정렬
        ) {
            // 왼쪽 끝에 힌트 아이콘 (Image 사용)
            Image(
                painter = painterResource(id = R.drawable.ic_hint), // 힌트 아이콘 리소스
                contentDescription = "Hint", // 아이콘 설명
                modifier = Modifier
                    .size(40.dp) // 아이콘 크기
                    .clickable {
                        navController.navigate("hint_screen") // hint_screen으로 전환
                    }
            )

            // 오른쪽 끝에 설정 아이콘 (Icon 사용)
            Icon(
                painter = painterResource(id = R.drawable.ic_settings_b), // 설정 아이콘 리소스
                contentDescription = "Settings", // 아이콘 설명
                modifier = Modifier
                    .size(30.dp) // 아이콘 크기
                    .clickable {
                        navController.navigate("puzzle_setting_screen") // puzzle_setting_screen으로 전환
                    }
            )
        }
    }
}

// 프리뷰
@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
fun PuzzleTopBarPreview() {
    val navController = rememberNavController()
    PuzzleTopBar(navController)
}