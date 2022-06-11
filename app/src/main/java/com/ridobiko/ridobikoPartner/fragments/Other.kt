package com.ridobiko.ridobikoPartner.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.github.dhaval2404.imagepicker.ImagePicker
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.activities.ImageViewerActivity
import com.ridobiko.ridobikoPartner.activities.MainActivity
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.FragmentOtherBinding
import com.ridobiko.ridobikoPartner.models.ApiResponseModel
import com.ridobiko.ridobikoPartner.models.BikesResponseModel
import com.ridobiko.ridobikoPartner.models.BookingResponseModel
import com.ridobiko.ridobikoPartner.models.Pictures
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class Other : Fragment() {
    private lateinit var BASE_IMAGE: String
    lateinit var binding:FragmentOtherBinding
    lateinit var selectedBooking:BookingResponseModel
    var bikeLeft:String?=null
    var bikeRight:String?=null
    var bikeFront:String?=null
    var bikeBack:String?=null
    var bikeCustomer:String?=null
    var bikeFuel:String?=null
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentOtherBinding.inflate(inflater)
        selectedBooking=AppVendor.selectedBooking
        binding.pDate.setText(selectedBooking.pickup_date.split(" ")[0])
        binding.pTime.setText(selectedBooking.pickup_date.split(" ")[1])
        binding.dDate.setText(selectedBooking.drop_date.split(" ")[0])
        binding.dTime.setText(selectedBooking.drop_date.split(" ")[1])
        BASE_IMAGE="https://ridobiko.com/android_app_ridobiko_owned_store/images/"+selectedBooking.trans_id+"/"
        if(selectedBooking.pictures==null) selectedBooking.pictures= Pictures()

        API.get().getAvailableBikes(selectedBooking.vendor_email_id)
            .enqueue(object:Callback<ApiResponseModel<ArrayList<BikesResponseModel>>> {
                override fun onResponse(
                    call: Call<ApiResponseModel<ArrayList<BikesResponseModel>>>,
                    response: Response<ApiResponseModel<ArrayList<BikesResponseModel>>>
                ) {
                    var list= mutableListOf<String>(selectedBooking.bike_name+" | "+selectedBooking.bikes_id)
                    for (i in  response.body()?.data!!) {
                        list.add(i.bike_name+" | "+i.bike_id)
                    }
                    binding.changeBike.adapter=
                        ArrayAdapter<String>(requireContext(),R.layout.spinner_item,list)
                }

                override fun onFailure(
                    call: Call<ApiResponseModel<ArrayList<BikesResponseModel>>>,
                    t: Throwable
                ) {
                }

            })
        Picasso.get().load(BASE_IMAGE + selectedBooking.pictures.exchanged_bike_back).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).into(binding.back)
        Picasso.get().load(BASE_IMAGE + selectedBooking.pictures.exchanged_bike_front).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).into(binding.front)
        Picasso.get().load(BASE_IMAGE + selectedBooking.pictures.exchanged_bike_right).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).into(binding.right)
        Picasso.get().load(BASE_IMAGE + selectedBooking.pictures.exchanged_bike_left).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).into(binding.left)
        Picasso.get().load(BASE_IMAGE + selectedBooking.pictures.exchanged_bike_fuel).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).into(binding.feulMeter)
        Picasso.get().load(BASE_IMAGE + selectedBooking.pictures.exchanged_bike_with_customer).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).into(binding.withCustomer)


        if(!selectedBooking.pictures.exchanged_bike_front.isNullOrEmpty()){
            binding.exchange.isEnabled=false
            binding.changeBike.isEnabled=false
        }
        binding.left.setOnClickListener {
            if(selectedBooking.pictures.exchanged_bike_left.isNullOrEmpty())
                ImagePicker.with(this).start(5)
            else
                requireActivity().startActivity(
                    Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.exchanged_bike_left))
        }

        binding.right.setOnClickListener {
            if(selectedBooking.pictures.exchanged_bike_right.isNullOrEmpty())
                ImagePicker.with(this).start(6)
            else
                requireActivity().startActivity(
                    Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.exchanged_bike_right))
        }
        binding.front.setOnClickListener {
            if(selectedBooking.pictures.exchanged_bike_front.isNullOrEmpty())
                ImagePicker.with(this).start(7)
            else
                requireActivity().startActivity(
                    Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.exchanged_bike_fuel))
        }
        binding.back.setOnClickListener {
            if(selectedBooking.pictures.exchanged_bike_back.isNullOrEmpty())
                ImagePicker.with(this).start(8)
            else
                requireActivity().startActivity(
                    Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.exchanged_bike_back))
        }
        binding.feulMeter.setOnClickListener {
            if(selectedBooking.pictures.exchanged_bike_fuel.isNullOrEmpty())
                ImagePicker.with(this).start(9)
            else
                requireActivity().startActivity(
                    Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.exchanged_bike_fuel))
        }
        binding.withCustomer.setOnClickListener {
            if(selectedBooking.pictures.exchanged_bike_with_customer.isNullOrEmpty())
                ImagePicker.with(this).start(10)
            else
                requireActivity().startActivity(
                    Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.exchanged_bike_with_customer))
        }
        binding.dDate.setOnClickListener{

            val dpd = DatePickerDialog(requireContext(),  { view, year, monthOfYear, dayOfMonth ->
                var dom="0"
                if(monthOfYear<10)dom+=monthOfYear+1
                else dom=(monthOfYear+1).toString()
                binding.dDate.setText("$year-$dom-$dayOfMonth")

            }, selectedBooking.drop_date.split(" ")[0].split("-")[0].toInt(), selectedBooking.drop_date
                .split(" ")[0].split("-")[1].toInt()-1, selectedBooking.drop_date
                .split(" ")[0].split("-")[2].toInt())

            dpd.show()
        }
        binding.dTime.setOnClickListener{
            TimePickerDialog(context, {view,hr,min->
                        binding.dTime.setText("$hr:$min:00")
                }, selectedBooking.drop_date.split(" ")[1].split(":")[0].toInt()
                , selectedBooking.drop_date.split(" ")[1].split(":")[1].toInt(), true).show()
        }
        binding.calRent.setOnClickListener{
            API.get().getrent(selectedBooking.vendor_email_id,selectedBooking.bikes_id,
                selectedBooking.drop_date.split(" ")[0]
                ,binding.dDate.text.toString(),selectedBooking.drop_date.split(" ")[1],binding.dTime.text.toString()).enqueue(object:Callback<ApiResponseModel<Double>>{
                override fun onResponse(
                    call: Call<ApiResponseModel<Double>>,
                    response: Response<ApiResponseModel<Double>>
                ) {
                    if(response.isSuccessful)
                    binding.totalRent.setText(response.body()!!.data.toInt().toString())
                }

                override fun onFailure(call: Call<ApiResponseModel<Double>>, t: Throwable) {
                }

            })
        }
        binding.exchange.setOnClickListener{
            AppVendor.uploaded=false
            Thread {
                API.get().submitExchange(
                    selectedBooking.trans_id,
                    binding.changeBike.selectedItem.toString().split(" | ")[1],
                    selectedBooking.drop_date,
                    bikeLeft,
                    bikeRight,
                    bikeFront,
                    bikeBack,
                    bikeCustomer,
                    bikeFuel
                ).enqueue(
                    object : Callback<ApiResponseModel<String>> {
                        override fun onResponse(
                            call: Call<ApiResponseModel<String>>,
                            response: Response<ApiResponseModel<String>>
                        ) {
                            AppVendor.uploaded = true
                        }

                        override fun onFailure(call: Call<ApiResponseModel<String>>, t: Throwable) {

                        }

                    }
                )
            }.start()
            requireContext().startActivity(Intent(requireActivity(),MainActivity::class.java))
            requireActivity().finish()
        }



        binding.createBooking.setOnClickListener{
            if(binding.totalRent.text.isNullOrEmpty()){
                Toast.makeText(requireContext(),"Please calculate rent first",Toast.LENGTH_SHORT).show()
            }else{
                API.get().extendBooking(selectedBooking.bikes_id,selectedBooking.trans_id,
                    binding.totalRent.text.toString()
                    ,selectedBooking.drop_date.split(" ")[0],
                    binding.dDate.text.toString(),selectedBooking.drop_date.split(" ")[1],
                    binding.dTime.text.toString(),selectedBooking.trip_details.deposit_collected_by_vendor,selectedBooking.trip_details.deposit_payment_mode,
                    selectedBooking.no_of_helmets,selectedBooking.KM_meter_pickup,selectedBooking.fuel_pickup,selectedBooking.trip_details.destination,selectedBooking.trip_details.purpose,selectedBooking.trip_details.id_collected,
                    selectedBooking.trip_details.city).enqueue(object:Callback<ApiResponseModel<String>>{
                    override fun onResponse(
                        call: Call<ApiResponseModel<String>>,
                        response: Response<ApiResponseModel<String>>
                    ) {
                      if(response.isSuccessful){
                          if(response.body()?.success==Constants.SUCCESS){
                              Toast.makeText(requireContext(),"Booking extended.",Toast.LENGTH_SHORT).show()
                          }else{
                              Toast.makeText(requireContext(),response.body()?.message,Toast.LENGTH_SHORT).show()

                          }
                      }
                    }

                    override fun onFailure(call: Call<ApiResponseModel<String>>, t: Throwable) {
                        Toast.makeText(requireContext(),Constants.WENT_WRONG,Toast.LENGTH_SHORT).show()

                    }

                })
            }
        }
        return binding.root
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            when (requestCode) {
                5 -> {
                    binding.left.setImageURI(data!!.data)
                    bikeLeft=photoConvert(data.data)

                }
                6 -> {
                    binding.right.setImageURI(data!!.data)
                    bikeRight=photoConvert(data.data)

                }
                7 -> {
                    binding.front.setImageURI(data!!.data)
                    bikeFront=photoConvert(data.data)
                }
                8 -> {
                    binding.back.setImageURI(data!!.data)
                    bikeBack=photoConvert(data.data)

                }
                9 -> {
                    binding.feulMeter.setImageURI(data!!.data)
                    bikeFuel=photoConvert(data.data)
                }
                10 -> {
                    binding.withCustomer.setImageURI(data!!.data)
                    bikeCustomer=photoConvert(data.data)

                }

            }
        }
    }

}