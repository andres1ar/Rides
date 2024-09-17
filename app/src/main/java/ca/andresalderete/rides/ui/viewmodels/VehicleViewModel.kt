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


    fun getVehicleList(amount: Int) {
        viewModelScope.launch {
            val result = repository.getVehicles(amount)
            result.let {
                _vehicleList.value = it
            }
        }
    }

    fun getRandomVehicle() {
        viewModelScope.launch {
            val result = repository.getRandomVehicle()
            result.let {
                _vehicle.value = it
            }
        }
    }
}