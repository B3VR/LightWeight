package com.example.lightweight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.*

class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var bmi = view.findViewById<TextView>(R.id.bmiView)
        bmi.text = getBMI(85.0,1.5).toShort().toString()
    }

    private fun getBMI(weight: Double, height: Double): Double{
        var bmi: Double
        bmi = weight/ (height*height)

        return bmi
    }
}

