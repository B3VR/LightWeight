package com.example.lightweight.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lightweight.Adapters.ExerciseClickListener
import com.example.lightweight.Adapters.TrainingsAdapter
import com.example.lightweight.R
import com.example.lightweight.models.Exercise
import com.example.lightweight.models.Training
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class TrainingArchivumFragment : Fragment(), View.OnClickListener {

    private var navController: NavController? = null
    private var db = FirebaseFirestore.getInstance()
    private var auth = Firebase.auth

    private lateinit var rvTrainins: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training_archivum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        rvTrainins = view.findViewById(R.id.rvTrainings)
        view.findViewById<ImageButton>(R.id.btnBackFromTrainings).setOnClickListener(this)

        getUserTrainings()
    }

    private fun getUserTrainings(){
        db.collection("Users")
            .document(auth.uid.toString())
            .collection("Training Archivum")
            .get()
            .addOnSuccessListener {
                var trainingsList = mutableListOf<Training>()
                trainingsList.addAll(it.toObjects(Training::class.java))

                inflateRecyclerView(trainingsList)

            }.addOnFailureListener {

            }
    }

    private fun inflateRecyclerView(trainingsList: MutableList<Training>) {

        rvTrainins.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TrainingsAdapter(trainingsList, object: ExerciseClickListener<Training> {

                override fun onExerciseClickListener(item: Training) {
                    var bundle = bundleOf("training" to item)
                    findNavController().navigate(R.id.action_trainingArchivumFragment_to_archiveTrainingFragment, bundle)
                }
            })
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnBackFromTrainings -> findNavController().navigate(R.id.action_trainingArchivumFragment_to_mainFragment)
        }
    }

}