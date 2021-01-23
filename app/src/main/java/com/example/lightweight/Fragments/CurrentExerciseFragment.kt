package com.example.lightweight.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lightweight.Adapters.ExerciseClickListener
import com.example.lightweight.Adapters.SeriesAdapter
import com.example.lightweight.R
import com.example.lightweight.models.Exercise
import com.example.lightweight.models.Serie
import com.example.lightweight.models.Training
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_current_exercise.*


class CurrentExerciseFragment : Fragment(), View.OnClickListener {
    var navControler: NavController? = null
    private var db = FirebaseFirestore.getInstance()
    private var auth: FirebaseAuth = Firebase.auth

    private lateinit var currentExercise: Exercise
    var seriesList = mutableListOf<Serie>(Serie())

    private lateinit var rv: RecyclerView
    private lateinit var adapter: SeriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navControler = Navigation.findNavController(view)

        view.findViewById<TextView>(R.id.tvEndExercise).setOnClickListener(this)
        view.findViewById<Button>(R.id.btnAddSeries).setOnClickListener(this)
        rv = view.findViewById<RecyclerView>(R.id.rvSeriesList)

        currentExercise = arguments?.getParcelable<Exercise>("exercise")!!

        tvCurrentExerciseName.text = currentExercise!!.name

        adapter = SeriesAdapter(seriesList, context)

        rv.layoutManager = LinearLayoutManager(activity)
        rv.adapter = adapter

    }

    private fun addSerie() {
        var newSerie = Serie()
        seriesList.add(newSerie)
        rvSeriesList.adapter?.notifyItemInserted(seriesList.size)
    }

    private  fun saveExercise() {
        db.collection("Users")
            .document(auth.uid.toString())
            .collection("Current Training")
            .document("Current Training")
            .get()
            .addOnSuccessListener {
                var currentTraining = it.toObject(Training::class.java)

                if(currentTraining!!.done == false) {
                    addExerciseToTraining(currentTraining)
                }
            }.addOnFailureListener {
                Log.w("START TRAINING FRAGMENT", "błąd pobrania obecnego treningu", it)

            }
    }

    private fun addExerciseToTraining(currentTraining: Training) {
        currentExercise!!.series.addAll(seriesList)

        currentTraining.exercises.add(currentExercise!!)

        db.collection("Users")
            .document(auth.uid.toString())
            .collection("Current Training")
            .document("Current Training")
            .set(currentTraining)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnAddSeries -> addSerie()

            R.id.tvEndExercise -> { saveExercise()
                //navControler!!.navigate(R.id.action_currentExerciseFragment_to_startTrainingFragment)
            }
        }
    }




}