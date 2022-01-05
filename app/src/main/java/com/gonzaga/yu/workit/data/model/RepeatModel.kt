package com.gonzaga.yu.workit.data.model

import java.io.Serializable

class RepeatModel(var day: String, selected: Boolean) : Serializable {

    var isSelected = false

    init {
        isSelected = selected
    }
}