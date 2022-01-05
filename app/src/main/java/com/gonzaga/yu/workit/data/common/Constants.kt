package com.gonzaga.yu.workit.data.common

import com.gonzaga.yu.workit.data.model.WorkoutModel
import com.gonzaga.yu.workit.data.model.ExerciseModel
import java.util.ArrayList

object Constants {
    const val MY_PREFS = "MyPrefs"

    @JvmField
    var days_selected = ArrayList<Int>()

    @JvmField
    var workoutModelArrayList = ArrayList<WorkoutModel>()

    @JvmField
    var exerciseModelArrayList = ArrayList<ExerciseModel>()
}