package com.example.lightweight.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
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

        view.findViewById<Button>(R.id.profileBtn).setOnClickListener(this)
        view.findViewById<Button>(R.id.editProfileBtn).setOnClickListener(this)
        view.findViewById<Button>(R.id.startTrainingBtn).setOnClickListener(this)
        view.findViewById<Button>(R.id.addTrainingBtn).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.profileBtn -> navControler!!.navigate(R.id.action_mainFragment_to_profileFragment)

            R.id.startTrainingBtn -> navControler!!.navigate(R.id.action_mainFragment_to_startTrainingFragment)

            R.id.editProfileBtn -> navControler!!.navigate(R.id.action_mainFragment_to_editProfileFragment)

        }
    }


}