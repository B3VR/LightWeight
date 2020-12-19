package com.example.lightweight.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lightweight.Adapters.ExercisesListAdapter
import com.example.lightweight.R
import com.example.lightweight.models.Exercise
import kotlinx.android.synthetic.main.fragment_exercises_list.*


class ExercisesListFragment : Fragment() {

    var execisesList = mutableListOf<Exercise>(
        Exercise("Wyciskanie sztangi leżąc", "Klatka piersiowa", R.drawable.dumbbell),
        Exercise("Wyciskanie sztangi nad głowę", "Barki", R.drawable.dumbbell),
        Exercise("Pompki" , "Klatka piersiowa", R.drawable.dumbbell),
        Exercise("Podciąganie", "Plecy", R.drawable.dumbbell),
        Exercise("Wiosłowanie sztangą", "Plecy", R.drawable.dumbbell),
        Exercise("Wiosłowanie hantlem jednorącz", "Plecy", R.drawable.dumbbell),
        Exercise("Wiosłowanie hantlami oburącz", "Plecy", R.drawable.dumbbell),
        Exercise("Przysiady ze sztangą", "Uda", R.drawable.dumbbell),
        Exercise("Uginania podudzi", "Mięśnie Dwugłowe uda", null)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercises_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.apply {

            layoutManager = LinearLayoutManager(activity)
            adapter = ExercisesListAdapter(execisesList)

        }
    }
}