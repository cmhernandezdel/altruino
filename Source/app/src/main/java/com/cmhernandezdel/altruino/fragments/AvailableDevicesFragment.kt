package com.cmhernandezdel.altruino.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.cmhernandezdel.altruino.R
import com.cmhernandezdel.altruino.adapters.BluetoothDevicesListAdapter
import com.cmhernandezdel.altruino.modules.BluetoothModule

class AvailableDevicesFragment : Fragment() {
    private var bluetoothModule: BluetoothModule? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val retView = inflater.inflate(R.layout.fragment_available_devices, container, false)
        bluetoothModule = BluetoothModule(requireContext())
        bluetoothModule?.let {
            val adapter = BluetoothDevicesListAdapter(requireContext(), it.availableDevices)
            val listView = retView.findViewById<ListView>(R.id.lv_available_devices)
            listView.adapter = adapter
            it.startDiscovery()
        }
        return retView
    }
}