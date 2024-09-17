package ca.andresalderete.rides.repository

import android.util.Log
import ca.andresalderete.rides.remote.RetrofitInstance
import ca.andresalderete.rides.models.VehicleItem
import ca.andresalderete.rides.models.Vehicles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VehicleRepository {
    suspend fun getVehicles(amount: Int): Vehicles? {
        return withContext(Dispatchers.IO) {
            val response = RetrofitInstance.api.getRandomVehicle(amount)
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("VehicleRepository", "Error getting vehicles: ${response.code()}")
                null
            }
        }
    }

    suspend fun getRandomVehicle(): VehicleItem? {
        return withContext(Dispatchers.IO) {
            val response = RetrofitInstance.api.getAllVehicles()
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("VehicleRepository", "Error getting a random vehicle: ${response.code()}")
                null
            }
        }
    }
}
