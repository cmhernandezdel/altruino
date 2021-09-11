package com.cmhernandezdel.altruino.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cmhernandezdel.altruino.R
import com.cmhernandezdel.altruino.fragments.AvailableDevicesFragment
import com.cmhernandezdel.altruino.fragments.BluetoothStatusFragment
import com.cmhernandezdel.altruino.fragments.BondedDevicesFragment

class DeviceListActivityPagerAdapter(manager: FragmentManager, private val context: Context) :
    FragmentPagerAdapter(manager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> BluetoothStatusFragment()
            1 -> AvailableDevicesFragment()
            else -> BondedDevicesFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position) {
            0 -> context.getString(R.string.view_pager_title_status)
            1 -> context.getString(R.string.view_pager_title_available)
            else -> context.getString(R.string.view_pager_title_bonded)
        }
    }

}