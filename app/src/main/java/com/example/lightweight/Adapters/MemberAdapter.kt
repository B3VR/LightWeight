package com.example.lightweight.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lightweight.R
import com.example.lightweight.models.Serie

class MemberAdapter(var seriesList: MutableList<Serie>) : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {

    inner class MemberViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        var serieNumber: TextView
        var weight: TextView
        var reps: TextView

        init {
            serieNumber = itemView.findViewById(R.id.tvSerieNumberMember)
            weight = itemView.findViewById(R.id.tvWeightMember)
            reps = itemView.findViewById(R.id.tvRepsMember)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.member_row, parent, false)
        return MemberViewHolder(v)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        var serie = seriesList[position]

        holder.serieNumber.text = (position + 1).toString()
        holder.weight.text = serie.weight.toString()
        holder.reps.text = serie.reps.toString()
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }

}
