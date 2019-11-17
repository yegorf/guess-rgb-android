package com.example.guess_rgb_kotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import butterknife.ButterKnife
import com.example.guess_rgb_kotlin.R
import com.example.guess_rgb_kotlin.fragment.GameFragment
import com.example.guess_rgb_kotlin.navigation.NavigationManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.fragment_container, GameFragment.getInstance())
            .commit()
    }

    override fun onBackPressed() {
        NavigationManager(supportFragmentManager)
            .navigateBack(this)
    }
}

