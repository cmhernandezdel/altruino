package com.cmhernandezdel.altruino.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.cmhernandezdel.altruino.R
import com.cmhernandezdel.altruino.viewmodels.BluetoothDeviceViewModel

class BondedDevicesListAdapter: RecyclerView.Adapter<BluetoothDeviceViewHolder>() {
    var bluetoothViewModels : List<BluetoothDeviceViewModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BluetoothDeviceViewHolder {
        val binding : ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_bluetooth_device, parent, false)
        return BluetoothDeviceViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return bluetoothViewModels.size
    }

    override fun onBindViewHolder(holder: BluetoothDeviceViewHolder, position: Int) {
        holder.bind(bluetoothViewModels[position])
    }

    fun updateItems(items: List<BluetoothDeviceViewModel>?){
        bluetoothViewModels = items ?: emptyList()
        notifyDataSetChanged()
    }
}