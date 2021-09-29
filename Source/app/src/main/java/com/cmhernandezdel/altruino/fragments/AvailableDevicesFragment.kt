package com.cmhernandezdel.altruino.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import com.cmhernandezdel.altruino.R
import com.cmhernandezdel.altruino.adapters.BluetoothDevicesListAdapter
import com.cmhernandezdel.altruino.modules.BluetoothModule

class AvailableDevicesFragment : Fragment() {
    private val classTag = "AvailableDevicesFragment.kt"
    private val bluetoothModule = BluetoothModule

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(classTag, "Fragment view created")
        val retView = inflater.inflate(R.layout.fragment_available_devices, container, false)
        bluetoothModule.setContext(requireContext())
        bluetoothModule.adapter = BluetoothDevicesListAdapter(requireContext(), bluetoothModule.availableDevices)

        bluetoothModule.let {
            val listView = retView.findViewById<ListView>(R.id.lv_available_devices)
            listView.adapter = it.adapter
            listView.onItemClickListener = AdapterView.OnItemClickListener {
                    _, _, position, _ ->
                val bluetoothDevice = it.availableDevices[position]
                Log.d(classTag, "Connecting to bluetooth device ${bluetoothDevice.name}")
                it.stopDiscovery()
                bluetoothDevice.createBond()
            }
        }
        return retView
    }

    override fun onResume() {
        super.onResume()
        Log.d(classTag, "Fragment resumed")
        bluetoothModule.startDiscovery()
    }
}