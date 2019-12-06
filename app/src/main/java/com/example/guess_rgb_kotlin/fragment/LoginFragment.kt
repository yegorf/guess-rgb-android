package com.example.guess_rgb_kotlin.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.guess_rgb_kotlin.R
import com.example.guess_rgb_kotlin.navigation.NavigationManager
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    lateinit var emailEt: EditText
    lateinit var passwordEt: EditText
    lateinit var loginBtn: Button
    lateinit var registrationBtn: Button

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
                    Toast.makeText(
                        context, "Authentication success.",
                        Toast.LENGTH_SHORT
                    ).show()
                    NavigationManager(fragmentManager as FragmentManager)
                        .openFragment(NavigationManager.SCREEN_STATISTICS)
                } else {
                    Toast.makeText(
                        context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity as Activity) { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    this.activity?.runOnUiThread {
                        Toast.makeText(
                            context, "Authentication success.",
                            Toast.LENGTH_SHORT
                        ).show()
                        NavigationManager(fragmentManager as FragmentManager)
                            .openFragment(NavigationManager.SCREEN_STATISTICS)
                    }

                } else {
                    this.activity?.runOnUiThread {
                        Toast.makeText(
                            context, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
    }
}