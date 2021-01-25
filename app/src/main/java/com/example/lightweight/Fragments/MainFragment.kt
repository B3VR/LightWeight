package com.example.lightweight.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.lightweight.R

class MainFragment : Fragment(), View.OnClickListener {

    var navControler: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navControler = Navigation.findNavController(view)

        view.findViewById<CardView>(R.id.btnUserProfile).setOnClickListener(this)
        view.findViewById<CardView>(R.id.btnStartTraining).setOnClickListener(this)
        view.findViewById<CardView>(R.id.btnUserTrainings).setOnClickListener(this)
        view.findViewById<CardView>(R.id.btnEditProfile).setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnUserProfile -> navControler!!.navigate(R.id.action_mainFragment_to_profileFragment)

            R.id.btnStartTraining -> navControler!!.navigate(R.id.action_mainFragment_to_startTrainingFragment)

            R.id.btnUserTrainings -> findNavController().navigate(R.id.action_mainFragment_to_trainingArchivumFragment)

            R.id.btnEditProfile -> findNavController().navigate(R.id.action_mainFragment_to_editProfileFragment)
        }
    }


}