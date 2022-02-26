package com.cmhernandezdel.altruino.adapters

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.cmhernandezdel.altruino.BR
import com.cmhernandezdel.altruino.viewmodels.BluetoothDeviceViewModel

class BluetoothDeviceViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(viewModel: BluetoothDeviceViewModel) {
        binding.setVariable(BR.viewModel, viewModel)
    }
}