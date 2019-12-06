package com.example.guess_rgb_kotlin.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guess_rgb_kotlin.R
import com.example.guess_rgb_kotlin.adapter.StatisticsAdapter
import com.example.guess_rgb_kotlin.constant.PrefKey
import com.example.guess_rgb_kotlin.data.entity.User
import com.example.guess_rgb_kotlin.navigation.NavigationManager
import com.example.guess_rgb_kotlin.tools.calculateLoose
import com.example.guess_rgb_kotlin.tools.calculateWin
import com.google.firebase.auth.FirebaseAuth

class StatisticFragment : Fragment() {

    private lateinit var winCountTv: TextView
    private lateinit var looseCountTv: TextView
    private lateinit var userTv: TextView
    private lateinit var signOutBtn: Button
    private lateinit var recycler: RecyclerView

    companion object {
        fun newInstance(): StatisticFragment {
            return StatisticFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        recycler = view.findViewById(R.id.rv_global_statistics)
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

        val users = mutableListOf<User>()
        users.add(User("user1@gmail.com", 5, 10))
        users.add(User("user2@gmail.com", 5, 30))
        users.add(User("user3@gmail.com", 1, 50))
        setGlobalStatistics(users)
    }

    private fun setGlobalStatistics(users: List<User>) {
        recycler.adapter = StatisticsAdapter(users, (context as Context))
        recycler.layoutManager = LinearLayoutManager(context)
    }
}