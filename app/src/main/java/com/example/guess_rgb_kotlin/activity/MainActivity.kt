package com.example.guess_rgb_kotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guess_rgb_kotlin.R
import com.example.guess_rgb_kotlin.navigation.NavigationManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationManager(supportFragmentManager)
            .openRoot()
    }
}