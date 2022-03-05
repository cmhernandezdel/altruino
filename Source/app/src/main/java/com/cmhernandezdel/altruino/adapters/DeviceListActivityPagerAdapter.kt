package com.cmhernandezdel.altruino.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cmhernandezdel.altruino.activities.device_list.fragments.AvailableDevicesFragment
import com.cmhernandezdel.altruino.activities.device_list.fragments.BluetoothStatusFragment
import com.cmhernandezdel.altruino.activities.device_list.fragments.BondedDevicesFragment

class DeviceListActivityPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BluetoothStatusFragment()
            1 -> AvailableDevicesFragment()
            else -> BondedDevicesFragment()
        }
    }
}