package com.example.guess_rgb_kotlin.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guess_rgb_kotlin.R
import com.example.guess_rgb_kotlin.constant.PrefKey
import com.example.guess_rgb_kotlin.data.entity.User
import com.example.guess_rgb_kotlin.data.fetchUserStatistic
import com.example.guess_rgb_kotlin.data.updateUserStatistic
import com.example.guess_rgb_kotlin.navigation.NavigationManager
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val auth = FirebaseAuth.getInstance().currentUser

        if (auth != null && auth.email != null) {
            fetchUserStatistic(this, auth.email.toString())
        }

        NavigationManager(supportFragmentManager)
            .openRoot()
    }

    override fun onDestroy() {
        super.onDestroy()
        val preferences = getPreferences(Context.MODE_PRIVATE)

        var winCount = 0
        var looseCount = 0

        if (preferences != null) {
            winCount = preferences.getInt(PrefKey.WIN_SCORE, 0)
            looseCount = preferences.getInt(PrefKey.LOOSE_SCORE, 0)
        }

        val user = User()
        user.email = FirebaseAuth.getInstance().currentUser?.email.toString()
        user.win = winCount.toLong()
        user.loose = looseCount.toLong()
        updateUserStatistic(user)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        NavigationManager(supportFragmentManager)
            .navigateBack(this)
    }
}