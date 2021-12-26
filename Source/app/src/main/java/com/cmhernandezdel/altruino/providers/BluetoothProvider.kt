package com.cmhernandezdel.altruino.providers

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket

class BluetoothProvider : IBluetoothStatusProvider, IBluetoothConnectionProvider {
    override fun enableBluetooth() {
        TODO("Not yet implemented")
    }

    override fun disableBluetooth() {
        TODO("Not yet implemented")
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