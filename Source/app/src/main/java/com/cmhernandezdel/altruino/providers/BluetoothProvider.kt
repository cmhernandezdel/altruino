package com.cmhernandezdel.altruino.providers

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.cmhernandezdel.altruino.exceptions.BluetoothNotAvailableException

class BluetoothProvider : IBluetoothStatusProvider, IBluetoothConnectionProvider {
    private val classTag = "BluetoothProvider.kt"
    private val bluetoothBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                BluetoothAdapter.ACTION_STATE_CHANGED -> {
                    val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
                    when (state) {
                        BluetoothAdapter.STATE_OFF -> Log.i(classTag, "Bluetooth turned off")
                        BluetoothAdapter.STATE_ON -> Log.i(classTag, "Bluetooth turned on")
                    }
                }
            }
        }
    }

    override fun enableBluetooth() {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() ?: throw BluetoothNotAvailableException("This device has no bluetooth adapter")
        val isTurningOn = bluetoothAdapter.enable()
        if (!isTurningOn) throw BluetoothNotAvailableException("Bluetooth could not turn on")
    }

    override fun disableBluetooth() {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() ?: throw BluetoothNotAvailableException("This device has no bluetooth adapter")
        val isTurningOff = bluetoothAdapter.disable()
        if (!isTurningOff) throw BluetoothNotAvailableException("Bluetooth could not turn off")
    }

    override fun isBluetoothEnabled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isBluetoothAvailable(): Boolean {
        TODO("Not yet implemented")
    }

    override fun startBluetoothDiscovery() {
        TODO("Not yet implemented")
    }

    override fun cancelBluetoothDiscovery() {
        TODO("Not yet implemented")
    }

    override fun getBondedDevices(): List<BluetoothDevice> {
        TODO("Not yet implemented")
    }

    override fun connect(device: BluetoothDevice): BluetoothSocket {
        TODO("Not yet implemented")
    }

    override fun send(message: String, socket: BluetoothSocket) {
        TODO("Not yet implemented")
    }
}