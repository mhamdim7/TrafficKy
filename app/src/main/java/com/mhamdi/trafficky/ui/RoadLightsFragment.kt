package com.mhamdi.trafficky.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mhamdi.trafficky.LightStates
import com.mhamdi.trafficky.databinding.FragmentRoadLightsBinding
import com.mhamdi.trafficky.extensions.ViewExtensions.updateTransitionState


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RoadLightsFragment : Fragment() {

    private var _binding: FragmentRoadLightsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: RoadLightsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRoadLightsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.run {

            updateTrafficStates()

            uiState.observe(viewLifecycleOwner) {
                it?.let {
                    binding.updateUi(it)
                    binding.buttonTryAgain.isEnabled = it == LightStates.RED
                }
            }

            binding.buttonTryAgain.setOnClickListener {
                updateTrafficStates()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun FragmentRoadLightsBinding.updateUi(state: LightStates) = when (state) {
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