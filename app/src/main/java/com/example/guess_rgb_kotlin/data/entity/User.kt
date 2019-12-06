package com.example.guess_rgb_kotlin.data.entity

import com.google.gson.annotations.SerializedName

//data class User(
//    var email: String,
//    var win: Int,
//    var loose: Int
//)

class User {
    @JvmField var email: String = "unknown"
    @SerializedName("win")
    @JvmField var win: Long = 0
    @SerializedName("loose")
    @JvmField var loose: Long = 0

    fun getEmail() = email
    fun getWin() = win
    fun getLoose() = loose
}