package com.mhamdi.trafficky.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mhamdi.trafficky.R
import com.mhamdi.trafficky.databinding.FragmentCarInputBinding
import com.mhamdi.trafficky.extensions.FragmentExtensions.viewLifecycle

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CarInputFragment : Fragment() {

    private var binding: FragmentCarInputBinding by viewLifecycle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStartDriving.setOnClickListener {
            findNavController().navigate(R.id.action_CarInputFragment_to_RoadLightsFragment)
        }
    }
}