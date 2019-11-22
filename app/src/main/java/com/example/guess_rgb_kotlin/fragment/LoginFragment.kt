package com.example.guess_rgb_kotlin.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.guess_rgb_kotlin.R
import com.example.guess_rgb_kotlin.navigation.NavigationManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.skydoves.colorpickerview.ColorPickerView

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    lateinit var emailEt: EditText
    lateinit var passwordEt: EditText
    lateinit var loginBtn: Button
    lateinit var registrationBtn: Button
    private val TAG = "yegorf"

    companion object {
        public fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        initViews(view)
        firebaseAuth()
        setOnClickListeners()
        return view
    }

    private fun firebaseAuth() {
        auth = FirebaseAuth.getInstance()
    }

    private fun setOnClickListeners() {
        loginBtn.setOnClickListener {
            login(emailEt.text.toString(), passwordEt.text.toString())
        }
        registrationBtn.setOnClickListener {
            createUser(emailEt.text.toString(), passwordEt.text.toString())
        }
    }

    private fun initViews(view: View) {
        emailEt = view.findViewById(R.id.et_email)
        passwordEt = view.findViewById(R.id.et_password)
        loginBtn = view.findViewById(R.id.btn_login)
        registrationBtn = view.findViewById(R.id.btn_registration)
    }

    private fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    Toast.makeText(context, "Authentication success.",
                        Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    NavigationManager(fragmentManager as FragmentManager)
                        .openFragment(NavigationManager.SCREEN_STATISTICS)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d(TAG, "createUserWithEmail:failure")
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity as Activity) { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    Log.i(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    NavigationManager(fragmentManager as FragmentManager)
                        .openFragment(NavigationManager.SCREEN_STATISTICS)
                } else {
                    Log.i(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signOut () {
        auth.signOut()
    }
}