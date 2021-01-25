package com.example.lightweight.Fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lightweight.Adapters.GroupAdapter
import com.example.lightweight.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.type.DateTime
import java.time.LocalDate
import com.example.lightweight.models.Training as Training


class StartTrainingFragment : Fragment(), View.OnClickListener {

    private var navController: NavController? = null
    private var db = FirebaseFirestore.getInstance()
    private var auth = Firebase.auth

    private lateinit var rvGroup: RecyclerView
    private lateinit var adapterGroup: GroupAdapter
    private lateinit var layoutManager: LinearLayoutManager

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
        rvGroup = view.findViewById(R.id.rvCurrentTraining)

        getCurrentTraining()
    }

    private  fun getCurrentTraining() {
        db.collection("Users")
            .document(auth.uid.toString())
            .collection("Current Training")
            .document("Current Training")
            .get()
            .addOnSuccessListener {
                var currentTraining = it.toObject(Training::class.java)

                Log.d("ROZMIAR", currentTraining!!.exercises.size.toString())
                if(currentTraining!!.done == false) {
                   displayCurrentTraining(currentTraining)

                }else{
                    addUserTrainingsData()
                    getCurrentTraining()
                }

            }.addOnFailureListener {
                Log.w("START TRAINING FRAGMENT", "błąd pobrania obecnego treningu", it)

            }
    }

    private fun displayCurrentTraining(currentTraining: Training) {
        rvGroup.apply {
            adapter = GroupAdapter(currentTraining, activity)
            layoutManager = LinearLayoutManager(activity)
        }
        rvGroup.adapter!!.notifyItemInserted(0)
    }

    private fun addUserTrainingsData(){
        var currentTraining = Training()
        currentTraining.done = false
        db.collection("Users").document(auth.uid!!.toString())
            .collection("Current Training").document("Current Training")
            .set(currentTraining)
    }

    private fun endTraining() {
        db.collection("Users").document(auth.uid!!.toString())
            .collection("Current Training").document("Current Training")
            .update("done", true)
            .addOnSuccessListener {


            }.addOnFailureListener {

            }

        db.collection("Users").document(auth.uid!!.toString())
            .collection("Current Training").document("Current Training")
            .get()
            .addOnSuccessListener {
                archiveTraining(it.toObject(Training::class.java)!!)

            }.addOnFailureListener {

            }
    }


    private fun archiveTraining(training: Training) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            training.date = LocalDate.now().toString()
        }

        db.collection("Users")
            .document(auth.uid!!.toString())
            .collection("Training Archivum")
            .document()
            .set(training)
            .addOnSuccessListener {

            }
    }


    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnAddExercise -> {
                navController?.navigate(R.id.action_startTrainingFragment_to_exercisesListFragment)
            }
            R.id.ivBackArrow3 -> navController?.navigate(R.id.action_startTrainingFragment_to_mainFragment)

            R.id.tvEndTraining -> {
                endTraining()
                navController?.navigate(R.id.action_startTrainingFragment_to_mainFragment)
            }
        }
    }


}