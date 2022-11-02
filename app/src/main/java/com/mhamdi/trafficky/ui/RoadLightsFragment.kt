package com.mhamdi.trafficky.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mhamdi.trafficky.LightStates
import com.mhamdi.trafficky.databinding.FragmentRoadLightsBinding
import com.mhamdi.trafficky.extensions.FragmentExtensions.viewLifecycle
import com.mhamdi.trafficky.extensions.ViewExtensions.updateTransitionState


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RoadLightsFragment : Fragment() {

    private var binding: FragmentRoadLightsBinding by viewLifecycle()

    private val viewModel: RoadLightsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoadLightsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.run {

            updateTrafficStates()

            uiState.observe(viewLifecycleOwner) {
                it?.let {
                    binding.run {
                        updateTrafficLightsUi(it)
                        buttonTryAgain.isEnabled(it)
                    }
                }
            }

            binding.buttonTryAgain.setOnClickListener {
                updateTrafficStates()
            }
        }
    }

    private fun Button.isEnabled(it: LightStates) {
        isEnabled = it == LightStates.RED
    }

    private fun FragmentRoadLightsBinding.updateTrafficLightsUi(state: LightStates) = when (state) {
        LightStates.GREEN -> {
            greenBulb.updateTransitionState(true)
            orangeBulb.updateTransitionState(false)
            redBulb.updateTransitionState(false)
        }
        LightStates.ORANGE -> {
            greenBulb.updateTransitionState(false)
            orangeBulb.updateTransitionState(true)
            redBulb.updateTransitionState(false)
        }
        LightStates.RED -> {
            greenBulb.updateTransitionState(false)
            orangeBulb.updateTransitionState(false)
            redBulb.updateTransitionState(true)
        }
    }

}