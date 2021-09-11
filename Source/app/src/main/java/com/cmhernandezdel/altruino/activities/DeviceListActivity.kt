package com.cmhernandezdel.altruino.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.cmhernandezdel.altruino.R
import com.cmhernandezdel.altruino.adapters.DeviceListActivityPagerAdapter
import com.google.android.material.tabs.TabLayout

// to pair: 1234
// 9600 bps
// 1 left 4 up 3 right 2 down
class DeviceListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_list)

        val pagerAdapter = DeviceListActivityPagerAdapter(supportFragmentManager, applicationContext)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = findViewById<ViewPager>(R.id.pager)
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        /*
        val deviceListView = findViewById<ListView>(R.id.bluetooth_devices_list)
        val list = ArrayList<String>()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        deviceListView.adapter = adapter
        deviceListView.onItemClickListener = onDeviceClicked
        */
    }

    private val onDeviceClicked = AdapterView.OnItemClickListener { _, view, _, _ ->
        val info = (view as TextView).text.toString()
        val address = info.substring(info.length - 17)
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("DEVICE_ADDRESS", address)
        startActivity(intent)
    }
}