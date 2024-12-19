package com.example.myadv.data

import com.example.myadv.R

data class PuzzleImage(
    val id: Int,              // 퍼즐 고유 ID
    val unclearImageRes: Int, // 클리어 전 이미지 리소스
    val clearImageRes: Int,   // 클리어 후 이미지 리소스
    val size: Int             // 퍼즐 크기 (NxN 퍼즐)
)

object PuzzleImageData {
    val puzzles = listOf(
        PuzzleImage(
            id = 1,
            unclearImageRes = R.drawable.puzzle_image_1_unclear, // 클리어 전 이미지
            clearImageRes = R.drawable.puzzle_image_1_clear,    // 클리어 후 이미지
            size = 3  // 4x4 퍼즐
        )
        // 추가 퍼즐 데이터는 여기에 추가
    )

    /**
     * 퍼즐 ID를 기반으로 해당 퍼즐 정보를 반환합니다.
     */
    fun getPuzzleById(puzzleId: Int): PuzzleImage =
        puzzles.first { it.id == puzzleId }
}