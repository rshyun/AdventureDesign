package com.example.myadv.model

import android.graphics.Bitmap

data class PuzzlePiece(
    val id: Int,
    val bitmap: Bitmap?, // 빈 타일일 경우 null
    var currentX: Float,
    var currentY: Float,
    val originalX: Float,
    val originalY: Float,
    val isEmpty: Boolean = false // 빈 타일 여부
) {
    fun isInOriginalPosition(): Boolean {
        return currentX == originalX && currentY == originalY
    }

    fun swapPositionWith(other: PuzzlePiece) {
        val tempX = currentX
        val tempY = currentY
        currentX = other.currentX
        currentY = other.currentY
        other.currentX = tempX
        other.currentY = tempY
    }
}