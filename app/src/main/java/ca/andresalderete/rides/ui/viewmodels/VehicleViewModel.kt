package ca.andresalderete.rides.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.andresalderete.rides.models.VehicleItem
import ca.andresalderete.rides.models.Vehicles
import ca.andresalderete.rides.repository.VehicleRepository
import kotlinx.coroutines.launch

class VehicleViewModel(
    private val repository: VehicleRepository
) : ViewModel() {

    private val _vehicleList = MutableLiveData<Vehicles>()
    val vehicleList: LiveData<Vehicles> get() = _vehicleList

    private val _vehicle = MutableLiveData<VehicleItem>()
    val vehicle: LiveData<VehicleItem> get() = _vehicle

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading


    fun getVehicleList(amount: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.getVehicles(amount)
            result.let {
                _isLoading.value = false
                _vehicleList.value = it
            }
        }
    }

    fun getRandomVehicle() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.getRandomVehicle()
            result.let {
                _isLoading.value = false
                _vehicle.value = it
            }
        }
    }

    fun isValidNumber(value: Int): Boolean = value in 1..100

    fun calculateCarbonEmission(value: Int): Double {
        if (value <= 5000) {
            return value.toDouble()
        } else {
            val remaining = value - 5000
            return 5000.0 + (remaining * 1.5)
        }
    }
}