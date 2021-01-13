package com.example.lightweight.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lightweight.Adapters.SeriesAdapter
import com.example.lightweight.R
import com.example.lightweight.models.Serie
import kotlinx.android.synthetic.main.fragment_current_exercise.*


class CurrentExerciseFragment : Fragment(), View.OnClickListener {

    var seriesList = mutableListOf<Serie>(Serie())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnAddSeries).setOnClickListener(this)

        rvSeriesList.apply {

            layoutManager = LinearLayoutManager(activity)
            adapter = SeriesAdapter(seriesList)
        }
    }

    private fun addSerie() {
        var newSerie = Serie()
        seriesList.add(newSerie)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnAddSeries -> {
                addSerie()
            }
        }
    }


}