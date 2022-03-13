package com.cmhernandezdel.altruino.viewmodels

import android.bluetooth.BluetoothDevice
import androidx.lifecycle.*
import com.cmhernandezdel.altruino.providers.IBluetoothConnectionProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvailableDevicesListViewModel @Inject constructor(private val bluetoothConnectionProvider: IBluetoothConnectionProvider) : ViewModel() {

    private val availableDevicesFlow: Flow<BluetoothDeviceViewModel>
    private val _devices = MutableLiveData<ArrayList<BluetoothDeviceViewModel>>(arrayListOf())
    val devices: LiveData<ArrayList<BluetoothDeviceViewModel>> by this::_devices

    init {
        startBluetoothDiscovery()
        val viewModelFlow = bluetoothConnectionProvider.getAvailableDevicesAsFlow().transform<BluetoothDevice, BluetoothDeviceViewModel> {
            BluetoothDeviceViewModel(it)
        }
        availableDevicesFlow = viewModelFlow
    }

    private fun startBluetoothDiscovery() = viewModelScope.launch {
        bluetoothConnectionProvider.startBluetoothDiscovery()
        availableDevicesFlow.collect {
            if (_devices.value != null) {
                val list = _devices.value!!
                list.add(it)
                _devices.postValue(list)
            } else {
                _devices.postValue(arrayListOf(it))
            }
        }
    }
}