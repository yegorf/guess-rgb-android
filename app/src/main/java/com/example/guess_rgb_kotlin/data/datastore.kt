package com.example.guess_rgb_kotlin.data

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


fun getTotalStatistic() {
//    val database = FirebaseFirestore
//        .getInstance()
//        .apply {
//            firestoreSettings = FirebaseFirestoreSettings.Builder()
//                .setPersistenceEnabled(false)
//                .build()
//        }

//    database.collection("users")
//        .get()
//        .addOnSuccessListener {
//            val users: List<User> = it.toObjects(User::class.java)
//        }
//        .addOnFailureListener {
//
//        }

    val instance = FirebaseDatabase.getInstance()
    val database = instance.reference

    database.child("users").addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onCancelled(p0: DatabaseError) {
        }

        override fun onDataChange(p0: DataSnapshot) {

            val users = p0.children
            users.forEach {
                Log.i("yegorf", it.toString())
            }
        }
    })
}