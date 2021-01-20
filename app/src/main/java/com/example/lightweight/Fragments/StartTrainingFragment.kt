package com.example.lightweight.Fragments

import android.os.Bundle
import android.util.Log
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.example.lightweight.models.Training as Training


class StartTrainingFragment : Fragment(), View.OnClickListener {
    private lateinit var currentTraining: Training

    private var navController: NavController? = null
    private var db = FirebaseFirestore.getInstance()
    private var auth = Firebase.auth

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

        currentTraining = loadOrCreateCurrentTraining()!!

        view.findViewById<Button>(R.id.btnAddExercise).setOnClickListener(this)
        view.findViewById<TextView>(R.id.tvEndTraining).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.ivBackArrow3).setOnClickListener(this)
    }

    private fun loadOrCreateCurrentTraining(): Training? {
        var currentTraining: Training?

        if(!checkCurrentTraining()){
            currentTraining = getCurrentTraining()

        }else{
            currentTraining = null
        }

        return currentTraining
    }

    private fun checkCurrentTraining(): Boolean {
        var currentTraining = getCurrentTraining()
        var isTrainingDone = currentTraining?.isDone

        return isTrainingDone!!
    }

    private fun getCurrentTraining(): Training? {
        var training = mutableListOf<Training>()

        db.collection("Users")
            .document(auth.uid.toString())
            .collection("Current Training")
            .document("Current Training")
            .get()
            .addOnSuccessListener {
                var currentTraining = it.toObject(Training::class.java)
                training.add(currentTraining!!)
                Log.d("StartTrainingFragment", "obecny trening pobrany")

            }.addOnFailureListener {
                Log.d("StartTrainingFragment", "błąd pobrania obecnego treningu")
            }

        if (training.isNotEmpty()){
            return training[0]

        }else{
            return null
        }
    }

    private fun saveTraining(){

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnAddExercise -> navController?.navigate(R.id.action_startTrainingFragment_to_exercisesListFragment)

            R.id.ivBackArrow3 -> navController?.navigate(R.id.action_startTrainingFragment_to_mainFragment)

            R.id.tvEndTraining -> {


            }
        }
    }
}