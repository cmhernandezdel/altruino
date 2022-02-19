package com.cmhernandezdel.altruino.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.cmhernandezdel.altruino.R
import com.cmhernandezdel.altruino.modules.BluetoothModule
import com.cmhernandezdel.altruino.viewmodels.BluetoothStatusViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BluetoothStatusFragment : Fragment(R.layout.fragment_bluetooth_status) {
    private val classTag = "BluetoothStatusFragment.kt"
    private val mViewModel: BluetoothStatusViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO bluetooth permission

        val binding = BluetoothStatusFragmentBinding.bind(view)
        binding.apply {
            viewModel = mViewModel
            lifecycleOwner = viewLifecycleOwner
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
        Log.i(classTag, "Loading bluetooth disabled UI")
        llBluetoothEnabled?.visibility = View.GONE
        llBluetoothDisabled?.visibility = View.VISIBLE
    }

    private fun loadBluetoothEnabledUI() {
        Log.i(classTag, "Loading bluetooth enabled UI")
        llBluetoothDisabled?.visibility = View.GONE
        llBluetoothEnabled?.visibility = View.VISIBLE
    }

    private fun isBluetoothPermissionGranted(): Boolean {
        context?.let {
            val bluetoothPermissionGranted = ContextCompat.checkSelfPermission(it, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED
            val bluetoothAdminPermissionGranted = ContextCompat.checkSelfPermission(it, Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED
            Log.i(classTag, "Permissions granted: BT: $bluetoothPermissionGranted, BT ADMIN: $bluetoothAdminPermissionGranted")
            return bluetoothPermissionGranted && bluetoothAdminPermissionGranted
        }
        return false
    }

    private fun onEnableBluetoothButtonClick() {
        if (bluetoothModule.enableBluetooth()) {
            loadBluetoothEnabledUI()
        }
    }

}