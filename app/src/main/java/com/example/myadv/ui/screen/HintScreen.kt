package com.example.myadv.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myadv.R
import com.example.myadv.ui.components.PuzzleTopBar

@Composable
fun HintScreen(navController: NavController) {
    val context = LocalContext.current // LocalContext를 통해 현재 Context 가져오기

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
        // 팝업 창
        Box(
            modifier = Modifier
                .width(720.dp)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White.copy(alpha = 0.9f))
                .padding(vertical = 40.dp, horizontal = 28.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // 닫기 아이콘
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_x), // 닫기 아이콘 리소스
                        contentDescription = "Close",
                        modifier = Modifier
                            .size(28.dp)
                            .clickable {
                                navController.popBackStack() // 닫기 동작: 뒤로가기
                            }
                    )
                }

                // 팝업 텍스트
                Text(
                    text = "광고를 보면 내가 힌트를 줄게! 힌트를 받고 싶으면 광고보기 버튼을 눌러줘~ 우리가 더 빨리 지구를 구할 수 있을 거야!",
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        lineHeight = 40.sp, // 텍스트 줄 간격
                        textAlign = TextAlign.Center // 텍스트 중앙 정렬
                    ),
                    modifier = Modifier
                        .padding(horizontal = 28.dp, vertical = 18.dp) // 좌우 및 상하 간격 추가
                        .align(Alignment.CenterHorizontally) // 텍스트 중앙 정렬
                )

                // 텍스트와 버튼 사이의 간격
                Spacer(modifier = Modifier.height(20.dp)) // 원하는 높이로 간격 추가

                // 광고보기 버튼
                Button(
                    onClick = {
                        // YouTube 링크 열기
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/cCW6eKySZjk?si=of94AnaHxE7jJl3Z"))
                        context.startActivity(intent) // Intent를 사용해 브라우저 열기
                    },
                    modifier = Modifier
                        .width(208.dp)
                        .height(54.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0B2F9F)
                    )
                ) {
                    Text(
                        text = "광고보기",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    widthDp = 1280,
    heightDp = 800
)
fun HintScreenPreview() {
    val navController = rememberNavController()
    HintScreen(navController = navController)
}