package com.cmhernandezdel.altruino.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import com.cmhernandezdel.altruino.R

class MainActivity : Activity() {
    private val forwardSignal = "F"
    private val backwardsSignal = "B"
    private val leftSignal = "L"
    private val rightSignal = "R"
    private val stopSignal = "\u0000"

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}