package com.cmhernandezdel.altruino.activities.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.cmhernandezdel.altruino.R
import com.cmhernandezdel.altruino.activities.device_list.DeviceListActivity

class SplashScreenActivity : AppCompatActivity() {
    private val handler = Handler(Looper.getMainLooper())
    private val delayInMilliseconds = 1000L

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