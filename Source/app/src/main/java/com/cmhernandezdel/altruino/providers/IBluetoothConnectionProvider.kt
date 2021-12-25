package com.cmhernandezdel.altruino.providers

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import com.cmhernandezdel.altruino.exceptions.BluetoothConnectionException
import com.cmhernandezdel.altruino.exceptions.BluetoothNotAvailableException
import com.cmhernandezdel.altruino.exceptions.BluetoothNotEnabledException
import com.cmhernandezdel.altruino.exceptions.BluetoothSocketException

interface IBluetoothConnectionProvider {
    /**
     * Starts discovering nearly bluetooth devices.
     * This method run asynchronously, register a BroadcastReceiver with BluetoothDevice.ACTION_FOUND,
     * BluetoothDevice.ACTION_DISCOVERY_STARTED, BluetoothDevice.ACTION_DISCOVERY_FINISHED,
     * BluetoothDevice.ACTION_BOND_STATE_CHANGED for listening to changes.
     * @throws BluetoothNotAvailableException If bluetooth is not available on the device.
     * @throws BluetoothNotEnabledException If bluetooth is not enabled on the device.
     */
    @Throws(BluetoothNotAvailableException::class, BluetoothNotEnabledException::class)
    fun startBluetoothDiscovery()

    /**
     * Cancels the discovery of nearly bluetooth devices.
     * This method run asynchronously, register a BroadcastReceiver with BluetoothDevice.ACTION_FOUND,
     * BluetoothDevice.ACTION_DISCOVERY_STARTED, BluetoothDevice.ACTION_DISCOVERY_FINISHED,
     * BluetoothDevice.ACTION_BOND_STATE_CHANGED for listening to changes.
     * @throws BluetoothNotAvailableException If bluetooth is not available on the device.
     * @throws BluetoothNotEnabledException If bluetooth is not enabled on the device.
     */
    @Throws(BluetoothNotAvailableException::class, BluetoothNotEnabledException::class)
    fun cancelBluetoothDiscovery()

    /**
     * Returns the list of bonded devices, i.e., the devices which this device can directly connect to.
     * @return A list containing all the bonded devices.
     * @throws BluetoothNotAvailableException If bluetooth is not available on the device.
     * @throws BluetoothNotEnabledException If bluetooth is not enabled on the device.
     */
    @Throws(BluetoothNotAvailableException::class, BluetoothNotEnabledException::class)
    fun getBondedDevices(): List<BluetoothDevice>

    /**
     * Connects to a device and returns the socket where the communication will happen.
     * @param device The device to connect to.
     * @return The socket that this device can use for sending and receiving information.
     * @throws BluetoothNotAvailableException If bluetooth is not available on the device.
     * @throws BluetoothNotEnabledException If bluetooth is not enabled on the device.
     * @throws BluetoothSocketException If there is any problem while creating the socket.
     * @throws BluetoothConnectionException If there is any problem while connecting to the socket.
     */
    @Throws(BluetoothNotAvailableException::class, BluetoothNotEnabledException::class, BluetoothSocketException::class, BluetoothConnectionException::class)
    fun connect(device: BluetoothDevice): BluetoothSocket

    /**
     * Sends a message through the socket.
     * @param message The message to send.
     * @param socket The socket this device will write the message to.
     * @throws BluetoothNotAvailableException If bluetooth is not available on the device.
     * @throws BluetoothNotEnabledException If bluetooth is not enabled on the device.
     * @throws BluetoothConnectionException If there is any problem while writing to the socket.
     */
    @Throws(BluetoothNotAvailableException::class, BluetoothNotEnabledException::class, BluetoothConnectionException::class)
    fun send(message: String, socket: BluetoothSocket)
}