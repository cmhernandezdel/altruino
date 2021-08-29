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
    private val upSignal = "4"
    private val downSignal = "2"
    private val leftSignal = "1"
    private val rightSignal = "3"

    var buttonUp: Button? = null
    var buttonDown: Button? = null
    var buttonLeft: Button? = null
    var buttonRight: Button? = null
    var bluetoothModule: BluetoothModule? = null
    var coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buttonUp = findViewById(R.id.button_up)
        buttonDown = findViewById(R.id.button_down)
        buttonLeft = findViewById(R.id.button_left)
        buttonRight = findViewById(R.id.button_right)

        buttonUp?.setOnClickListener { onUpButtonClick() }
        buttonDown?.setOnClickListener { onDownButtonClick() }
        buttonLeft?.setOnClickListener { onLeftButtonClick() }
        buttonRight?.setOnClickListener { onRightButtonClick() }
    }

    private fun onUpButtonClick() = coroutineScope.launch {
        bluetoothModule?.let { module ->
            val success = module.sendSignalAsync(upSignal).await()
        }
    }

    private fun onDownButtonClick() = coroutineScope.launch {
        bluetoothModule?.let { module ->
            val success = module.sendSignalAsync(downSignal).await()
        }
    }

    private fun onLeftButtonClick() = coroutineScope.launch {
        bluetoothModule?.let { module ->
            val success = module.sendSignalAsync(leftSignal).await()
        }
    }

    private fun onRightButtonClick() = coroutineScope.launch {
        bluetoothModule?.let { module ->
            val success = module.sendSignalAsync(rightSignal).await()
        }
    }
}