package com.example.guess_rgb_kotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.guess_rgb_kotlin.R
import com.google.firebase.auth.FirebaseAuth
import com.skydoves.colorpickerview.ColorPickerView

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    lateinit var emailEt: EditText
    lateinit var passwordEt: EditText
    lateinit var loginBtn: Button

    companion object {
        public fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_navigation, container, false)
        initViews(view)
        setOnClickListeners()
        firebaseAuth()
        return view
    }

    private fun firebaseAuth() {
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
    }

    private fun setOnClickListeners() {
        loginBtn.setOnClickListener {
            login(emailEt.text.toString(), passwordEt.text.toString())
        }
    }

    private fun initViews(view: View) {
        emailEt = view.findViewById(R.id.et_email)
        passwordEt = view.findViewById(R.id.et_password)
        loginBtn = view.findViewById(R.id.btn_login)
    }

    private fun createUser(email: String, password: String) {

    }

    private fun login(email: String, password: String) {

    }
}