package com.example.lightweight.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.lightweight.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(), View.OnClickListener {

    var navControler: NavController? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        navControler = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.editProfileBtn2).setOnClickListener(this)

        var bmi = view.findViewById<TextView>(R.id.bmiView)
        bmi.text = getBMI(85.0,1.5).toShort().toString()
        userNameTxt.text = auth.uid.toString()
    }

    private fun getBMI(weight: Double, height: Double): Double{
        var bmi: Double
        bmi = weight/ (height*height)

        return bmi
    }

    override fun onClick(v: View?){
        when(v!!.id){
            R.id.editProfileBtn2 -> navControler!!.navigate(R.id.action_profileFragment_to_editProfileFragment)
        }
    }
}

