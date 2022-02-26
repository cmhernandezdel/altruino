package com.cmhernandezdel.altruino.viewmodels

import android.bluetooth.BluetoothDevice
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmhernandezdel.altruino.providers.IBluetoothConnectionProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BondedDevicesListViewModel @Inject constructor(private val bluetoothConnectionProvider: IBluetoothConnectionProvider): ViewModel() {
    val bondedDevices = MutableLiveData<List<BluetoothDevice>>()

    init {
        getBondedDevices()
    }

    fun getBondedDevices() = viewModelScope.launch {
        val devices = bluetoothConnectionProvider.getBondedDevices()
        bondedDevices.postValue(devices)
    }
}