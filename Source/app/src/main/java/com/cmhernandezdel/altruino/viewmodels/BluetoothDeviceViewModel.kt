package com.cmhernandezdel.altruino.viewmodels

import android.bluetooth.BluetoothDevice
import androidx.lifecycle.MutableLiveData

class BluetoothDeviceViewModel(private val bluetoothDevice: BluetoothDevice) {
    val name = MutableLiveData<String>()
    val address = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>(false)

    init {
        name.postValue(bluetoothDevice.name)
        address.postValue(bluetoothDevice.address)
    }
}