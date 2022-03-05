package com.cmhernandezdel.altruino.activities.device_list.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.cmhernandezdel.altruino.R
import com.cmhernandezdel.altruino.viewmodels.BluetoothStatusViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BluetoothStatusFragment : Fragment(R.layout.fragment_bluetooth_status) {
    private val classTag = "BluetoothStatusFrag.kt"
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

    private fun isBluetoothPermissionGranted(): Boolean {
        context?.let {
            val bluetoothPermissionGranted = ContextCompat.checkSelfPermission(it, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED
            val bluetoothAdminPermissionGranted = ContextCompat.checkSelfPermission(it, Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED
            Log.i(classTag, "Permissions granted: BT: $bluetoothPermissionGranted, BT ADMIN: $bluetoothAdminPermissionGranted")
            return bluetoothPermissionGranted && bluetoothAdminPermissionGranted
        }
        return false
    }
}