package com.cmhernandezdel.altruino.activities.splash_screen

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.cmhernandezdel.altruino.R
import com.cmhernandezdel.altruino.activities.device_list.DeviceListActivity

class SplashScreenActivity : AppCompatActivity() {
    private val handler = Handler(Looper.getMainLooper())
    private val delayInMilliseconds = 1000L
    private val classTag = "SplashScreenActivity.kt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler.postDelayed({
            val homeIntent = Intent(this, DeviceListActivity::class.java)
            startActivity(homeIntent)
            finish()
        }, delayInMilliseconds)
    }
}