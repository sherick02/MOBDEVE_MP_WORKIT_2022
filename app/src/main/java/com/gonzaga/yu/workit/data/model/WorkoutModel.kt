package com.gonzaga.yu.workit.data.model

import com.gonzaga.yu.workit.data.model.ExerciseModel
import java.io.Serializable
import java.util.*

class WorkoutModel(
    var workout_name: String,
    var day: Int,
    var exerciseModels: ArrayList<ExerciseModel>
) : Serializable