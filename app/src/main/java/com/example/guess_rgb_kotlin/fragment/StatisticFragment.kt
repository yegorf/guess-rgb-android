package com.example.guess_rgb_kotlin.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.guess_rgb_kotlin.R
import com.example.guess_rgb_kotlin.constant.PrefKey
import com.example.guess_rgb_kotlin.navigation.NavigationManager
import com.example.guess_rgb_kotlin.tools.calculateLoose
import com.example.guess_rgb_kotlin.tools.calculateWin
import com.google.firebase.auth.FirebaseAuth

class StatisticFragment : Fragment() {

    private lateinit var winCountTv: TextView
    private lateinit var looseCountTv: TextView
    private lateinit var userTv: TextView
    private lateinit var signOutBtn: Button

    companion object {
        public fun newInstance(): StatisticFragment {
            return StatisticFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_statistic, container, false)
        initViews(view)
        setData()
        return view
    }

    private fun initViews(view: View) {
        winCountTv = view.findViewById(R.id.tv_win_count)
        looseCountTv = view.findViewById(R.id.tv_loose_count)
        userTv = view.findViewById(R.id.tv_user)
        signOutBtn = view.findViewById(R.id.btn_sign_out)
    }

    private fun setData() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        userTv.text = currentUser?.email

        val preferences = activity?.getPreferences(Context.MODE_PRIVATE)

        var winCount = 0
        var looseCount = 0

        if (preferences != null) {
            winCount = preferences.getInt(PrefKey.WIN_SCORE, 0)
            looseCount = preferences.getInt(PrefKey.LOOSE_SCORE, 0)
        }

        val win = "$winCount (${calculateWin(winCount, looseCount)}%)"
        val loose = "$looseCount (${calculateLoose(winCount, looseCount)}%)"

        winCountTv.text = win
        looseCountTv.text = loose

        signOutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            NavigationManager(fragmentManager as FragmentManager)
                .openFragment(NavigationManager.SCREEN_LOGIN)
        }
    }
}