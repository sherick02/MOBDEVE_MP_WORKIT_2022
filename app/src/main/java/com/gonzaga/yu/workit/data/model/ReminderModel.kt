package com.gonzaga.yu.workit.data.model

import java.io.Serializable
import java.util.*

class ReminderModel : Serializable {
    var title: String? = null
    var time: Long = 0
    var repeat: ArrayList<Int>? = null
    var is_on = false
}