package ca.andresalderete.rides.api

import ca.andresalderete.rides.models.VehicleItem
import ca.andresalderete.rides.models.Vehicles
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VehicleApi {
    @GET("random_vehicle")
    suspend fun getRandomVehicle(@Query("size") size: Int): Response<Vehicles>

    @GET("random_vehicle")
    suspend fun getAllVehicles(): Response<VehicleItem>

    companion object {
        const val BASE_URL = "https://random-data-api.com/api/vehicle/"
    }
}