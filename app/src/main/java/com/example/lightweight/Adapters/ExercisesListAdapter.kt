package com.example.lightweight.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.lightweight.R
import com.example.lightweight.models.Exercise
import kotlinx.android.synthetic.main.exercise_row.view.*

class ExercisesListAdapter(var exerciseList: MutableList<Exercise>, var listener:ExerciseClickListener) : RecyclerView.Adapter<ExercisesListAdapter.ExercisesViewHolder>() {

    inner class ExercisesViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var exerciseName: TextView
        var exerciseTarget: TextView
        var exerciseImage: ImageView

        init {
            itemView.setOnClickListener{
                listener.onClickListener()
            }

            exerciseTarget = itemView.findViewById(R.id.tvExerciseTarget)
            exerciseName = itemView.findViewById(R.id.tvExerciseName)
            exerciseImage = itemView.findViewById(R.id.ivExercise)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisesViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.exercise_row, parent, false)
        return ExercisesViewHolder(v)
    }

    override fun onBindViewHolder(holder: ExercisesViewHolder, position: Int) {

        var currentExercise = exerciseList[position]

        holder.exerciseName.text = currentExercise.name
        holder.exerciseTarget.text = currentExercise.target

        if(currentExercise.imageResource != null) {
            holder.exerciseImage.setImageResource(currentExercise.imageResource!!)
        }
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}