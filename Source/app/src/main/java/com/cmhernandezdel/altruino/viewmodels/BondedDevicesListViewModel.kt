package com.cmhernandezdel.altruino.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmhernandezdel.altruino.providers.IBluetoothConnectionProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BondedDevicesListViewModel @Inject constructor(private val bluetoothConnectionProvider: IBluetoothConnectionProvider): ViewModel() {
    val bondedDevices = MutableLiveData<List<BluetoothDeviceViewModel>>()

    init {
        getBondedDevices()
    }

    private fun getBondedDevices() = viewModelScope.launch {
        val devices = bluetoothConnectionProvider.getBondedDevices()
        val viewModels = devices.map { BluetoothDeviceViewModel(it) }
        bondedDevices.postValue(viewModels)
    }
}