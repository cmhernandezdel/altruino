package com.cmhernandezdel.altruino.modules

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import kotlinx.coroutines.*
import java.io.IOException
import java.util.*

class BluetoothModule {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val bluetoothUUID = UUID.fromString("") // TODO
    private var bluetoothAdapter: BluetoothAdapter? = null
    private var bluetoothSocket: BluetoothSocket? = null

    fun isBluetoothEnabled(): Boolean {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter == null) {
            return false
        } else {
            return bluetoothAdapter!!.isEnabled
        }
    }

    fun connectToBluetoothAsync(address: String): Deferred<Boolean> = coroutineScope.async {
        if (bluetoothSocket == null) {
            try {
                bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
                bluetoothAdapter?.let { adapter ->
                    val device = adapter.getRemoteDevice(address)
                    bluetoothSocket =
                        device.createInsecureRfcommSocketToServiceRecord(bluetoothUUID)
                    adapter.cancelDiscovery()
                    bluetoothSocket?.connect()
                }
                return@async true
            } catch (e: IOException) {
                return@async false
            }
        } else {
            return@async true
        }
    }

    fun sendSignalAsync(signal: String): Deferred<Boolean> = coroutineScope.async {
        bluetoothSocket?.let { socket ->
            try {
                socket.outputStream.write(signal.encodeToByteArray())
                return@async true
            } catch (e: IOException) {
                return@async false
            }
        } ?: run {
            return@async false
        }
    }
}