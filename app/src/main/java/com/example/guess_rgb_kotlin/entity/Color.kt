package com.example.guess_rgb_kotlin.entity

data class Color(var r: Int, var g: Int, var b: Int) {
    override fun toString(): String {
        return "RGB($r,$g,$b)"
    }
}