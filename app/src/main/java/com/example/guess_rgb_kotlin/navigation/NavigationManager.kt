package com.example.guess_rgb_kotlin.navigation

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.guess_rgb_kotlin.R
import com.example.guess_rgb_kotlin.fragment.GameFragment

class NavigationManager(private val fragmentManager: FragmentManager) {

    companion object {
        val SCREEN_GAME = "SCREEN_GAME"
        val SCREEN_STATISTICS = "SCREEN_STATISTICS"
        val SCREEN_INFO = "SCREEN_INFO"
        val SCREEN_RGB = "SCREEN_RGB"
        val SCREEN_SETTINGS = "SCREEN_SETTINGS"
        var currentScreen = SCREEN_GAME
    }

    public fun openFragment(screenName: String) {
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
            SCREEN_GAME -> GameFragment()
            SCREEN_STATISTICS -> Fragment()
            SCREEN_INFO -> Fragment()
            SCREEN_RGB -> Fragment()
            SCREEN_SETTINGS -> Fragment()
            else -> null
        }
    }

    public fun navigateBack(activity: Activity) {
        if (currentScreen == SCREEN_GAME) {
            activity.finish()
        } else {
            openFragment(SCREEN_GAME)
        }
    }
}