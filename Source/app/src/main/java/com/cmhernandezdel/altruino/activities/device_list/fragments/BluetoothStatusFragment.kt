package com.cmhernandezdel.altruino.activities.device_list.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.cmhernandezdel.altruino.R
import com.cmhernandezdel.altruino.databinding.FragmentBluetoothStatusBinding
import com.cmhernandezdel.altruino.viewmodels.BluetoothStatusViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BluetoothStatusFragment : Fragment(R.layout.fragment_bluetooth_status) {
    private val mViewModel: BluetoothStatusViewModel by viewModels()

    private val requestBluetoothPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (!isGranted) {
            showRationaleDialogForBluetoothPermission()
        }
    }

    private val requestBluetoothAdminPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (!isGranted) {
            showRationaleDialogForBluetoothAdminPermission()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_DENIED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.BLUETOOTH)) {
                showRationaleDialogForBluetoothPermission()
            } else {
                requestBluetoothPermissionLauncher.launch(Manifest.permission.BLUETOOTH)
            }
        }

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_DENIED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.BLUETOOTH_ADMIN)) {
                showRationaleDialogForBluetoothAdminPermission()
            } else {
                requestBluetoothAdminPermissionLauncher.launch(Manifest.permission.BLUETOOTH_ADMIN)
            }
        }

        val binding = FragmentBluetoothStatusBinding.bind(view)
        binding.apply {
            viewModel = mViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    private fun showRationaleDialogForBluetoothPermission() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle(R.string.bluetooth_status_fragment_rationale_bluetooth_dialog_title)
        alertDialogBuilder.setMessage(R.string.bluetooth_status_fragment_rationale_bluetooth_dialog_message)
        alertDialogBuilder.setPositiveButton(R.string.bluetooth_status_fragment_rationale_dialog_consent) { _, _ ->
            requestBluetoothPermissionLauncher.launch(Manifest.permission.BLUETOOTH)
        }
        alertDialogBuilder.setNegativeButton(R.string.bluetooth_status_fragment_rationale_dialog_reject) { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.show()
    }

    private fun showRationaleDialogForBluetoothAdminPermission() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle(R.string.bluetooth_status_fragment_rationale_bluetooth_admin_dialog_title)
        alertDialogBuilder.setMessage(R.string.bluetooth_status_fragment_rationale_bluetooth_admin_dialog_message)
        alertDialogBuilder.setPositiveButton(R.string.bluetooth_status_fragment_rationale_dialog_consent) { _, _ ->
            requestBluetoothPermissionLauncher.launch(Manifest.permission.BLUETOOTH_ADMIN)
        }
        alertDialogBuilder.setNegativeButton(R.string.bluetooth_status_fragment_rationale_dialog_reject) { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.show()
    }
}