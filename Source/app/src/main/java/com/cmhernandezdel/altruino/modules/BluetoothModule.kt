package com.cmhernandezdel.altruino.modules

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import com.cmhernandezdel.altruino.adapters.BluetoothDevicesListAdapter
import kotlinx.coroutines.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class BluetoothModule(private val context: Context) {
    private val classTag = "BluetoothModule.kt"

    // DISCOVERY
    val availableDevices = ArrayList<BluetoothDevice>()
    private val discoveryReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                BluetoothDevice.ACTION_FOUND -> {
                    val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    if (!availableDevices.contains(device!!)) availableDevices.add(device)
                    adapter.notifyDataSetChanged()
                    Log.i(classTag, "Found device ${device.name}")
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
    
    private val bluetoothUUID = UUID.fromString("e568e2da-c7e1-4d84-8c35-fdd14307fbd1")
    private var bluetoothAdapter: BluetoothAdapter? = null
    private var bluetoothSocket: BluetoothSocket? = null

    val adapter = BluetoothDevicesListAdapter(context, availableDevices)

    fun getBondedDevices(): ArrayList<BluetoothDevice> {
        val retList = ArrayList<BluetoothDevice>()
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        bluetoothAdapter?.let {
            retList.addAll(it.bondedDevices)
        }
        return retList
    }

    fun startDiscovery() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        bluetoothAdapter?.let {
            Log.i(classTag, "Starting discovery of bluetooth devices...")
            if (it.isDiscovering) it.cancelDiscovery()
            it.startDiscovery()
            val filter = IntentFilter()
            filter.addAction(BluetoothDevice.ACTION_FOUND)
            filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
            filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
            context.registerReceiver(discoveryReceiver, filter)
        }
    }

    fun stopDiscovery() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        bluetoothAdapter?.cancelDiscovery()
    }

    fun enableBluetooth(): Boolean {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        bluetoothAdapter?.let {
            return it.enable()
        }
        return false
    }

    fun isBluetoothEnabled(): Boolean {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter == null) {
            Log.i(classTag, "Bluetooth adapter is null")
            return false
        } else {
            Log.i(classTag, "Bluetooth adapter is not null")
            return bluetoothAdapter!!.isEnabled
        }
    }

    suspend fun connectToBluetoothAsync(device: BluetoothDevice): Boolean = withContext(Dispatchers.IO) {
        if (bluetoothSocket == null) {
            try {
                bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
                bluetoothAdapter?.let { adapter ->
                    adapter.cancelDiscovery()
                    bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(bluetoothUUID)
                    bluetoothSocket?.connect()
                    return@withContext true
                } ?: Log.w(classTag, "Bluetooth adapter is null in connectToBluetoothAsync")
                return@withContext false
            } catch (e: IOException) {
                Log.w(classTag, "Exception connecting to bluetooth: ${e.message}")
                return@withContext false
            }
        } else {
            return@withContext true
        }
    }

    suspend fun sendSignalAsync(signal: String): Boolean = withContext(Dispatchers.IO) {
        bluetoothSocket?.let { socket ->
            try {
                socket.outputStream.write(signal.encodeToByteArray())
                return@withContext true
            } catch (e: IOException) {
                return@withContext false
            }
        } ?: run {
            return@withContext false
        }
    }
}