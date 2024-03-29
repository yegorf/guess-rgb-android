package com.example.guess_rgb_kotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.guess_rgb_kotlin.R
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.colorpickerview.ColorPickerView
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener

class RgbFragment : Fragment() {

    lateinit var colorPickerView: ColorPickerView
    lateinit var rgbColorTv: TextView
    lateinit var hexColorTv: TextView
    lateinit var colorView: LinearLayout

    companion object {
        fun newInstance(): RgbFragment {
            return RgbFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rgb, container, false)
        initViews(view)
        setListeners()
        return view
    }

    private fun setListeners() {
        colorPickerView.colorListener =
            ColorEnvelopeListener { colorEnvelope: ColorEnvelope, _: Boolean ->
                colorView.setBackgroundColor(colorEnvelope.color)
                val hex = "HEX: #${colorEnvelope.hexCode.substring(2)}"
                hexColorTv.text = hex
                val colorArray = colorEnvelope.argb
                val color = "RGB: ${colorArray[1]},${colorArray[2]},${colorArray[3]}"
                rgbColorTv.text = color
                rgbColorTv.setTextColor(
                    android.graphics.Color.rgb(
                        255 - colorArray[1],
                        255 - colorArray[2],
                        255 - colorArray[3]
                    )
                )
            }
    }

    private fun initViews(view: View) {
        colorPickerView = view.findViewById(R.id.v_color_picker)
        rgbColorTv = view.findViewById(R.id.tv_rgb_code)
        hexColorTv = view.findViewById(R.id.tv_hex_code)
        colorView = view.findViewById(R.id.v_color_code)
    }
}