package com.cmhernandezdel.altruino.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import com.cmhernandezdel.altruino.R
import com.cmhernandezdel.altruino.modules.BluetoothModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : Activity() {
    private val forwardSignal = "F"
    private val backwardsSignal = "B"
    private val leftSignal = "L"
    private val rightSignal = "R"
    private val stopSignal = "\u0000"
    private val bluetoothModule = BluetoothModule

    var buttonUp: Button? = null
    var buttonDown: Button? = null
    var buttonLeft: Button? = null
    var buttonRight: Button? = null
    var coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonUp = findViewById(R.id.button_up)
        buttonDown = findViewById(R.id.button_down)
        buttonLeft = findViewById(R.id.button_left)
        buttonRight = findViewById(R.id.button_right)

        buttonUp?.setOnTouchListener { _, event ->
            if(event.action == MotionEvent.ACTION_DOWN){
                onButtonPress(forwardSignal)
                true
            }
            else if(event.action == MotionEvent.ACTION_UP){
                onButtonRelease()
                true
            }
            else false
        }

        buttonDown?.setOnTouchListener { _, event ->
            if(event.action == MotionEvent.ACTION_DOWN){
                onButtonPress(backwardsSignal)
                true
            }
            else if(event.action == MotionEvent.ACTION_UP){
                onButtonRelease()
                true
            }
            else false
        }

        buttonLeft?.setOnTouchListener { _, event ->
            if(event.action == MotionEvent.ACTION_DOWN){
                onButtonPress(leftSignal)
                true
            }
            else if(event.action == MotionEvent.ACTION_UP){
                onButtonRelease()
                true
            }
            else false
        }

        buttonRight?.setOnTouchListener { _, event ->
            if(event.action == MotionEvent.ACTION_DOWN){
                onButtonPress(rightSignal)
                true
            }
            else if(event.action == MotionEvent.ACTION_UP){
                onButtonRelease()
                true
            }
            else false
        }

        bluetoothModule.setContext(this)
    }

    private fun onButtonPress(signal: String) = coroutineScope.launch {
        bluetoothModule.sendSignalAsync(signal)
    }

    private fun onButtonRelease() = coroutineScope.launch {
        bluetoothModule.sendSignalAsync(stopSignal)
    }
}