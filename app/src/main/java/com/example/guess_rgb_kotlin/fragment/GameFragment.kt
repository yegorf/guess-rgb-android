package com.example.guess_rgb_kotlin.fragment

import android.content.Context
import android.graphics.Color.rgb
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import com.example.guess_rgb_kotlin.R
import com.example.guess_rgb_kotlin.entity.Color
import com.example.guess_rgb_kotlin.tools.generateRGB
import com.example.guess_rgb_kotlin.tools.getPlateNum
import java.util.*
import kotlin.math.abs

class GameFragment : Fragment() {

    private lateinit var colorTv: TextView
    private lateinit var plate1: Button
    private lateinit var plate2: Button
    private lateinit var plate3: Button
    private lateinit var plate4: Button
    private lateinit var plate5: Button
    private lateinit var plate6: Button
    private lateinit var plate7: Button
    private lateinit var plate8: Button
    private lateinit var plate9: Button

    private var plates = LinkedList<Button>()
    private val preferences = activity?.getPreferences(Context.MODE_PRIVATE)

    companion object {
        fun getInstanse(): GameFragment {
            return GameFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        ButterKnife.bind(this, view)
        initViews(view)

        plates.add(plate1)
        plates.add(plate2)
        plates.add(plate3)
        plates.add(plate4)
        plates.add(plate5)
        plates.add(plate6)
        plates.add(plate7)
        plates.add(plate8)
        plates.add(plate9)
        setData()

        return view
    }

    //todo binding
    private fun initViews(view: View) {
        colorTv = view.findViewById(R.id.tv_color_rgb)
        plate1 = view.findViewById(R.id.plate1)
        plate2 = view.findViewById(R.id.plate2)
        plate3 = view.findViewById(R.id.plate3)
        plate4 = view.findViewById(R.id.plate4)
        plate5 = view.findViewById(R.id.plate5)
        plate6 = view.findViewById(R.id.plate6)
        plate7 = view.findViewById(R.id.plate7)
        plate8 = view.findViewById(R.id.plate8)
        plate9 = view.findViewById(R.id.plate9)
    }

    private fun setPlateText(plate: Button, color: Color) {
        plate.textSize = 15F
        val r = abs(255 - color.r)
        val g = abs(255 - color.g)
        val b = abs(255 - color.b)
        plate.setTextColor(rgb(r, g, b))
        val answer = "${color.r},${color.g},${color.b}"
        plate.text = answer
    }

    private fun setData() {
        clearPlates()
        val answer = getPlateNum()
        var right: Boolean

        for (i in plates.indices) {
            right = answer == i
            setPlateColor(plates[i], right)
        }
    }

    private fun setPlateColor(plate: Button, right: Boolean) {
        val color = generateRGB()
        plate.setBackgroundResource(R.drawable.plate)

        when (val drawable = plate.background) {
            is ShapeDrawable -> drawable.paint.color = rgb(color.r, color.g, color.b)
            is GradientDrawable -> drawable.setColor(rgb(color.r, color.g, color.b))
            is ColorDrawable -> drawable.color = rgb(color.r, color.g, color.b)
        }

        if (right) {
            colorTv.text = color.toString()
            plate.setOnClickListener {
                Toast.makeText(context, getString(R.string.right_answer), Toast.LENGTH_SHORT).show()
                setData()

                if (activity != null) {
                    if (preferences != null) {
                        val count = preferences.getInt("WIN_SCORE", 0)
                        preferences.edit().putInt("WIN_SCORE", count + 1).apply()
                    }
                }
            }
        } else {
            plate.setOnClickListener {
                setPlateText(plate, color)
                if (activity != null) {
                    if (preferences != null) {
                        val count = preferences.getInt("LOOSE_SCORE", 0)
                        preferences.edit().putInt("LOOSE_SCORE", count + 1).apply()
                    }
                }
                plate.isClickable = false
            }
        }
    }

    private fun clearPlates() {
        for (plate in plates) {
            plate.text = ""
            plate.isClickable = true
        }
    }
}