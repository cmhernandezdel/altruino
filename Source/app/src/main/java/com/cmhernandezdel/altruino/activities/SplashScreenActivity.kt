package com.cmhernandezdel.altruino.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.cmhernandezdel.altruino.R

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