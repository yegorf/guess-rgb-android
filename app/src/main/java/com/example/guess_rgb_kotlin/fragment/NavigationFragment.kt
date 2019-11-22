package com.example.guess_rgb_kotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.guess_rgb_kotlin.R
import com.example.guess_rgb_kotlin.navigation.NavigationManager

class NavigationFragment : Fragment() {

    lateinit var manager: NavigationManager
    lateinit var game: ImageButton
    lateinit var account: ImageButton
    lateinit var info: ImageButton
    lateinit var rgb: ImageButton
    lateinit var settings: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_navigation, container, false)
        initViews(view)
        setOnClickListeners()
        return view
    }

    private fun initViews(view: View) {
        game = view.findViewById(R.id.ib_game)
        account = view.findViewById(R.id.ib_account)
        info = view.findViewById(R.id.ib_info)
        rgb = view.findViewById(R.id.ib_rgb)
        settings = view.findViewById(R.id.ib_settings)
    }

    private fun setOnClickListeners() {
        manager = NavigationManager(activity!!.supportFragmentManager)
        game.setOnClickListener {
            manager.openFragment(NavigationManager.SCREEN_GAME)
        }
        account.setOnClickListener {
            manager.openFragment(NavigationManager.SCREEN_STATISTICS)
        }
        info.setOnClickListener {
            manager.openFragment(NavigationManager.SCREEN_INFO)
        }
        rgb.setOnClickListener {
            manager.openFragment(NavigationManager.SCREEN_RGB)
        }
        settings.setOnClickListener {
            manager.openFragment(NavigationManager.SCREEN_SETTINGS)
        }
    }
}