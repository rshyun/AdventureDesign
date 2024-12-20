package com.example.myadv.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.example.myadv.R
import com.example.myadv.ui.components.TopBar

@Composable
fun PuzzleBookScreen(navController: NavController) {
    val stages = listOf("1단계", "2단계", "3단계", "4단계", "5단계")
    val images = listOf(
        R.drawable.puzzle_image_1_clear,
        R.drawable.puzzle_image_2_clear,
        R.drawable.puzzle_image_3_clear,
        R.drawable.puzzle_image_4_clear,
        R.drawable.puzzle_image_5_clear,
        R.drawable.puzzle_image_6_clear
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // TopBar 사용
        TopBar(navController)

        // 화면 콘텐츠
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 지구본
            Image(
                painter = painterResource(id = R.drawable.earth),
                contentDescription = "Earth",
                modifier = Modifier
                    .size(160.dp)
                    .clickable { navController.navigate("home_screen") } // home_screen으로 이동
            )

            // 스테이지 탭
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                stages.forEachIndexed { index, stage ->
                    Box(
                        modifier = Modifier
                            .clip(
                                if (index == 1) RoundedCornerShape(16.dp)
                                else RoundedCornerShape(12.dp)
                            )
                            .background(
                                if (index == 1) Color.White else Color.LightGray.copy(alpha = 0.5f)
                            )
                            .padding(vertical = 8.dp, horizontal = 12.dp)
                    ) {
                        Text(
                            text = stage,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (index == 1) Color.Black else Color.Gray
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 스테이지 이미지
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(images) { image ->
                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .padding(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = image),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 쿼카
            Image(
                painter = painterResource(id = R.drawable.home_quokka),
                contentDescription = "Quokka",
                modifier = Modifier.size(180.dp)
            )
        }
    }
}
