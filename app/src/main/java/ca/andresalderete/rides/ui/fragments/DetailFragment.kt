package ca.andresalderete.rides.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ca.andresalderete.rides.R
import ca.andresalderete.rides.databinding.FragmentDetailBinding
import ca.andresalderete.rides.models.VehicleItem

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var vehicle: VehicleItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)

        binding.backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getVehicleDetails()
    }

    private fun getVehicleDetails() {
        vehicle = args.vehicle
        binding.detailVin.text = vehicle.vin
        binding.detailMakeAndModel.text = vehicle.makeAndModel
        binding.detailColor.text = vehicle.color
        binding.detailType.text = vehicle.carType
    }
}