package com.cmhernandezdel.altruino.activities.device_list

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.cmhernandezdel.altruino.R
import com.cmhernandezdel.altruino.activities.main.MainActivity
import com.cmhernandezdel.altruino.adapters.DeviceListActivityPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

// to pair: 1234
// 9600 bps
// 1 left 4 up 3 right 2 down
@AndroidEntryPoint
class DeviceListActivity : AppCompatActivity() {

    private val classTag = "DeviceListActivity.kt"

    private val permissionsLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            Log.d(classTag, "Permission granted")
        }
    }

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


        val bluetoothAdminPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED
        val bluetoothPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED
        val locationPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

        if (!bluetoothAdminPermissionGranted) {
            permissionsLauncher.launch(Manifest.permission.BLUETOOTH_ADMIN)
        }

        if (!bluetoothPermissionGranted) {
            permissionsLauncher.launch(Manifest.permission.BLUETOOTH)
        }

        if (!locationPermissionGranted) {
            permissionsLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

    }

    private val onDeviceClicked = AdapterView.OnItemClickListener { _, view, _, _ ->
        val info = (view as TextView).text.toString()
        val address = info.substring(info.length - 17)
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("DEVICE_ADDRESS", address)
        startActivity(intent)
    }
}