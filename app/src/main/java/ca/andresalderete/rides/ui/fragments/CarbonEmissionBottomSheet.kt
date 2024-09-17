package ca.andresalderete.rides.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ca.andresalderete.rides.databinding.ItemBottomSheetBinding
import ca.andresalderete.rides.repository.VehicleRepository
import ca.andresalderete.rides.ui.viewmodels.VehicleViewModel
import ca.andresalderete.rides.ui.viewmodels.VehicleViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CarbonEmissionBottomSheet: BottomSheetDialogFragment() {
    private lateinit var binding: ItemBottomSheetBinding

    private val viewModel: VehicleViewModel by viewModels {
        VehicleViewModelFactory(VehicleRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val kilometrage = arguments?.getInt("kilometrage")
        kilometrage?.let {
            binding.estimatedEmission.text = viewModel.calculateCarbonEmission(it).toString()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.setOnShowListener {
            val d = it as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let { sheet ->
                val behavior = BottomSheetBehavior.from(sheet)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }
}