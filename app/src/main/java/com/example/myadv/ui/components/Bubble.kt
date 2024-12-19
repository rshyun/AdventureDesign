package com.example.myadv.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import com.example.myadv.R
import com.example.myadv.data.getQuokkaName
import kotlinx.coroutines.runBlocking

@Composable
fun Bubble(
    bubbleText: String,
    quokkaImageRes: Int,
    onNextClick: () -> Unit,
    quokkaSize: Dp = 540.dp, // 쿼카 이미지 크기
    quokkaOffsetX: Dp = (-28).dp, // 쿼카 이미지 X축 위치
    quokkaOffsetY: Dp = (-120).dp, // 쿼카 이미지 Y축 위치
    NameBoxOffsetY: Dp = (-60).dp // 쿼카 이름 상자의 Y축 위치
) {
    val context = LocalContext.current
    val quokkaName = remember { mutableStateOf("???") }

    var dialogBoxHeight by remember { mutableStateOf(0) } // 텍스트 박스 높이 저장

    LaunchedEffect(Unit) {
        val savedName = runBlocking { context.getQuokkaName() }
        quokkaName.value = savedName
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 중앙 하단의 대화 상자와 쿼카 이미지
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart // 하단 왼쪽 정렬
        ) {
            // 쿼카 이미지
            Image(
                painter = painterResource(id = quokkaImageRes), // 쿼카 이미지 리소스
                contentDescription = "쿼카", // 이미지 설명
                modifier = Modifier
                    .size(quokkaSize) // 쿼카 이미지 크기 설정
                    .offset(x = quokkaOffsetX, y = quokkaOffsetY) // 이미지 위치 조정
            )
        }

        // 하단 대화 상자
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter) // 하단 중앙 정렬
                .padding(horizontal = 24.dp, vertical = 26.dp)
                .clip(RoundedCornerShape(16.dp)) // 둥근 모서리 처리
                .background(Color(0xE66A6A6A)) // 반투명 배경색 설정
                .padding(vertical = 64.dp, horizontal = 52.dp) // 내부 여백 설정
                .onGloballyPositioned { coordinates ->
                    dialogBoxHeight = coordinates.size.height // 텍스트 박스 높이 측정
                }
        ) {
            Text(
                text = bubbleText,
                style = TextStyle(color = Color.White, fontSize = 28.sp), // 텍스트 스타일 설정
                lineHeight = 58.sp, // 텍스트 줄 간격 설정
                modifier = Modifier
                    .fillMaxWidth() // 텍스트가 전체 너비를 차지하도록 설정
                    .padding(top = 16.dp) // 텍스트 위쪽 여백 설정
            )
        }

        // 쿼카 이름 상자
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart) // 하단 왼쪽 정렬
                .offset(x = 100.dp, y = NameBoxOffsetY) // 외부에서 입력받은 Y축 오프셋 적용
                .width(220.dp) // 상자 너비 설정
                .clip(RoundedCornerShape(12.dp)) // 둥근 모서리 처리
                .background(Color.LightGray) // 상자 배경색 설정
                .padding(10.dp) // 내부 여백 설정
        ) {
            Text(
                text = quokkaName.value, // 저장된 쿼카 이름 표시
                style = TextStyle(
                    fontSize = 28.sp, // 글자 크기
                    fontWeight = FontWeight.Bold, // 글자 굵기
                    color = Color.Black // 글자 색상
                ),
                modifier = Modifier.align(Alignment.Center) // 텍스트 중앙 정렬
            )
        }

        // Next 아이콘, 대화 상자 내 우측 최하단
        Icon(
            painter = painterResource(id = R.drawable.ic_next), // Next 아이콘 리소스
            contentDescription = "Next", // 아이콘 설명
            tint = Color.White, // 아이콘 색상
            modifier = Modifier
                .size(80.dp) // 아이콘 크기
                .align(Alignment.BottomEnd) // 우측 하단 정렬
                .padding(end = 40.dp, bottom = 42.dp) // 오른쪽 및 아래쪽 여백 추가
                .clickable { onNextClick() }
        )
    }
}

// 프리뷰
@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
fun BubblePreview() {
    Bubble(
        bubbleText = "오른쪽에 있는 책 보여? 저건 친구가 퍼즐을 다 맞출 때마다 맞춘 퍼즐들을 확인할 수 있는 도감이야! 도감의 모든 퍼즐을 클리어해보자! 아! 그리고 왼쪽에 있는 우주선은 아이템을 살 수 있는 상점이야. 단계를 클리어할 때마다 주기도 하고 친구가 살 수도 있어! 아이템으로 날 꾸며줄래?",
        quokkaImageRes = R.drawable.explain_quokka, // 예시 쿼카 이미지
        onNextClick = {}, // 클릭 시 동작
        quokkaSize = 540.dp, // 쿼카 이미지 크기 설정
        quokkaOffsetX = (-45).dp, // 쿼카 이미지 X축 위치 조정
        quokkaOffsetY = (-120).dp, // 쿼카 이미지 Y축 위치 조정
        NameBoxOffsetY = (-300).dp // 쿼카 이름 상자의 Y축 위치 조정
    )
}