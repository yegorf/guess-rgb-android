package com.example.guess_rgb_kotlin.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.guess_rgb_kotlin.R
import com.example.guess_rgb_kotlin.data.getTotalStatistic
import com.example.guess_rgb_kotlin.navigation.NavigationManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("yegorf","kek")

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

        NavigationManager(supportFragmentManager)
            .openRoot()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        NavigationManager(supportFragmentManager)
            .navigateBack(this)
    }
}