package com.cmhernandezdel.altruino.activities.device_list.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cmhernandezdel.altruino.R
import com.cmhernandezdel.altruino.databinding.FragmentAvailableDevicesBinding
import com.cmhernandezdel.altruino.viewmodels.AvailableDevicesListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AvailableDevicesFragment : Fragment(R.layout.fragment_available_devices) {
    private val classTag = "AvailableDevicesFragment.kt"
    private val mViewModel : AvailableDevicesListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      val binding = FragmentAvailableDevicesBinding.bind(view)
        binding.apply {
            viewModel = mViewModel
            lifecycleOwner = viewLifecycleOwner

            lvAvailableDevices.apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
    }
}