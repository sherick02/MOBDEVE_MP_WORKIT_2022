package com.gonzaga.yu.workit.data.model

import java.io.Serializable

class ExerciseModel : Serializable {
    var day = 0
    var workout_name: String? = null
    var title: String? = null
    var des: String? = null
    var reps = 0
    var sets = 0
    var rest_time = 0
}