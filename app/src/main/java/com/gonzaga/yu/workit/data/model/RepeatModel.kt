package com.R.R.data.model

import java.io.Serializable

class RepeatModel(var day: String, selected: Boolean) : Serializable {

    var isSelected = false

    init {
        isSelected = selected
    }
}