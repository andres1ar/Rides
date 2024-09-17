package ca.andresalderete.rides.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.andresalderete.rides.repository.VehicleRepository

@Suppress("UNCHECKED_CAST")
class VehicleViewModelFactory(private val repository: VehicleRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehicleViewModel::class.java)) {
            return VehicleViewModel(repository) as T
        } else throw IllegalArgumentException("Unknown ViewModel class")
    }
}