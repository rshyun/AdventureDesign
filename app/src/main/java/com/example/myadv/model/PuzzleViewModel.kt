package com.example.myadv.model

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.example.myadv.data.PuzzleImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PuzzleViewModel(
    private val puzzleImage: PuzzleImage,
    private val originalBitmap: Bitmap
) : ViewModel() {

    private val _puzzlePieces = MutableStateFlow<List<PuzzlePiece>>(emptyList())
    val puzzlePieces: StateFlow<List<PuzzlePiece>> = _puzzlePieces.asStateFlow()

    private val _isPuzzleSolved = MutableStateFlow(false)
    val isPuzzleSolved: StateFlow<Boolean> = _isPuzzleSolved.asStateFlow()

    val gridSize = puzzleImage.size

    private lateinit var emptyPiece: PuzzlePiece

    init {
        createPuzzlePieces()
    }

    private fun createPuzzlePieces() {
        val pieces = mutableListOf<PuzzlePiece>()
        val pieceWidth = originalBitmap.width / gridSize
        val pieceHeight = originalBitmap.height / gridSize

        // 모든 조각 생성 (빈 조각 포함)
        for (row in 0 until gridSize) {
            for (col in 0 until gridSize) {
                val pieceId = row * gridSize + col
                val currentX = col.toFloat()
                val currentY = row.toFloat()

                val piece = if (pieceId == gridSize * gridSize - 1) {
                    // 빈 조각 생성
                    PuzzlePiece(
                        id = pieceId,
                        bitmap = null,
                        currentX = currentX,
                        currentY = currentY,
                        originalX = currentX,
                        originalY = currentY,
                        isEmpty = true
                    ).also { emptyPiece = it }
                } else {
                    // 일반 조각 생성
                    val pieceBitmap = Bitmap.createBitmap(
                        originalBitmap,
                        col * pieceWidth,
                        row * pieceHeight,
                        pieceWidth,
                        pieceHeight
                    )
                    PuzzlePiece(
                        id = pieceId,
                        bitmap = pieceBitmap,
                        currentX = currentX,
                        currentY = currentY,
                        originalX = currentX,
                        originalY = currentY
                    )
                }
                pieces.add(piece)
            }
        }

        // 해결 가능한 상태로 퍼즐 섞기
        createSolvableConfiguration(pieces)
        _puzzlePieces.value = pieces
    }

    private fun createSolvableConfiguration(pieces: MutableList<PuzzlePiece>) {
        val maxMoves = 100  // 충분한 섞임을 위한 이동 횟수
        val moves = listOf(-1, 1, -gridSize, gridSize)  // 좌, 우, 상, 하 이동

        repeat(maxMoves) {
            // 현재 빈 칸의 위치에서 가능한 이동 방향 찾기
            val validMoves = mutableListOf<Int>()
            val emptyIndex = pieces.indexOf(emptyPiece)
            val emptyRow = emptyIndex / gridSize
            val emptyCol = emptyIndex % gridSize

            // 각 방향으로의 이동이 가능한지 확인
            moves.forEach { move ->
                val newIndex = emptyIndex + move
                val newRow = newIndex / gridSize
                val newCol = newIndex % gridSize

                // 이동이 보드 안에 있고 한 줄에서 다른 줄로 건너뛰지 않는지 확인
                if (newIndex in 0 until pieces.size &&
                    (move == gridSize || move == -gridSize || // 상하 이동
                            (move == 1 && emptyCol < gridSize - 1) || // 우측 이동
                            (move == -1 && emptyCol > 0))) { // 좌측 이동
                    validMoves.add(move)
                }
            }

            // 가능한 이동 중 하나를 무작위로 선택하여 수행
            if (validMoves.isNotEmpty()) {
                val randomMove = validMoves.random()
                val targetIndex = emptyIndex + randomMove

                // 선택된 방향으로 조각 교환
                pieces[emptyIndex] = pieces[targetIndex].also {
                    it.currentX = emptyPiece.currentX
                    it.currentY = emptyPiece.currentY
                }
                pieces[targetIndex] = emptyPiece.also {
                    it.currentX = (targetIndex % gridSize).toFloat()
                    it.currentY = (targetIndex / gridSize).toFloat()
                }
            }
        }
    }

    fun isValidMove(piece: PuzzlePiece): Boolean {
        return (Math.abs(piece.currentX - emptyPiece.currentX) == 1f && piece.currentY == emptyPiece.currentY) ||
                (Math.abs(piece.currentY - emptyPiece.currentY) == 1f && piece.currentX == emptyPiece.currentX)
    }

    fun isHorizontalMove(piece: PuzzlePiece): Boolean {
        return piece.currentY == emptyPiece.currentY &&
                Math.abs(piece.currentX - emptyPiece.currentX) == 1f
    }

    fun isVerticalMove(piece: PuzzlePiece): Boolean {
        return piece.currentX == emptyPiece.currentX &&
                Math.abs(piece.currentY - emptyPiece.currentY) == 1f
    }

    fun movePieceToNearestValidPosition(piece: PuzzlePiece, offsetX: Float, offsetY: Float, pieceSize: Float) {
        if (!isValidMove(piece)) return // 빈 타일과의 유효한 이동이 아니면 리턴

        // 이동 방향이 빈 타일 쪽인지 확인
        val moveTowardsEmpty = when {
            isHorizontalMove(piece) -> {
                if (piece.currentX < emptyPiece.currentX) {
                    offsetX > pieceSize / 3 // 오른쪽으로 이동
                } else {
                    offsetX < -pieceSize / 3 // 왼쪽으로 이동
                }
            }
            isVerticalMove(piece) -> {
                if (piece.currentY < emptyPiece.currentY) {
                    offsetY > pieceSize / 3 // 아래로 이동
                } else {
                    offsetY < -pieceSize / 3 // 위로 이동
                }
            }
            else -> false // 수평 또는 수직 이동이 아니라면 이동 불가
        }

        if (moveTowardsEmpty) {
            // 빈 타일 쪽으로 이동이 확인된 경우 위치 교환
            piece.swapPositionWith(emptyPiece)
            _puzzlePieces.value = _puzzlePieces.value.toList() // StateFlow 업데이트
            checkPuzzleSolved()
        }
    }

    private fun checkPuzzleSolved() {
        val solved = _puzzlePieces.value.all { it.isInOriginalPosition() }
        _isPuzzleSolved.value = solved
    }
}