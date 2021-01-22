package com.example.lightweight.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.lightweight.R
import com.example.lightweight.models.Exercise
import com.example.lightweight.models.Serie
import java.lang.Exception

class SeriesAdapter(var seriesList: MutableList<Serie>, var context: Context) : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

   inner class SeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
       var serieNumber: TextView
       var weight: EditText
       var reps: EditText
       var isDone: CheckBox

       init {
           serieNumber = itemView.findViewById(R.id.tvSerieNumber)
           weight = itemView.findViewById(R.id.etHowMuchWeight)
           reps = itemView.findViewById(R.id.etReps)
           isDone = itemView.findViewById(R.id.cbIsDone)
       }
   }
    
    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        var serieNr = (position + 1).toString()
        holder.serieNumber.text = serieNr

        var currentSerie = seriesList[position]

        holder.isDone.setOnClickListener{

            try {
                currentSerie.weight = holder.weight.text.toString().toDouble()
                currentSerie.reps = holder.reps.text.toString().toDouble()

            }catch (e: Exception){
                Toast.makeText(context, "Nieprawidłowo podana wartość", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.series_row, parent, false)
        return SeriesViewHolder(v)
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }


}