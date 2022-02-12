package com.cmhernandezdel.altruino.providers

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import com.cmhernandezdel.altruino.exceptions.BluetoothNotAvailableException
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BluetoothProvider @Inject constructor(@ApplicationContext private val context: Context) : IBluetoothStatusProvider, IBluetoothConnectionProvider {
    private val classTag = "BluetoothProvider.kt"
    private val bluetoothUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    private val bluetoothBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                BluetoothAdapter.ACTION_STATE_CHANGED -> {
                    val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
                    when (state) {
                        BluetoothAdapter.STATE_OFF -> Log.i(classTag, "Bluetooth turned off")
                        BluetoothAdapter.STATE_ON -> Log.i(classTag, "Bluetooth turned on")
                    }
                }
                BluetoothDevice.ACTION_FOUND -> {
                    val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    // TODO: what do we do with this device?
                }
                BluetoothAdapter.ACTION_DISCOVERY_STARTED -> {
                    Log.i(classTag, "Discovery started")
                }
                BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                    Log.i(classTag, "Discovery finished")
                }
                BluetoothDevice.ACTION_BOND_STATE_CHANGED -> {
                    val newBondState = intent.getIntExtra(BluetoothDevice.ACTION_BOND_STATE_CHANGED, BluetoothDevice.BOND_NONE)
                    if (newBondState == BluetoothDevice.BOND_BONDED) {
                        Log.i(classTag, "Successfully bonded to device")
                    }
                }
            }
        }
    }

    init {
        val filter = IntentFilter()
        filter.addAction(BluetoothDevice.ACTION_FOUND)
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED)
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
        context.registerReceiver(bluetoothBroadcastReceiver, filter)
    }

    override fun enableBluetooth() {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() ?: throw BluetoothNotAvailableException("This device has no bluetooth adapter")
        val isTurningOn = bluetoothAdapter.enable()
        if (!isTurningOn) throw BluetoothNotAvailableException("Bluetooth could not turn on")
    }

    override fun disableBluetooth() {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() ?: throw BluetoothNotAvailableException("This device has no bluetooth adapter")
        val isTurningOff = bluetoothAdapter.disable()
        if (!isTurningOff) throw BluetoothNotAvailableException("Bluetooth could not turn off")
    }

    override fun isBluetoothEnabled(): Boolean {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() ?: throw BluetoothNotAvailableException("This device has no bluetooth adapter")
        return bluetoothAdapter.isEnabled
    }

    override fun isBluetoothAvailable(): Boolean {
        BluetoothAdapter.getDefaultAdapter() ?: return false
        return true
    }

    override fun startBluetoothDiscovery() {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() ?: throw BluetoothNotAvailableException("This device has no bluetooth adapter")
        bluetoothAdapter.startDiscovery()
    }

    override fun cancelBluetoothDiscovery() {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() ?: throw BluetoothNotAvailableException("This device has no bluetooth adapter")
        if (bluetoothAdapter.isDiscovering) {
            bluetoothAdapter.cancelDiscovery()
        }
    }

    override fun getBondedDevices(): List<BluetoothDevice> {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() ?: throw BluetoothNotAvailableException("This device has no bluetooth adapter")
        return bluetoothAdapter.bondedDevices.toList()
    }

    override fun connect(device: BluetoothDevice): BluetoothSocket {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() ?: throw BluetoothNotAvailableException("This device has no bluetooth adapter")
        bluetoothAdapter.cancelDiscovery()
        val bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(bluetoothUUID)
        bluetoothSocket?.connect()
        return bluetoothSocket
    }

    override fun send(message: String, socket: BluetoothSocket) {
        socket.outputStream.write(message.encodeToByteArray())
    }
}