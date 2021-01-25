package com.example.lightweight.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lightweight.Adapters.ArchiveTrainingAdapter
import com.example.lightweight.R
import com.example.lightweight.models.Training
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_archive_training.*

class ArchiveTrainingFragment : Fragment(), View.OnClickListener{

    private var navController: NavController? = null

    private lateinit var training: Training
    private lateinit var rvArchiveTraining: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_archive_training, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvArchiveTraining = view.findViewById(R.id.rvArchiveTraining)
        navController = Navigation.findNavController(view)
        view.findViewById<ImageButton>(R.id.btnBackToArchiveTrainings).setOnClickListener(this)

        training = arguments?.getParcelable<Training>("training")!!
        displayTraining(training)

    }

    private fun displayTraining(training: Training){

        tvArchiveTrainingDate.text = training.date

        rvArchiveTraining.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ArchiveTrainingAdapter(training, requireActivity())
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnBackToArchiveTrainings -> findNavController().navigate(R.id.action_archiveTrainingFragment_to_trainingArchivumFragment)
        }
    }

}