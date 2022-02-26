package com.cmhernandezdel.altruino.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cmhernandezdel.altruino.adapters.BluetoothDevicesListAdapter
import com.cmhernandezdel.altruino.viewmodels.BluetoothDeviceViewModel

object BluetoothDeviceListToRecyclerViewBindingAdapter {
    @BindingAdapter("itemViewModels")
    @JvmStatic
    fun bindItemViewModels(recyclerView: RecyclerView, viewModels: List<BluetoothDeviceViewModel>?){
        val adapter = getOrCreateAdapter(recyclerView)
        adapter.updateItems(viewModels)
    }

    private fun getOrCreateAdapter(recyclerView: RecyclerView): BluetoothDevicesListAdapter {
        return if (recyclerView.adapter != null && recyclerView.adapter is BluetoothDevicesListAdapter) {
            recyclerView.adapter as BluetoothDevicesListAdapter
        } else {
            val bindableRecyclerAdapter = BluetoothDevicesListAdapter()
            recyclerView.adapter = bindableRecyclerAdapter
            bindableRecyclerAdapter
        }
    }
}