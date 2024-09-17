package ca.andresalderete.rides.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ca.andresalderete.rides.R
import ca.andresalderete.rides.ui.adapters.AdapterClass
import ca.andresalderete.rides.ui.viewmodels.VehicleViewModel
import ca.andresalderete.rides.ui.viewmodels.VehicleViewModelFactory
import ca.andresalderete.rides.databinding.FragmentHomeBinding
import ca.andresalderete.rides.models.VehicleItem
import ca.andresalderete.rides.models.Vehicles
import ca.andresalderete.rides.repository.VehicleRepository

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var lastSearch = 1

    private val viewModel: VehicleViewModel by viewModels {
        VehicleViewModelFactory(VehicleRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.vehiclesRecyclerView.layoutManager = LinearLayoutManager(context)

        binding.getVehiclesBtn.setOnClickListener {
            if (binding.editTextText.text.toString().isNotEmpty()) {
                val number = binding.editTextText.text.toString().toInt()
                if (viewModel.isValidNumber(number)) {
                    viewModel.getVehicleList(number)
                    lastSearch = number
                } else {
                    Toast.makeText(context, getString(R.string.wrong_number_error), Toast.LENGTH_SHORT).show()
                }
            } else {
                viewModel.getRandomVehicle()
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getVehicleList(lastSearch)
            binding.swipeRefreshLayout.isRefreshing = false
        }

        viewModel.vehicle.observe(viewLifecycleOwner) { vehicle ->
            val list = Vehicles()
            list.add(vehicle)
            setupRecyclerView(list)
        }

        viewModel.vehicleList.observe(viewLifecycleOwner) { vehicles ->
            setupRecyclerView(vehicles.sortedBy { it.vin })
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        return binding.root
    }

    private fun setupRecyclerView(list: List<VehicleItem>) {
        val adapter = AdapterClass(list)
        binding.vehiclesRecyclerView.adapter = adapter
        adapter.setOnClickListener(object : AdapterClass.OnClickListener {
            override fun onClick(vehicle: VehicleItem) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(vehicle = vehicle))
            }
        })
    }
}