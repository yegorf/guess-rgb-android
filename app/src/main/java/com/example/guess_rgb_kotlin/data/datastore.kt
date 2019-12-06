package com.example.guess_rgb_kotlin.data

import android.util.Log
import com.example.guess_rgb_kotlin.data.entity.User
import com.example.guess_rgb_kotlin.fragment.StatisticFragment
import com.google.firebase.firestore.FirebaseFirestore


const val TAG = "Firestore"

const val USERS = "users"

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
        .addOnFailureListener { exception ->
            Log.e(TAG, exception.toString())
        }
}

fun updateUserStatistic(user: User) {
    val store = FirebaseFirestore.getInstance()
    val userMap = mutableMapOf<String, Any>()
    userMap.put(user.email, user)

    store.collection(USERS)
        .document(user.email)
        .update(userMap)
}