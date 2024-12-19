package com.example.myadv.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myadv.ui.components.PuzzleTopBar

@Composable
fun PuzzleSettingScreen(navController: NavController) {
    // 상태 관리: 효과음과 알림수신
    var isSoundOn by remember { mutableStateOf(false) }
    var isNotificationOn by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 상단 바
        PuzzleTopBar(navController)

        Box(
            modifier = Modifier
                .fillMaxSize() // 화면 전체 크기
                .background(Color.DarkGray.copy(alpha = 0.75f)) // DarkGray 75% 투명도
        )

        // 중앙 하얀색 반투명 직사각형 상자
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center // 중앙 배치
        ) {
            Column(
                modifier = Modifier
                    .width(440.dp) // 가로 길이 설정
                    .height(600.dp) // 세로 길이 설정
                    .clip(RoundedCornerShape(16.dp)) // 둥근 모서리 처리
                    .background(Color.White.copy(alpha = 0.9f)) // 하얀색 반투명 배경
                    .padding(28.dp), // 내부 여백
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // 제목
                Text(
                    text = "설정",
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 효과음 상태 텍스트 (클릭 가능)
                Text(
                    text = if (isSoundOn) "효과음 ON" else "효과음 OFF",
                    modifier = Modifier.clickable {
                        isSoundOn = !isSoundOn // 상태 토글
                    },
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )

                // 알림수신 상태 텍스트 (클릭 가능)
                Text(
                    text = if (isNotificationOn) "알림수신 ON" else "알림수신 OFF",
                    modifier = Modifier.clickable {
                        isNotificationOn = !isNotificationOn // 상태 토글
                    },
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // 구분선
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth() // 구분선을 가로로 꽉 채움
                )

                // 홈화면 버튼 (클릭 가능)
                Text(
                    text = "홈화면",
                    modifier = Modifier.clickable {
                        navController.navigate("home_screen")
                    },
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )

                // 공지사항 버튼 (클릭 가능)
                Text(
                    text = "공지사항",
                    modifier = Modifier.clickable {
                        // 공지사항 클릭 동작
                    },
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )

                // 구분선
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth() // 구분선을 가로로 꽉 채움
                )

                Spacer(modifier = Modifier.height(20.dp))

                // 버전 정보
                Text(
                    text = "현재 버전 : 1.1.1",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )

                Text(
                    text = "권장 버전 : 1.1.2",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                // 확인 버튼
                Button(
                    onClick = {
                        navController.popBackStack() // 뒤로가기 역할
                    },
                    modifier = Modifier
                        .width(160.dp)
                        .height(52.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0B2F9F)
                    )
                ) {
                    Text(
                        text = "확인",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(
    name = "Puzzle Setting Screen Preview",
    showBackground = true,
    widthDp = 1280,
    heightDp = 800
)
@Composable
fun PuzzleSettingScreenPreview() {
    val navController = rememberNavController() // 미리보기용 NavController
    PuzzleSettingScreen(navController = navController)
}