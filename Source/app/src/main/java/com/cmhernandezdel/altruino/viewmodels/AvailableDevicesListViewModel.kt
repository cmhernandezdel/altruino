package com.cmhernandezdel.altruino.viewmodels

import android.bluetooth.BluetoothDevice
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmhernandezdel.altruino.providers.IBluetoothConnectionProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvailableDevicesListViewModel @Inject constructor(private val bluetoothConnectionProvider: IBluetoothConnectionProvider) : ViewModel() {

    val availableDevices: Flow<BluetoothDeviceViewModel>

    init {
        startBluetoothDiscovery()
        val viewModelFlow = bluetoothConnectionProvider.getAvailableDevicesAsFlow().transform<BluetoothDevice, BluetoothDeviceViewModel> {
            BluetoothDeviceViewModel(it)
        }
        availableDevices = viewModelFlow
    }

    private fun startBluetoothDiscovery() = viewModelScope.launch {
        bluetoothConnectionProvider.startBluetoothDiscovery()
    }
}