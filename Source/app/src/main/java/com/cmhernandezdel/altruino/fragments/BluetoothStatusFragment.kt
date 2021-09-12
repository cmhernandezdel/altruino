package com.cmhernandezdel.altruino.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.cmhernandezdel.altruino.R
import com.cmhernandezdel.altruino.modules.BluetoothModule

class BluetoothStatusFragment : Fragment() {
    private val bluetoothModule: BluetoothModule = BluetoothModule()
    private var llBluetoothEnabled: LinearLayout? = null
    private var llBluetoothDisabled: LinearLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        llBluetoothEnabled = view?.findViewById(R.id.ll_enabled_bluetooth)
        llBluetoothDisabled = view?.findViewById(R.id.ll_disabled_bluetooth)
        return inflater.inflate(R.layout.fragment_bluetooth_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonEnableBluetooth = view.findViewById<Button>(R.id.button_enable_bluetooth)
        buttonEnableBluetooth.setOnClickListener { onEnableBluetoothButtonClick() }

        if (isBluetoothPermissionGranted() && bluetoothModule.isBluetoothEnabled()) {
            loadBluetoothEnabledUI()
        } else if (!isBluetoothPermissionGranted()) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN
                ), 12597
            )
        } else {
            loadBluetoothDisabledUI()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            12597 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadBluetoothEnabledUI()
                } else {
                    loadBluetoothDisabledUI()
                }
            }
            else -> {
                // ignore other permission requests
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun loadBluetoothDisabledUI() {
        llBluetoothEnabled?.visibility = View.GONE
        llBluetoothDisabled?.visibility = View.VISIBLE
    }

    private fun loadBluetoothEnabledUI() {
        llBluetoothDisabled?.visibility = View.GONE
        llBluetoothEnabled?.visibility = View.VISIBLE
    }

    private fun isBluetoothPermissionGranted(): Boolean {
        context?.let {
            return ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.BLUETOOTH
            ) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.BLUETOOTH_ADMIN
            ) == PackageManager.PERMISSION_GRANTED
        }
        return false
    }

    private fun onEnableBluetoothButtonClick() {
        if (bluetoothModule.enableBluetooth()) {
            loadBluetoothEnabledUI()
        }
    }

}