package com.example.lightweight.models

class Exercise(var name: String, var target: String, val imageResource: Int?) {
    var series = mutableListOf<Serie>()
}