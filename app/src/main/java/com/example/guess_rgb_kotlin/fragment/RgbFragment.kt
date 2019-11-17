package com.example.guess_rgb_kotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.guess_rgb_kotlin.R

class RgbFragment : Fragment() {

    companion object {
        public fun getInstance(): RgbFragment {
            return RgbFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rgb, container, false)
    }
}