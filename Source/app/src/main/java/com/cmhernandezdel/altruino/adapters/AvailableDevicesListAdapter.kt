package com.cmhernandezdel.altruino.adapters

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.cmhernandezdel.altruino.R

class AvailableDevicesListAdapter(context: Context, devices: ArrayList<BluetoothDevice>) : ArrayAdapter<BluetoothDevice>(context, 0, devices) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val device = getItem(position)
        val retView = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_bluetooth_device, parent, false)
        device?.let {
            val textViewName = retView.findViewById<TextView>(R.id.bluetooth_device_item_name)
            val textViewAddress = retView.findViewById<TextView>(R.id.bluetooth_device_item_address)
            textViewName.text = device.name
            textViewAddress.text = device.address
        }
        return retView
    }
}