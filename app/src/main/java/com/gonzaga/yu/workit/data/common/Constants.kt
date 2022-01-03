package com.R.R.data.common

import com.R.R.data.model.WorkoutModel
import com.R.R.data.model.ExerciseModel
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