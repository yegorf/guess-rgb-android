package com.example.guess_rgb_kotlin.data

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.example.guess_rgb_kotlin.R
import com.example.guess_rgb_kotlin.constant.PrefKey
import com.example.guess_rgb_kotlin.data.entity.User
import com.example.guess_rgb_kotlin.fragment.StatisticFragment
import com.google.firebase.firestore.FirebaseFirestore


const val USERS = "users"
const val WIN_COUNT = "win"
const val LOOSE_COUNT = "loose"

fun getTotalStatistic(view: StatisticFragment) {
    val store = FirebaseFirestore.getInstance()
    store.collection(USERS)
        .get()
        .addOnSuccessListener { result ->
            val data = mutableListOf<User>()
            result.forEach {
                val user = it.toObject(User::class.java)
                user.email = it.id
                data.add(user)
            }
            view.setGlobalStatistics(data)
        }
        .addOnFailureListener {
            Toast.makeText(
                view.context,
                view.context?.getString(R.string.network_problems),
                Toast.LENGTH_LONG
            )
                .show()
        }
}

fun updateUserStatistic(view: StatisticFragment, user: User) {
    val store = FirebaseFirestore.getInstance()
    val map = hashMapOf(
        WIN_COUNT to user.win,
        LOOSE_COUNT to user.loose
    )

    store.collection(USERS)
        .document(user.email)
        .set(map)
        .addOnSuccessListener {
            getTotalStatistic(view)
        }
        .addOnFailureListener {
            Toast.makeText(
                view.context,
                view.context?.getString(R.string.network_problems),
                Toast.LENGTH_LONG
            )
                .show()
        }
}

fun updateUserStatistic(user: User) {
    val store = FirebaseFirestore.getInstance()
    val map = hashMapOf(
        WIN_COUNT to user.win,
        LOOSE_COUNT to user.loose
    )

    store.collection(USERS)
        .document(user.email)
        .set(map)
}

fun fetchUserStatistic(activity: Activity, email: String) {
    val store = FirebaseFirestore.getInstance()
    store.collection(USERS)
        .document(email)
        .get()
        .addOnSuccessListener { document ->
            val user = document.toObject(User::class.java)
            if (user != null) {
                val winCount = user.win
                val looseCount = user.loose

                val preferences = activity.getPreferences(Context.MODE_PRIVATE)
                preferences.edit().putInt(PrefKey.WIN_SCORE, winCount.toInt()).apply()
                preferences.edit().putInt(PrefKey.LOOSE_SCORE, looseCount.toInt()).apply()
            }
        }
}