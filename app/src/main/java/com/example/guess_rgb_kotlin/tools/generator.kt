package com.example.guess_rgb_kotlin.tools

import com.example.guess_rgb_kotlin.entity.Color

private val MAX = 255
private val MAX_PLATES = 8

fun generateRGB(): Color {
    val r = (Math.random() * MAX).toInt()
    val g = (Math.random() * MAX).toInt()
    val b = (Math.random() * MAX).toInt()
    return Color(r, g, b)
}

fun getPlateNum(): Int {
    return (Math.random() * MAX_PLATES).toInt()
}