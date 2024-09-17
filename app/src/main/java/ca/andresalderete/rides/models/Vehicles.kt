package ca.andresalderete.rides.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class Vehicles : ArrayList<VehicleItem>()

@Parcelize
data class VehicleItem(
    @SerializedName("car_options")
    val carOptions: List<String>,
    @SerializedName("car_type")
    val carType: String,
    val color: String,
    val doors: Int,
    @SerializedName("drive_type")
    val driveType: String,
    @SerializedName("fuel_type")
    val fuelType: String,
    val id: Int,
    val kilometrage: Int,
    @SerializedName("license_plate")
    val licensePlate: String,
    @SerializedName("make_and_model")
    val makeAndModel: String,
    val mileage: Int,
    val specs: List<String>,
    val transmission: String,
    val uid: String,
    val vin: String
) : Parcelable