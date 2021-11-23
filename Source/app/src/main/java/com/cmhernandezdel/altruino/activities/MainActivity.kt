package com.cmhernandezdel.altruino.activities

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import com.cmhernandezdel.altruino.R
import com.cmhernandezdel.altruino.modules.BluetoothModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : Activity() {
    private val upSignal = "F"
    private val downSignal = "B"
    private val leftSignal = "L"
    private val rightSignal = "R"
    private val bluetoothModule = BluetoothModule

    var buttonUp: Button? = null
    var buttonDown: Button? = null
    var buttonLeft: Button? = null
    var buttonRight: Button? = null
    var coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonUp = findViewById(R.id.button_up)
        buttonDown = findViewById(R.id.button_down)
        buttonLeft = findViewById(R.id.button_left)
        buttonRight = findViewById(R.id.button_right)

        buttonUp?.setOnClickListener { onUpButtonClick() }
        buttonDown?.setOnClickListener { onDownButtonClick() }
        buttonLeft?.setOnClickListener { onLeftButtonClick() }
        buttonRight?.setOnClickListener { onRightButtonClick() }

        bluetoothModule.setContext(this)
    }

    private fun onUpButtonClick() = coroutineScope.launch {
        bluetoothModule?.let { module ->
            val success = module.sendSignalAsync(upSignal)
        }
    }

    private fun onDownButtonClick() = coroutineScope.launch {
        bluetoothModule?.let { module ->
            val success = module.sendSignalAsync(downSignal)
        }
    }

    private fun onLeftButtonClick() = coroutineScope.launch {
        bluetoothModule?.let { module ->
            val success = module.sendSignalAsync(leftSignal)
        }
    }

    private fun onRightButtonClick() = coroutineScope.launch {
        bluetoothModule?.let { module ->
            val success = module.sendSignalAsync(rightSignal)
        }
    }
}