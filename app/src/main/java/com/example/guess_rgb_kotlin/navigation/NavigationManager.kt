package com.example.guess_rgb_kotlin.navigation

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.guess_rgb_kotlin.R
import com.example.guess_rgb_kotlin.fragment.*
import com.google.firebase.auth.FirebaseAuth

class NavigationManager(private val fragmentManager: FragmentManager) {

    companion object {
        const val SCREEN_GAME = "SCREEN_GAME"
        const val SCREEN_STATISTICS = "SCREEN_STATISTICS"
        const val SCREEN_INFO = "SCREEN_INFO"
        const val SCREEN_RGB = "SCREEN_RGB"
        const val SCREEN_SETTINGS = "SCREEN_SETTINGS"
        const val SCREEN_LOGIN = "SCREEN_LOGIN"
        var currentScreen = SCREEN_GAME
    }

    fun openRoot() {
        val fragment = getFragmentForScreen(SCREEN_GAME)
        if (fragment != null) {
            open(fragment, SCREEN_GAME)
            currentScreen = SCREEN_GAME
        }
    }

    fun openFragment(screenName: String) {
        if (currentScreen != screenName) {
            val fragment = getFragmentForScreen(screenName)
            if (fragment != null) {
                open(fragment, screenName)
                currentScreen = screenName
            }
        }
    }

    private fun open(fragment: Fragment, screenName: String) {
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(screenName)
            .commit()
    }

    private fun getFragmentForScreen(screenName: String): Fragment? {
        return when (screenName) {
            SCREEN_GAME -> GameFragment.newInstance()
            SCREEN_STATISTICS -> {
                return if (FirebaseAuth.getInstance().currentUser != null) {
                    currentScreen = SCREEN_STATISTICS
                    StatisticFragment.newInstance()
                } else {
                    currentScreen = SCREEN_LOGIN
                    LoginFragment.newInstance()
                }
            }
            SCREEN_INFO -> InfoFragment.newInstance()
            SCREEN_RGB -> RgbFragment.newInstance()
            SCREEN_SETTINGS -> SettingsFragment.newInstance()
            SCREEN_LOGIN -> LoginFragment.newInstance()
            else -> null
        }
    }

    fun navigateBack(activity: Activity) {
        if (currentScreen == SCREEN_GAME) {
            activity.finish()
        } else {
            openFragment(SCREEN_GAME)
        }
    }
}