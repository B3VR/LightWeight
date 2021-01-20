package com.example.lightweight.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lightweight.R
import com.example.lightweight.models.Exercise
import com.example.lightweight.models.Serie

class SeriesAdapter(var seriesList: MutableList<Serie>) : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

   inner class SeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
       var serieNumber: TextView

       init {
           serieNumber = itemView.findViewById(R.id.tvSerieNumber)
       }
   }
    
    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        var serieNr = (position + 1).toString()

        holder.serieNumber.text = serieNr
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.series_row, parent, false)
        return SeriesViewHolder(v)
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }


}