package com.example.lightweight.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.lightweight.R
import kotlinx.android.synthetic.main.fragment_start_training.*


class StartTrainingFragment : Fragment(), View.OnClickListener {

    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start_training, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.btnAddExercise).setOnClickListener(this)
        view.findViewById<TextView>(R.id.tvEndTraining).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.ivBackArrow3).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnAddExercise -> navController?.navigate(R.id.action_startTrainingFragment_to_exercisesListFragment)

            R.id.ivBackArrow3 -> navController?.navigate(R.id.action_startTrainingFragment_to_mainFragment)
        }
    }
}