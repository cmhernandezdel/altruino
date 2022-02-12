package com.cmhernandezdel.altruino.providers

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.cmhernandezdel.altruino.exceptions.BluetoothNotAvailableException
import java.util.*

class BluetoothProvider : IBluetoothStatusProvider, IBluetoothConnectionProvider {
    private val classTag = "BluetoothProvider.kt"
    private val bluetoothUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
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
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() ?: throw BluetoothNotAvailableException("This device has no bluetooth adapter")
        return bluetoothAdapter.isEnabled
    }

    override fun isBluetoothAvailable(): Boolean {
        BluetoothAdapter.getDefaultAdapter() ?: return false
        return true
    }

    override fun startBluetoothDiscovery() {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() ?: throw BluetoothNotAvailableException("This device has no bluetooth adapter")
        bluetoothAdapter.startDiscovery()
    }

    override fun cancelBluetoothDiscovery() {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() ?: throw BluetoothNotAvailableException("This device has no bluetooth adapter")
        if (bluetoothAdapter.isDiscovering) {
            bluetoothAdapter.cancelDiscovery()
        }
    }

    override fun getBondedDevices(): List<BluetoothDevice> {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() ?: throw BluetoothNotAvailableException("This device has no bluetooth adapter")
        return bluetoothAdapter.bondedDevices.toList()
    }

    override fun connect(device: BluetoothDevice): BluetoothSocket {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() ?: throw BluetoothNotAvailableException("This device has no bluetooth adapter")
        bluetoothAdapter.cancelDiscovery()
        val bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(bluetoothUUID)
        bluetoothSocket?.connect()
        return bluetoothSocket
    }

    override fun send(message: String, socket: BluetoothSocket) {
        socket.outputStream.write(message.encodeToByteArray())
    }
}