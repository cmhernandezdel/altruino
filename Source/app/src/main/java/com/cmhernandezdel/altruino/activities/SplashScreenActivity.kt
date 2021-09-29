package com.cmhernandezdel.altruino.activities

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.cmhernandezdel.altruino.R
import java.io.IOException
import java.util.*

class SplashScreenActivity : AppCompatActivity() {
    private val handler = Handler(Looper.getMainLooper())
    private val delayInMilliseconds = 1000L
    private val classTag = "SplashScreenActivity.kt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q) {
            val buffer = ByteArray(10)
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            val socket = bluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord("What?", UUID.fromString("e568e2da-c7e1-4d84-8c35-fdd14307fbd1"))
            val connectedSocket = socket.accept()
            Log.i(classTag, "Connection established")
            while (true) {
                try {
                    connectedSocket.inputStream.read(buffer)
                    val text = String(buffer, 0, buffer.size)
                    Log.i(classTag, "Received data: $text")
                } catch (exception: IOException) {
                    break
                }
            }

        } else {
            handler.postDelayed({
                val homeIntent = Intent(this, DeviceListActivity::class.java)
                startActivity(homeIntent)
                finish()
            }, delayInMilliseconds)
        }
    }
}