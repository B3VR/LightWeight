package com.example.lightweight.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lightweight.R
import com.example.lightweight.models.Training

class ArchiveTrainingAdapter(var training: Training, var activity: FragmentActivity?) : RecyclerView.Adapter<ArchiveTrainingAdapter.ArchiveTrainingViewHolder>() {

    inner class ArchiveTrainingViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        var exerciseName: TextView
        var exerciseImage: ImageView
        var rvMember: RecyclerView

        init{
            exerciseName = itemView.findViewById(R.id.tvExerciseNameGroup)
            exerciseImage = itemView.findViewById(R.id.ivExerciseImage)
            rvMember = itemView.findViewById(R.id.rvMember)
        }

    }

    override fun onBindViewHolder(holder: ArchiveTrainingViewHolder, position: Int) {
        var exercise = training.exercises[position]

        holder.exerciseName.text = exercise.name

        if(exercise.imageResource != null){
            holder.exerciseImage.setImageResource(exercise.imageResource!!)
        }

        var memberAdapter = MemberAdapter(exercise.series)
        var layoutManager = LinearLayoutManager(activity)

        holder.rvMember.adapter = memberAdapter
        holder.rvMember.layoutManager = layoutManager

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchiveTrainingAdapter.ArchiveTrainingViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.group_row, parent, false)
        return ArchiveTrainingViewHolder(v)
    }

    override fun getItemCount(): Int {
        return training.exercises.size
    }



}
