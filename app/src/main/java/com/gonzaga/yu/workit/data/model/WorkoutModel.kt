package com.R.R.data.model

import java.io.Serializable
import java.util.*

class WorkoutModel(
    var workout_name: String,
    var day: Int,
    var exerciseModels: ArrayList<ExerciseModel>
) : Serializable