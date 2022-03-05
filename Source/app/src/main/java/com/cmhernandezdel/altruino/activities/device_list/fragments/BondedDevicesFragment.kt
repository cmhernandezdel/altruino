package com.cmhernandezdel.altruino.activities.device_list.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import com.cmhernandezdel.altruino.R
import com.cmhernandezdel.altruino.activities.main.MainActivity
import com.cmhernandezdel.altruino.adapters.BluetoothDevicesListAdapter
import com.cmhernandezdel.altruino.modules.BluetoothModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BondedDevicesFragment : Fragment() {
    private val bluetoothModule = BluetoothModule
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val retView = inflater.inflate(R.layout.fragment_bonded_devices, container, false)
        val bondedDevices = bluetoothModule.getBondedDevices()
        val adapter = BluetoothDevicesListAdapter(requireContext(), bondedDevices)
        val listView = retView.findViewById<ListView>(R.id.lv_bonded_devices)
        listView.adapter = adapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            scope.launch {
                val success = bluetoothModule.connectToBluetoothAsync(bondedDevices[position])
                if (success) {
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        return retView
    }
}