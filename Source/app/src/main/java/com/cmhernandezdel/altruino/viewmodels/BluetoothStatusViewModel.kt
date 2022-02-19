package com.cmhernandezdel.altruino.viewmodels

import androidx.lifecycle.MutableLiveData
import com.cmhernandezdel.altruino.providers.IBluetoothStatusProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BluetoothStatusViewModel @Inject constructor(bluetoothStatusProvider: IBluetoothStatusProvider) {
    val isBluetoothEnabled = MutableLiveData(false)

}