package com.example.guess_rgb_kotlin.tools

import java.text.DecimalFormat

fun calculateWin(win: Int, loose: Int): String {
    val percent = ((win / (win + loose)) * 100).toDouble()
    return round(percent)
}

fun calculateLoose(win: Int, loose: Int): String {
    val percent = ((loose / (win + loose)) * 100).toDouble()
    return round(percent)
}

private fun round(number: Double): String {
    val format = DecimalFormat("##.00")
    return format.format(number)
}