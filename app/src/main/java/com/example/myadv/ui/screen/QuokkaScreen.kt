package com.example.myadv.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.runBlocking
import com.example.myadv.R
import com.example.myadv.data.getQuokkaName
import com.example.myadv.ui.components.TopBar

@Composable
fun QuokkaScreen(navController: NavController) {
    val context = LocalContext.current
    val quokkaName = remember { mutableStateOf("???") } // 쿼카 이름 기본값 설정

    // 쿼카 이름을 저장된 데이터에서 불러오는 로직
    LaunchedEffect(Unit) {
        val savedName = runBlocking { context.getQuokkaName() }
        quokkaName.value = savedName
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

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 쿼카 이미지와 하단 대화 상자
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart // 하단 왼쪽 정렬
        ) {
            // 쿼카 이미지
            Image(
                painter = painterResource(id = R.drawable.home_quokka), // 쿼카 이미지 리소스
                contentDescription = "쿼카",
                modifier = Modifier
                    .size(540.dp) // 이미지 크기 설정
                    .offset(x = (-70).dp, y = (-100).dp) // 이미지 위치 조정
            )
        }

        // 하단 대화 상자
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter) // 하단 중앙 정렬
                .padding(horizontal = 24.dp, vertical = 26.dp)
                .clip(RoundedCornerShape(16.dp)) // 둥근 모서리 처리
                .background(Color(0xE66A6A6A)) // 반투명 배경색
                .padding(vertical = 64.dp, horizontal = 52.dp) // 내부 여백
        ) {
            // 대화 내용 텍스트
            Text(
                text = "지구의 오른쪽에 있는 책은 맞춘 퍼즐을 확인할 수 있는 도감이고, 왼쪽에 있는 우주선은 상점이야. 내 이름을 바꾸고 싶으면 '변경하기' 를 눌러줘. 지구가 푸른 빛을 되찾는 날까지 같이 힘내서 퍼즐을 맞춰보자! 우리 둘이서 함께라면 할 수 있어, 그치?", // 텍스트 정의
                style = TextStyle(color = Color.White, fontSize = 28.sp), // 텍스트 스타일
                lineHeight = 58.sp, // 줄 간격
                modifier = Modifier
                    .fillMaxWidth() // 텍스트가 상자 전체를 채우도록 설정
                    .padding(top = 16.dp) // 텍스트 위쪽 여백
            )
        }

        // 쿼카 이름 상자
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart) // 하단 왼쪽 정렬
                .offset(x = 80.dp, y = (-300).dp) // 대화 상자 높이에 따라 위치 조정
                .width(220.dp) // 이름 상자 너비 설정
                .clip(RoundedCornerShape(12.dp)) // 둥근 모서리 처리
                .background(Color.LightGray) // 밝은 회색 배경
                .padding(10.dp) // 내부 여백
        ) {
            Text(
                text = quokkaName.value, // 저장된 쿼카 이름 표시
                style = TextStyle(
                    fontSize = 28.sp, // 글자 크기
                    fontWeight = FontWeight.Bold, // 굵은 글씨
                    color = Color.Black // 글자 색상
                ),
                modifier = Modifier.align(Alignment.Center) // 중앙 정렬
            )
        }

        // "맞아", "아니야" 버튼
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 28.dp, bottom = 28.dp) // 화면 오른쪽 및 하단 여백
                .offset(x = (-20).dp, y = (-20).dp)
                .align(Alignment.BottomEnd), // 화면 오른쪽 하단 정렬
            horizontalArrangement = Arrangement.End // 버튼이 오른쪽에 정렬되도록 설정
        ) {
            ChooseButton(text = "돌아가기") {
                navController.navigate("home_screen") // "돌아가기" 클릭 시 home_screen으로 이동
            }
            Spacer(modifier = Modifier.width(32.dp)) // 버튼 간의 간격
            ChooseButton(text = "변경하기") {
                navController.navigate("name_change_screen") // "변경하기" 클릭 시 name_change_screen으로 이동
            }
        }
    }
}

// "맞아", "아니야" 버튼 구성
@Composable
fun ChooseButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp)) // 둥근 모서리 처리
            .background(Color(0xFF0B2F9F)) // 파란색 배경
            .padding(vertical = 12.dp, horizontal = 60.dp) // 버튼 내부 여백
            .clickable { onClick() } // 클릭 이벤트 추가
    ) {
        Text(
            text = text, // 버튼에 표시될 텍스트
            style = TextStyle(
                color = Color.White, // 흰색 텍스트
                fontSize = 24.sp, // 글자 크기
                fontWeight = FontWeight.Bold // 굵은 글씨
            ),
            modifier = Modifier.align(Alignment.Center) // 중앙 정렬
        )
    }
}

// 프리뷰
@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
fun QuokkaScreenPreview() {
    val navController = rememberNavController() // 네비게이션 컨트롤러 생성
    QuokkaScreen(navController = navController)
}
