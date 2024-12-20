package com.example.myadv.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import com.example.myadv.R
import com.example.myadv.ui.components.TopBar

@Composable
fun SpaceshipShopScreen(navController: NavController) {
    val items = listOf(
        R.drawable.ic_cap, // 모자
        R.drawable.ic_shirts, // 셔츠
        R.drawable.ic_shoes, // 신발
        R.drawable.ic_deck // 장식
    )

    val caps = listOf(
        R.drawable.ic_cap1,
        R.drawable.ic_cap2,
        R.drawable.ic_cap3,
        R.drawable.ic_cap4,
        R.drawable.ic_cap5,
        R.drawable.ic_cap6,
        R.drawable.ic_cap7,
        R.drawable.ic_cap8,
        R.drawable.ic_cap9
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // TopBar 사용
        TopBar(navController)

        // 흰색 바탕 위에 뒤로가기 아이콘과 문구
        Column(
            modifier = Modifier
                .size(width = 1100.dp, height = 680.dp) // 흰색 바탕 높이 늘림
                .align(Alignment.Center) // 화면 중앙에 배치
                .clip(RoundedCornerShape(16.dp)) // 둥근 모서리
                .background(Color.White) // 흰색 배경
                .padding(24.dp) // 내부 여백 설정
        ) {
            // 상단 바
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 뒤로가기 아이콘
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { navController.navigate("home_screen") } // home_screen으로 이동
                )
                Spacer(modifier = Modifier.width(16.dp))
                // 우주선 상점 문구
                Text(
                    text = "우주선 상점",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            }

            // 내용 추가 (아이템 카테고리와 목록 등)
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // 왼쪽 콘텐츠
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom // 쿼카를 아래로 정렬
                ) {
                    // 쿼카 이미지
                    Image(
                        painter = painterResource(id = R.drawable.home_quokka),
                        contentDescription = "쿼카",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(bottom = 24.dp) // 아래쪽으로 추가 패딩 설정
                    )
                }

                // 오른쪽 콘텐츠: 모자 목록
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3), // 3열로 고정
                    modifier = Modifier
                        .weight(2f)
                        .padding(horizontal = 16.dp) // 충분한 패딩 확보
                        .offset(y = (-30).dp), // 조금 위로 이동
                    verticalArrangement = Arrangement.spacedBy(24.dp), // 세로 간격 일정
                    horizontalArrangement = Arrangement.spacedBy(24.dp) // 가로 간격 일정
                ) {
                    items(caps) { cap ->
                        Box(
                            modifier = Modifier
                                .size(140.dp) // 크기 설정
                                .aspectRatio(1f) // 정사각형 유지
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.Gray.copy(alpha = 0.5f))
                                .padding(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = cap),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    name = "Spaceship Shop Screen Preview",
    showBackground = true,
    widthDp = 1280,
    heightDp = 800
)
@Composable
fun SpaceshipShopScreenPreview() {
    val navController = rememberNavController()
    SpaceshipShopScreen(navController)
}
