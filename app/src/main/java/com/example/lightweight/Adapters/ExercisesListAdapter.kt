package com.example.lightweight.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lightweight.R
import kotlinx.android.synthetic.main.exercise_row.view.*

class ExercisesListAdapter : RecyclerView.Adapter<ExercisesListAdapter.ExercisesViewHolder>() {
    private val names = arrayOf( "wyciskanie", "podciąganie", "przysiady", "Bieganie")

    inner class ExercisesViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){


        var exerciseName: TextView

        init {
            exerciseName = itemView.findViewById(R.id.tvExerciseName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisesViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.exercise_row, parent, false)
        return ExercisesViewHolder(v)
    }

    override fun onBindViewHolder(holder: ExercisesViewHolder, position: Int) {
        holder.exerciseName.text = names[position]
    }

    override fun getItemCount(): Int {
        return names.size
    }
}