package com.example.lightweight.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lightweight.R
import com.example.lightweight.models.Exercise
import com.example.lightweight.models.Training

class TrainingsAdapter(var trainingsList: MutableList<Training>, private var listener: ExerciseClickListener<Training>) : RecyclerView.Adapter<TrainingsAdapter.TrainingsViewHolder>() {
    inner class TrainingsViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        var date: TextView

        init {
            date = itemView.findViewById(R.id.tvTrainingDate)
        }
    }

    override fun onBindViewHolder(holder: TrainingsViewHolder, position: Int) {
        var trainingDate: String = trainingsList[position].date.toString()

        Log.d("TRAININGS", trainingDate)
        if(trainingDate != null){
            holder.date.text = trainingDate


        }else{
            holder.date.text = "Brak daty"
        }

        holder.itemView.setOnClickListener {
            listener.onExerciseClickListener(trainingsList[position])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.training_row, parent, false)
        return TrainingsViewHolder(v)
    }

    override fun getItemCount(): Int {
        return trainingsList.size
    }
}
