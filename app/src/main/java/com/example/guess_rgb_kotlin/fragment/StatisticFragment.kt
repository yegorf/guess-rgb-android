package com.example.guess_rgb_kotlin.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guess_rgb_kotlin.R
import com.example.guess_rgb_kotlin.adapter.StatisticsAdapter
import com.example.guess_rgb_kotlin.constant.PrefKey
import com.example.guess_rgb_kotlin.data.entity.User
import com.example.guess_rgb_kotlin.data.getTotalStatistic
import com.example.guess_rgb_kotlin.data.updateUserStatistic
import com.example.guess_rgb_kotlin.navigation.NavigationManager
import com.example.guess_rgb_kotlin.tools.calculateLoose
import com.example.guess_rgb_kotlin.tools.calculateWin
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.round

class StatisticFragment : Fragment() {

    private lateinit var winCountTv: TextView
    private lateinit var looseCountTv: TextView
    private lateinit var userTv: TextView
    private lateinit var recycler: RecyclerView
    private lateinit var scorePb: ProgressBar

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
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_statistic, container, false)
        initViews(view)
        setData()
        sendStatistic()
        return view
    }

    private fun initViews(view: View) {
        winCountTv = view.findViewById(R.id.tv_win_count)
        looseCountTv = view.findViewById(R.id.tv_loose_count)
        userTv = view.findViewById(R.id.tv_user)
        recycler = view.findViewById(R.id.rv_global_statistics)
        scorePb = view.findViewById(R.id.pb_progress)
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

        val winPercent = calculateWin(winCount, looseCount)
        val loosePercent = calculateLoose(winCount, looseCount)

        val win = "$winCount ($winPercent%)"
        val loose = "$looseCount ($loosePercent%)"

        winCountTv.text = win
        looseCountTv.text = loose

        scorePb.progress = round(winPercent.toFloat()).toInt()

        getTotalStatistic(this)
    }

    private fun sendStatistic() {
        val preferences = activity?.getPreferences(Context.MODE_PRIVATE)

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
        updateUserStatistic(this, user)
    }

    fun setGlobalStatistics(users: List<User>) {
        users.forEach { it.setPercents() }
        users.sortedBy { it.winPercent }
        recycler.adapter = StatisticsAdapter(users, (context as Context))
        recycler.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.add(context?.getString(R.string.sign_out))
            .setOnMenuItemClickListener {
                FirebaseAuth.getInstance().signOut()
                NavigationManager(fragmentManager as FragmentManager)
                    .openFragment(NavigationManager.SCREEN_LOGIN)
                true
            }
        super.onCreateOptionsMenu(menu, inflater)
    }
}