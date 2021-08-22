package com.cmhernandezdel.altruino.activities

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import com.cmhernandezdel.altruino.R

class MainActivity : Activity() {
    var buttonUp: Button? = null
    var buttonDown: Button? = null
    var buttonLeft: Button? = null
    var buttonRight: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buttonUp = findViewById(R.id.button_up)
        buttonDown = findViewById(R.id.button_down)
        buttonLeft = findViewById(R.id.button_left)
        buttonRight = findViewById(R.id.button_right)
    }

    private fun onUpButtonClick() {

    }

}