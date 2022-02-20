package com.cmhernandezdel.altruino.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.cmhernandezdel.altruino.exceptions.BluetoothNotAvailableException
import com.cmhernandezdel.altruino.providers.IBluetoothStatusProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BluetoothStatusViewModel @Inject constructor(private val bluetoothStatusProvider: IBluetoothStatusProvider) : ViewModel() {
    private val classTag = "BluetoothStatusVM.kt"
    val bluetoothStatus = bluetoothStatusProvider.getBluetoothStatusAsLiveData()

    fun enableBluetooth() {
        try {
            bluetoothStatusProvider.enableBluetooth()
        } catch (exception: BluetoothNotAvailableException) {
            Log.e(classTag, "Bluetooth is not available on this device")
        }
    }
}