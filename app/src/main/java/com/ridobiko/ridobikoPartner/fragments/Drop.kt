package com.ridobiko.ridobikoPartner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.databinding.FragmentDropBinding
import com.ridobiko.ridobikoPartner.models.ApiResponseModel
import com.ridobiko.ridobikoPartner.models.BookingResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Drop : Fragment() {
    lateinit var binding: FragmentDropBinding;
    lateinit var selectedBooking: BookingResponseModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDropBinding.inflate(inflater, container, false)
        selectedBooking = AppVendor.selectedBooking
        // Inflate the layout for this fragment
        if (selectedBooking.pickup != "Not Done") {
            binding.pickupNotDone.visibility = View.VISIBLE
        } else {
            binding.pickupDone.visibility = View.VISIBLE
        }
        binding.helmetsAtPickup.hint = selectedBooking.no_of_helmets
        binding.fuelAtPickup.hint = selectedBooking.fuel_pickup
        binding.kmReadingPickup.hint = selectedBooking.KM_meter_pickup

        //checkbox
        binding.fuelYes.setOnClickListener{
            if(binding.fuelNo.isChecked){
                binding.fuelNo.toggle()
            }
        }
        binding.fuelNo.setOnClickListener{
            if(binding.fuelYes.isChecked){
                binding.fuelYes.toggle()
            }
        }
        binding.kmYes.setOnClickListener{
            if(binding.kmNo.isChecked){
                binding.kmNo.toggle()
            }
        }
        binding.kmNo.setOnClickListener{
            if(binding.kmYes.isChecked){
                binding.kmYes.toggle()
            }
        }
        binding.mainYes.setOnClickListener{
            if(binding.mainNo.isChecked){
                binding.mainNo.toggle()
            }
        }
        binding.mainNo.setOnClickListener{
            if(binding.mainYes.isChecked){
                binding.mainYes.toggle()
            }
        }
        binding.condYes.setOnClickListener{
            if(binding.condNo.isChecked){
                binding.condNo.toggle()
            }
        }
        binding.condNo.setOnClickListener{
            if(binding.condYes.isChecked){
                binding.condYes.toggle()
            }
        }
        binding.idYes.setOnClickListener{
            if(binding.idNo.isChecked){
                binding.idNo.toggle()
            }
        }
        binding.idNo.setOnClickListener{
            if(binding.idYes.isChecked){
                binding.idYes.toggle()
            }
        }





        //fuel charge
        binding.calculateFuelCost.setOnClickListener{
            if(binding.fuelAtPickup.text.isNullOrEmpty()){

            }else{

                var fuel = binding.fuelAtPickup.text.toString().toDouble()
                var max_fuel_bars = if(selectedBooking.max_fuel_bars.toDouble()==0.0) 10.0 else selectedBooking.max_fuel_bars.toDouble()
                if (fuel > max_fuel_bars) {
                    Toast.makeText(
                        requireContext(),
                        "Fuel meter reading cannot be more than max Fuel bars(" + max_fuel_bars + ")",
                        Toast.LENGTH_SHORT
                    ).show();
                } else {
                    var maxFuelTankBar = max_fuel_bars
                    var fuelTankVolume = if(selectedBooking.fuel_tank.toString().toDouble()==0.0) 10.0 else selectedBooking.fuel_tank.toString().toDouble()
                    var fuelPickup = if(selectedBooking.fuel_pickup.toDouble()==0.0) 10.0 else selectedBooking.fuel_pickup.toDouble()
                    var fuelCost = 0.0;
                    var fuelPrice = 100

                    if (fuelPickup == maxFuelTankBar) {
                        fuelCost = (fuelPickup - fuel);//4
                        // alert(1)
                        if (fuelCost == 0.0) {
                            fuelCost = 0.0;
                            // alert(2)
                        } else if (fuelCost > 0) {//3
                            fuelCost =
                                (0.20 * fuelTankVolume + (fuelCost - 1) * (fuelTankVolume - 0.20 * fuelTankVolume) / (maxFuelTankBar - 1)) * fuelPrice;
                            // alert(3)
                        }
                    } else if (fuelPickup < maxFuelTankBar) {
                        fuelCost = fuelPickup - fuel;//4
                        // alert(4)
                        if (fuelCost == 0.0) {
                            fuelCost = 0.0;
                            // alert(5)
                        } else if (fuelCost > 0) {//3
                            fuelCost =
                                ((fuelCost) * (fuelTankVolume - 0.20 * fuelTankVolume) / (maxFuelTankBar - 1)) * fuelPrice;
                            // alert(6)
                        } else if (fuelCost < 0) {
                            // alert(7)
                            if (fuel == maxFuelTankBar) {
                                // alert(8)
                                fuelCost =
                                    (0.20 * fuelTankVolume + (-fuelCost - 1) * (fuelTankVolume - 0.20 * fuelTankVolume) / (maxFuelTankBar - 1)) * fuelPrice;
                                fuelCost = -fuelCost;
                            } else {
                                // alert(9)
                                fuelCost =
                                    ((fuelCost) * (fuelTankVolume - 0.20 * fuelTankVolume) / (maxFuelTankBar - 1)) * fuelPrice;
                                //  fuelCost = -fuelCost;
                            }
                        }
                    }
                    binding.fuelCost.setText(Math.round(fuelCost).toString())

                }

            }
        }
        binding.calculateKmCost.setOnClickListener{
            val km_drop = binding.kmReadingPickup.text.toString()
            if (km_drop == "") {
                Toast.makeText(requireContext(),"Please fill KM Reading",Toast.LENGTH_SHORT).show()
            } else {
                var km = km_drop.toDouble()
                var pickupDate = selectedBooking.pickup_date
                var dropDate = selectedBooking.drop_date
                var datePickup = pickupDate.split(" ")[0].split("-")[2].toDouble()
                var monthPickup = pickupDate.split(" ")[0].split("-")[1].toDouble()
                var dateDrop = dropDate.split(" ")[0].split("-")[2].toDouble()
                var monthDrop = dropDate.split(" ")[0].split("-")[1].toDouble()
                var hourPickUp = pickupDate.split(" ")[1].split(":")[0].toDouble()
                var hourDrop = dropDate.split(" ")[1].split(":")[0].toDouble()
                var noOfDays = 0
                var kilometreLimit = selectedBooking.km_limit.toDouble()
                var extraKilometreCharge = selectedBooking.additional_km_cost.toDouble()
//                var bikePerDayRent = selectedBooking.rent_per_day
                var kmPickup = selectedBooking.KM_meter_pickup.toDouble()

                // alert(fuelPrice)
                var distanceCost = 0

                // alert(fuelCost);
                if (monthDrop - monthPickup > 0) {
                    noOfDays = ((monthDrop - monthPickup) * 30).toInt()
                    // alert(1)
                }
                noOfDays = (noOfDays + (dateDrop - datePickup)).toInt()
                if (-hourPickUp + hourDrop > 0) {
                    noOfDays++
                    // alert(2)
                }
                if (noOfDays == 0) {
                    noOfDays = 1
                    // alert(3)
                }
                var distanceDiff = km - kmPickup
                // alert(distanceDiff)
                if (distanceDiff > kilometreLimit * noOfDays) {
                    distanceCost = ((distanceDiff - kilometreLimit) * extraKilometreCharge).toInt()
                }
                var totalDistanceInt =Math.round(distanceCost.toDouble())
                binding.kmCost.setText(totalDistanceInt.toString())
            }
        }
        binding.submit.setOnClickListener{

            //editText errors
            if (binding.helmetsAtPickup.text.isNullOrEmpty()){
                binding.helmetsAtPickup.error="this field can't be empty"
            }
            else if (binding.fuelAtPickup.text.isNullOrEmpty()){
                binding.fuelAtPickup.error="this field can't be empty"
            }
           else if (binding.kmReadingPickup.text.isNullOrEmpty()){
                binding.kmReadingPickup.error="this field can't be empty"
            }
            else if (binding.maintainaceCost.text.isNullOrEmpty()){
                binding.maintainaceCost.error="this field can't be empty"
            }
            else if (binding.maintainaceDetails.text.isNullOrEmpty()){
                binding.maintainaceDetails.error="this field can't be empty"
            }
            else if (binding.collectedBy.text.isNullOrEmpty()){
                binding.collectedBy.error="this field can't be empty"
            }
            else if (binding.comment.text.isNullOrEmpty()){
                binding.comment.error="this field can't be empty"
            }

          else
            {
                var chargesConfirmed = 0
                if (binding.fuelYes.isChecked) chargesConfirmed += binding.fuelCost.text.toString()
                    .toInt()
                if (binding.kmYes.isChecked) chargesConfirmed += binding.kmCost.text.toString()
                    .toInt()
                if (binding.mainYes.isChecked) chargesConfirmed += binding.maintainaceCost.text.toString()
                    .toInt()

                API.get().submitDrop(
                    selectedBooking.trans_id,
                    selectedBooking.drop_date,
                    binding.helmetsAtPickup.text.toString(),
                    binding.comment
                        .text.toString(),
                    binding.kmReadingPickup.text.toString(),
                    binding.fuelAtPickup.text.toString(),
                    binding.fuelCost.text.toString(),
                    binding.kmCost.text.toString(),
                    binding.maintainaceCost.text.toString(),
                    binding.maintainaceDetails.text.toString(),
                    if (binding.condYes.isChecked) "1" else "0",
                    if (binding.idYes.isChecked) "1" else "0",
                    binding.collectedBy.text.toString(),
                    if (binding.fuelYes.isChecked) "1" else "0",
                    if (binding.kmYes.isChecked) "1" else "0",
                    if (binding.mainYes.isChecked) "1" else "0",
                    chargesConfirmed.toString()
                ).enqueue(object : Callback<ApiResponseModel<String>> {
                    override fun onResponse(
                        call: Call<ApiResponseModel<String>>,
                        response: Response<ApiResponseModel<String>>
                    ) {

                    }

                    override fun onFailure(call: Call<ApiResponseModel<String>>, t: Throwable) {

                    }

                })
            }
        }

        return binding.root
    }
}