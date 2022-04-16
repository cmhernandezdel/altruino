package com.cmhernandezdel.altruino.viewmodels

import android.bluetooth.BluetoothDevice
import android.util.Log
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

    private val classTag = "AvailableDevicesListViewModel.kt"
    private val _devices = MutableLiveData<ArrayList<BluetoothDeviceViewModel>>(arrayListOf())
    val devices: LiveData<ArrayList<BluetoothDeviceViewModel>> by this::_devices

    init {
        viewModelScope.launch {
            startBluetoothDiscovery()
            bluetoothConnectionProvider.getAvailableDevicesAsFlow().collect {
                val currentList = _devices.value
                val vm = BluetoothDeviceViewModel(it)
                if(currentList != null) {
                    currentList.add(vm)
                    _devices.postValue(currentList!!)
                }
                else{
                    _devices.postValue(arrayListOf(vm))
                }
            }
        }
    }

    private fun startBluetoothDiscovery() = viewModelScope.launch {
        Log.d(classTag, "Starting bluetooth discovery")
        bluetoothConnectionProvider.startBluetoothDiscovery()
    }
}