package com.example.myadv.ui.screen

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myadv.R
import com.example.myadv.data.PuzzleImage
import com.example.myadv.model.PuzzleViewModel
import com.example.myadv.ui.components.PuzzleTopBar
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import com.example.myadv.ui.components.Bubble
import kotlinx.coroutines.delay

@Composable
fun PuzzleScreen(
    navController: NavController,
    puzzleId: Int,
    puzzleViewModel: PuzzleViewModel
) {
    val puzzlePieces by puzzleViewModel.puzzlePieces.collectAsState()
    val isPuzzleSolved by puzzleViewModel.isPuzzleSolved.collectAsState()
    var showUnclearImage by remember { mutableStateOf(false) }
    val density = LocalDensity.current
    val showBubble = remember { mutableStateOf(false) }

    LaunchedEffect(isPuzzleSolved) {
        if (isPuzzleSolved) {
            delay(500) // 0.5초 대기
            showUnclearImage = true

            delay(500) // 추가 대기
            showBubble.value = true
        }
    }

    val boxSize = 600.dp
    val pieceSize = boxSize / puzzleViewModel.gridSize

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        PuzzleTopBar(navController)

        Box(
            modifier = Modifier
                .size(boxSize)
                .background(Color.White),
        ) {
            // 빈 타일 먼저 그리기
            puzzlePieces.filter { it.isEmpty }.forEach { piece ->
                Box(
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                (piece.currentX * (boxSize.toPx() / puzzleViewModel.gridSize)).toInt(),
                                (piece.currentY * (boxSize.toPx() / puzzleViewModel.gridSize)).toInt()
                            )
                        }
                        .size(pieceSize)
                        .background(Color.White)
                )
            }

            // 나머지 퍼즐 조각 그리기
            puzzlePieces.filter { !it.isEmpty && it.bitmap != null }.forEach { piece ->
                var offsetX by remember { mutableStateOf(0f) }
                var offsetY by remember { mutableStateOf(0f) }
                var isDragging by remember { mutableStateOf(false) }

                Image(
                    bitmap = piece.bitmap!!.asImageBitmap(),
                    contentDescription = "Puzzle Piece ${piece.id}",
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                (piece.currentX * (boxSize.toPx() / puzzleViewModel.gridSize)).toInt() + if (isDragging) offsetX.toInt() else 0,
                                (piece.currentY * (boxSize.toPx() / puzzleViewModel.gridSize)).toInt() + if (isDragging) offsetY.toInt() else 0
                            )
                        }
                        .size(pieceSize)
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragStart = {
                                    if (puzzleViewModel.isValidMove(piece)) {
                                        isDragging = true
                                        offsetX = 0f
                                        offsetY = 0f
                                    }
                                },
                                onDragEnd = {
                                    if (isDragging) {
                                        puzzleViewModel.movePieceToNearestValidPosition(
                                            piece,
                                            offsetX,
                                            offsetY,
                                            boxSize.value * density.density / puzzleViewModel.gridSize
                                        )
                                    }
                                    isDragging = false
                                    offsetX = 0f
                                    offsetY = 0f
                                },
                                onDragCancel = {
                                    isDragging = false
                                    offsetX = 0f
                                    offsetY = 0f
                                },
                                onDrag = { change, dragAmount ->
                                    if (isDragging) {
                                        change.consume()
                                        val newOffsetX = offsetX + dragAmount.x
                                        val newOffsetY = offsetY + dragAmount.y

                                        if (puzzleViewModel.isHorizontalMove(piece)) {
                                            offsetX = newOffsetX.coerceIn(
                                                -pieceSize.toPx(),
                                                pieceSize.toPx()
                                            )
                                        } else if (puzzleViewModel.isVerticalMove(piece)) {
                                            offsetY = newOffsetY.coerceIn(
                                                -pieceSize.toPx(),
                                                pieceSize.toPx()
                                            )
                                        }
                                    }
                                }
                            )
                        }
                )
            }
        }

        if (showUnclearImage) {
            Image(
                painter = painterResource(id = R.drawable.puzzle_image_1_unclear),
                contentDescription = "Unclear Image",
                modifier = Modifier.size(600.dp)
            )
        }

        if (showBubble.value) {
            Bubble(
                bubbleText = "우와! 퍼즐을 다 맞췄어! 이제 마지막 단계야! 이곳을 깨끗하게 만들고 싶다면 내가 내는 퀴즈를 맞혀줘! 퀴즈를 풀면, 더러웠던 환경이 반짝반짝 깨끗하게 바뀔 거야! 준비됐지? 한 번 가볼까?",
                quokkaImageRes = R.drawable.name_quokka,
                onNextClick = { navController.navigate("quiz_screen") },
                quokkaSize = 540.dp,
                quokkaOffsetX = (-30).dp,
                quokkaOffsetY = (-90).dp,
                NameBoxOffsetY = (-240).dp
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
fun PuzzleScreenPreview() {
    val context = LocalContext.current
    val originalBitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.puzzle_image_1_unclear)
    val dummyViewModel = PuzzleViewModel(
        puzzleImage = PuzzleImage(
            id = 1,
            unclearImageRes = R.drawable.puzzle_image_1_unclear,
            clearImageRes = R.drawable.puzzle_image_1_clear,
            size = 3
        ),
        originalBitmap = originalBitmap
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PuzzleScreen(
                navController = rememberNavController(),
                puzzleViewModel = dummyViewModel,
                puzzleId = 1
            )
        }
    }
}
