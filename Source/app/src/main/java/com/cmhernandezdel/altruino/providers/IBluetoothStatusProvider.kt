package com.cmhernandezdel.altruino.providers

import androidx.lifecycle.LiveData
import com.cmhernandezdel.altruino.exceptions.BluetoothNotAvailableException
import com.cmhernandezdel.altruino.models.enums.BluetoothStatus

interface IBluetoothStatusProvider {
    /**
     * Enables bluetooth on the device.
     * @throws BluetoothNotAvailableException If bluetooth is not available on the device.
     */
    @Throws(BluetoothNotAvailableException::class)
    fun enableBluetooth()

    /**
     * Disables bluetooth on the device.
     * @throws BluetoothNotAvailableException If bluetooth is not available on the device.
     */
    @Throws(BluetoothNotAvailableException::class)
    fun disableBluetooth()

    /**
     * Returns whether bluetooth is enabled or not.
     * If bluetooth is not available, this function will return false.
     * @return True if bluetooth is enabled, false otherwise.
     */
    fun isBluetoothEnabled(): Boolean

    /**
     * Returns whether bluetooth is available on this device.
     * @return True if bluetooth is available, false otherwise.
     */
    fun isBluetoothAvailable(): Boolean

    /**
     * Returns the status of the bluetooth adapter as a live data.
     * @return A live data containing the bluetooth status.
     */
    fun getBluetoothStatusAsLiveData(): LiveData<BluetoothStatus>
}