package com.example.guess_rgb_kotlin.data.entity

import com.example.guess_rgb_kotlin.tools.calculateLoose
import com.example.guess_rgb_kotlin.tools.calculateWin
import com.google.gson.annotations.SerializedName
import kotlin.math.round

class User {
    //todo refactor
    @JvmField
    var email: String = "unknown"
    @SerializedName("win")
    @JvmField
    var win: Long = 0
    @SerializedName("loose")
    @JvmField
    var loose: Long = 0

    //todo remove
    var winPercent = 0
    var loosePercent = 0

    fun setPercents() {
        winPercent = round(calculateWin(win.toInt(), loose.toInt()).toFloat()).toInt()
        loosePercent = round(calculateLoose(win.toInt(), loose.toInt()).toFloat()).toInt()
    }
}