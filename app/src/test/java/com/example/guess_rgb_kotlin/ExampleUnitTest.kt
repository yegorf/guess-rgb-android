package com.example.guess_rgb_kotlin

import com.example.guess_rgb_kotlin.tools.calculateWin
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        print(calculateWin(3, 7))
    }
}
