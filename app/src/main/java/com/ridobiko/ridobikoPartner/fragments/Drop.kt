package com.ridobiko.ridobikoPartner.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.dhaval2404.imagepicker.ImagePicker
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.activities.ImageViewerActivity
import com.ridobiko.ridobikoPartner.activities.MainActivity
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.FragmentDropBinding
import com.ridobiko.ridobikoPartner.models.ApiResponseModel
import com.ridobiko.ridobikoPartner.models.BookingResponseModel
import com.ridobiko.ridobikoPartner.models.TripDetails
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream
import kotlin.math.roundToInt

class Drop : Fragment() {
    lateinit var binding: FragmentDropBinding;
    lateinit var selectedBooking: BookingResponseModel

     var bikeLeft: String?=null
     var bikeRight: String?=null
     var bikeFront: String?=null
     var bikeBack: String?=null
     var bikeFuel: String?=null
    var BASE_IMAGE="https://ridobiko.com/android_app_ridobiko_owned_store/images/"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDropBinding.inflate(inflater, container, false)
        selectedBooking = AppVendor.selectedBooking
        //drop status
        binding.dropStatus.setText(selectedBooking.drop)
        if (selectedBooking.drop=="Not Done"){
            binding.dropStatus.setTextColor(Color.parseColor("#F44336"))
        }


//not editable
//        selectedBooking.drop="Done"
        if(selectedBooking.trip_details==null)selectedBooking.trip_details= TripDetails()
        binding.extraKmCharge.setText(if(selectedBooking.trip_details.extra_km_charge.isNullOrEmpty())"0" else selectedBooking.trip_details.extra_km_charge)
        binding.fuelCharge.setText(AppVendor.fuel_price)

         if (selectedBooking.drop=="Done"){
             binding.helmetsAtPickup.isEnabled = false
             binding.fuelAtPickup.isEnabled = false
             binding.fuelCharge.isEnabled = false
             binding.fuelCost.isEnabled = false
             binding.fuelYes.isEnabled = false
             binding.fuelNo.isEnabled = false
             binding.kmReadingPickup.isEnabled = false
             binding.extraKmCharge.isEnabled = false
             binding.kmYes.isEnabled = false
             binding.kmNo.isEnabled = false
             binding.maintainaceCost.isEnabled = false
             binding.mainYes.isEnabled = false
             binding.mainNo.isEnabled = false
             binding.maintainaceDetails.isEnabled = false
             binding.condYes.isEnabled = false
             binding.condNo.isEnabled = false
             binding.idYes.isEnabled = false
             binding.idNo.isEnabled = false
             binding.collectedBy.isEnabled = false
             binding.comment.isEnabled = false



             binding.collectedBy.setHint(selectedBooking.trip_details.collected_by)
             if(selectedBooking.trip_details.fuel_charge_apply=="1")binding.fuelYes.toggle()
             else binding.fuelNo.toggle()
             if(selectedBooking.trip_details.maintenance_charge_apply=="1")binding.mainYes.toggle()
             else binding.mainNo.toggle()
             if(selectedBooking.trip_details.vehicle_check=="1")binding.condYes.toggle()
             else binding.condNo.toggle()
             if(selectedBooking.trip_details.km_charge_apply=="1")binding.kmYes.toggle()
             else binding.kmNo.toggle()
             if(selectedBooking.trip_details.id_returned=="1")binding.idYes.toggle()
             else binding.idNo.toggle()

             binding.helmetsAtPickup.setHint(selectedBooking.trip_details.no_of_helmets_drop)
             binding.fuelAtPickup.setHint(selectedBooking.trip_details.fuel_drop)
             binding.kmReadingPickup.setHint(selectedBooking.trip_details.KM_meter_drop)
             binding.maintainaceDetails.setHint(selectedBooking.trip_details.other_charge_reason)
             binding.comment.setHint(selectedBooking.trip_details.comment)
         }else{
             binding.helmetsAtPickup.hint = "On Pickup "+selectedBooking.no_of_helmets
             binding.fuelAtPickup.hint =  "On Pickup "+selectedBooking.fuel_pickup
             binding.kmReadingPickup.hint =  "On Pickup "+selectedBooking.KM_meter_pickup
         }
        BASE_IMAGE="https://ridobiko.com/android_app_ridobiko_owned_store/images/"+selectedBooking.trans_id+"/"
        // Inflate the layout for this fragment
//        if (selectedBooking.pickup != "Not Done") {
//            binding.pickupNotDone.visibility = View.VISIBLE
//        } else {
//            binding.pickupDone.visibility = View.VISIBLE
//        }


        //status


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

        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.bike_left_drop).placeholder(R.drawable.bike_placeholder).into(binding.left)
        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.bike_back_drop).placeholder(R.drawable.bike_placeholder).into(binding.back)
        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.bike_right_drop).placeholder(R.drawable.bike_placeholder).into(binding.right)
        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.bike_front_drop).placeholder(R.drawable.bike_placeholder).into(binding.front)
        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.bike_fuel_meter_drop).placeholder(R.drawable.bike_placeholder).into(binding.fuelMeter)



        binding.left.setOnClickListener {
            if(selectedBooking.drop=="Not Done")
                ImagePicker.with(this).start(5)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("Drop images",
                    BASE_IMAGE+selectedBooking.pictures.bike_left_drop))

        }
        binding.right.setOnClickListener {
            if(selectedBooking.drop=="Not Done")
                ImagePicker.with(this).start(6)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("Drop images",
                    BASE_IMAGE+selectedBooking.pictures.bike_right_drop))
        }
        binding.front.setOnClickListener {
            if(selectedBooking.drop=="Not Done")
                ImagePicker.with(this).start(7)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("Drop images",
                    BASE_IMAGE+selectedBooking.pictures.bike_front_drop))
        }
        binding.back.setOnClickListener {
            if(selectedBooking.drop=="Not Done")
                ImagePicker.with(this).start(8)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("Drop images",
                    BASE_IMAGE+selectedBooking.pictures.bike_back_drop))
        }
        binding.fuelMeter.setOnClickListener {
            if(selectedBooking.drop=="Not Done")
                ImagePicker.with(this).start(9)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("Drop images",
                    BASE_IMAGE+selectedBooking.pictures.bike_fuel_meter_drop))
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
                    var fuelPrice = AppVendor.fuel_price.toInt()

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
                    binding.fuelCost.setText(fuelCost.roundToInt().toString())

                }

            }
        }
        binding.calculateKmCost.setOnClickListener{
           API.get().calculateKmCharges(selectedBooking.vendor_email_id,selectedBooking.bikes_id,selectedBooking.pickup_date
               .split(" ")[0],selectedBooking.drop_date.split(" ")[0]).enqueue(object:Callback<ApiResponseModel<String>>{
               override fun onResponse(
                   call: Call<ApiResponseModel<String>>,
                   response: Response<ApiResponseModel<String>>
               ) {
                   if(response.isSuccessful){
                       if(response.body()?.success==Constants.SUCCESS){
                           var l= response.body()?.data!!.isNullOrEmpty() || response.body()?.data!! == "N/A"
                          var limit= if(!l) response.body()?.data!!.toInt() else 0
                           if(binding.kmReadingPickup.text.toString().toInt()-selectedBooking.trip_details.KM_meter_pickup.toInt()>limit){
                               binding.kmCost.setText(((binding.kmReadingPickup.text.toString().toInt()-selectedBooking.trip_details.KM_meter_pickup.toInt()-limit!!)*binding.extraKmCharge.text.toString().toInt()).toString())
                           }else binding.kmCost.setText("0")

                       }
                   }
               }

               override fun onFailure(call: Call<ApiResponseModel<String>>, t: Throwable) {
               }

           })
        }
        binding.submit.setOnClickListener{
            binding.pb.visibility=View.VISIBLE

            //editText errors
            if (binding.helmetsAtPickup.text.isNullOrEmpty()){
                binding.helmetsAtPickup.error="this field can't be empty"
            }
            else if (binding.fuelYes.isChecked && binding.fuelAtPickup.text.isNullOrEmpty()){
                binding.fuelAtPickup.error="this field can't be empty"
            }
           else if (binding.kmYes.isChecked && binding.kmReadingPickup.text.isNullOrEmpty()){
                binding.kmReadingPickup.error="this field can't be empty"
            }
            else if (binding.mainYes.isChecked && binding.maintainaceCost.text.isNullOrEmpty()){
                binding.maintainaceCost.error="this field can't be empty"
            }
            else if (binding.collectedBy.text.isNullOrEmpty()){
                binding.collectedBy.error="this field can't be empty"
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
                AppVendor.uploaded=false;

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
                    )

                    {
                        binding.pb.visibility=View.GONE
                        if(response.isSuccessful) {
                            if (response.body()?.success.equals(Constants.SUCCESS))

//                            doAsync {
                                Thread {
                                    uploadImages()
                                }.start()
//                            }.execute()


                            requireActivity().runOnUiThread {
                                requireActivity().startActivity(
                                    Intent(
                                        requireContext(),
                                        MainActivity::class.java
                                    )
                                )
                                Toast.makeText(context, "Drop Done", Toast.LENGTH_SHORT).show()
                                requireActivity().finish()


                            }

                        }
                    }

                    override fun onFailure(call: Call<ApiResponseModel<String>>, t: Throwable) {
                        binding.pb.visibility=View.GONE
                        Toast.makeText(requireContext(), Constants.WENT_WRONG, Toast.LENGTH_SHORT).show()

                    }

                })
            }
            binding.pb.visibility=View.GONE

        }


        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            when (requestCode) {
                5 -> {
                    binding.left.setImageURI(data!!.data)
                    bikeLeft=photoConvert(data.data!!)

                }
                6 -> {
                    binding.right.setImageURI(data!!.data)
                    bikeRight=photoConvert(data.data!!)

                }
                7 -> {
                    binding.front.setImageURI(data!!.data)
                    bikeFront=photoConvert(data.data!!)
                }
                8 -> {
                    binding.back.setImageURI(data!!.data)
                    bikeBack=photoConvert(data.data!!)

                }
                9 -> {
                    binding.fuelMeter.setImageURI(data!!.data)
                    bikeFuel=photoConvert(data.data!!)
                }
            }
        }
    }
    fun photoConvert(uri: Uri?): String? {
        try {
            return if (uri != null) {
//                val applicationContext: Context = BookingActivity.getContextOfApplication()
//                applicationContext.getContentResolver()
                val inputStream: InputStream = activity?.applicationContext!!.getContentResolver().openInputStream(uri)!!
                val bitmap = BitmapFactory.decodeStream(inputStream)
                val outputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream)
                val bytes = outputStream.toByteArray()
                val encode = Base64.encodeToString(bytes, Base64.DEFAULT)
                Log.e("ENCODE", encode)
                encode
            } else {

                null
            }
        } catch (e: FileNotFoundException) {

            e.printStackTrace()
        }
        return null
    }
    private fun uploadImages() {
        API.get().uploadDropImages(selectedBooking.trans_id,selectedBooking.bikes_id,
            bikeLeft,bikeRight,
                bikeFront,bikeBack, bikeFuel).enqueue(object:Callback<ApiResponseModel<String>>{
            override fun onResponse(
                call: Call<ApiResponseModel<String>>,
                response: Response<ApiResponseModel<String>>
            ) {
                AppVendor.uploaded=true;
            }

            override fun onFailure(call: Call<ApiResponseModel<String>>, t: Throwable) {
                AppVendor.uploaded=true
            }

        })
    }
    class doAsync(val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            handler()
            return null
        }
    }
}