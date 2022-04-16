package com.cmhernandezdel.altruino.viewmodels

import android.bluetooth.BluetoothDevice
import android.util.Log
import androidx.lifecycle.MutableLiveData

class BluetoothDeviceViewModel(private val bluetoothDevice: BluetoothDevice) {
    private val classTag = "BluetoothDeviceViewModel.kt"
    val name = MutableLiveData<String>()
    val address = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>(false)

    init {
        Log.i(classTag, "Creating new device ${bluetoothDevice.name}, ${bluetoothDevice.address}")
        name.postValue(bluetoothDevice.name)
        address.postValue(bluetoothDevice.address)
    }
}