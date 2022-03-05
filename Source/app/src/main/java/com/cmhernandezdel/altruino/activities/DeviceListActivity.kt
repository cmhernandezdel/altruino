package com.cmhernandezdel.altruino.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.cmhernandezdel.altruino.R
import com.cmhernandezdel.altruino.adapters.DeviceListActivityPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// to pair: 1234
// 9600 bps
// 1 left 4 up 3 right 2 down
class DeviceListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_list)

        val pagerAdapter = DeviceListActivityPagerAdapter(this)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = findViewById<ViewPager2>(R.id.pager)
        viewPager.adapter = pagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.view_pager_title_status)
                1 -> getString(R.string.view_pager_title_available)
                else -> getString(R.string.view_pager_title_bonded)
            }
        }.attach()
    }

    private val onDeviceClicked = AdapterView.OnItemClickListener { _, view, _, _ ->
        val info = (view as TextView).text.toString()
        val address = info.substring(info.length - 17)
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("DEVICE_ADDRESS", address)
        startActivity(intent)
    }
}