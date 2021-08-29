package com.cmhernandezdel.altruino.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cmhernandezdel.altruino.R

// to pair: 1234
// 9600 bps
// 1 left 4 up 3 right 2 down
class DeviceListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_list)
    }
}