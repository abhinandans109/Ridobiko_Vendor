package com.ridobiko.ridobikoPartner.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.github.dhaval2404.imagepicker.ImagePicker
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.activities.TodaysPickups
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.FragmentPickupBinding
import com.ridobiko.ridobikoPartner.models.ApiResponseModel
import com.ridobiko.ridobikoPartner.models.BikesResponseModel
import com.ridobiko.ridobikoPartner.models.BookingResponseModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream

class Pickup : Fragment() {
    lateinit var binding: FragmentPickupBinding
    lateinit var selectedBooking:BookingResponseModel
    lateinit var customerAdhaarFront:Uri
    lateinit var customerAdhaarBack:Uri
    lateinit var customerDriving:Uri
    lateinit var customerOfficeId:Uri
    lateinit var bikeLeft:Uri
    lateinit var bikeRight:Uri
    lateinit var bikeFront:Uri
    lateinit var bikeBack:Uri
    lateinit var bikeCustomer:Uri
    lateinit var bikeFuel:Uri
    lateinit var helmet_front_1:Uri
    lateinit var helmet_back_1:Uri
    lateinit var helmet_front_2:Uri
    lateinit var helmet_back_2:Uri
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentPickupBinding.inflate(inflater,container,false)
        binding.showDropDown.tag = R.drawable.drop_down
        selectedBooking=AppVendor.selectedBooking
        binding.pickupStatus.setText(selectedBooking.pickup)
        Picasso.get().load(selectedBooking.bike_image).placeholder(R.drawable.bike_placeholder).into(binding.bikeImage)


        //not editable
        selectedBooking.pickup="Done"

        if (selectedBooking.pickup=="Done"){
            binding.amountCollected.setFocusable(false)
            binding.modeOfCollectionDeposit.setFocusable(false)
            binding.changeBike.setFocusable(false)
            binding.kmReading.setFocusable(false)
            binding.fuelMeterReading.setFocusable(false)
            binding.kmReading.setFocusable(false)
            binding.noOfHelmets.setFocusable(false)
            binding.idCollected.setFocusable(false)
            binding.purpose.setFocusable(false)
            binding.destination.setFocusable(false)
        }



        API.get().getAvailableBikes(selectedBooking.vendor_email_id).enqueue(object:Callback<ApiResponseModel<ArrayList<BikesResponseModel>>> {
            override fun onResponse(
                call: Call<ApiResponseModel<ArrayList<BikesResponseModel>>>,
                response: Response<ApiResponseModel<ArrayList<BikesResponseModel>>>
            ) {
                var list= mutableListOf<String>(selectedBooking.bike_name+" | "+selectedBooking.bikes_id)
               for (i in  response.body()?.data!!) {
                    list.add(i.bike_name+" | "+i.bike_id)
               }
                binding.changeBike.adapter=ArrayAdapter<String>(requireContext(),R.layout.support_simple_spinner_dropdown_item,list)
            }

            override fun onFailure(
                call: Call<ApiResponseModel<ArrayList<BikesResponseModel>>>,
                t: Throwable
            ) {
            }

        }
        )

        binding.showDropDown.setOnClickListener {

            if (binding.showDropDown.tag == R.drawable.drop_down) {
                binding.dropDown.visibility = View.VISIBLE
                binding.showDropDown.setImageResource(R.drawable.drop_up)
                binding.showDropDown.tag = R.drawable.drop_up

            } else {
                binding.dropDown.visibility = View.GONE
                binding.showDropDown.setImageResource(R.drawable.drop_down)
                binding.showDropDown.tag = R.drawable.drop_down
            }
        }
        binding.submit.setOnClickListener{
            binding.pb.visibility=View.VISIBLE

            submitData()
        }
        binding.modeOfRemainingAmount.adapter=ArrayAdapter<String>(requireContext(),R.layout.support_simple_spinner_dropdown_item,
            mutableListOf("Mode Of Payment","Cash","UPI","Card","Direct Bank Transfer"))
        binding.idCollected.adapter=ArrayAdapter<String>(requireContext(),R.layout.support_simple_spinner_dropdown_item,
            mutableListOf("Aadhaar card","PAN Card","Voter ID"))
        binding.modeOfCollectionDeposit.adapter=ArrayAdapter<String>(requireContext(),R.layout.support_simple_spinner_dropdown_item,
            mutableListOf("Mode Of Payment","Cash","UPI","Card","Direct Bank Transfer"))
        binding.fuelMeterReading.adapter=ArrayAdapter<String>(requireContext(),R.layout.support_simple_spinner_dropdown_item,
            mutableListOf("1","2","3","4","5","6","7","8","9","10"))
        binding.noOfHelmets.adapter=ArrayAdapter<String>(requireContext(),R.layout.support_simple_spinner_dropdown_item,
            mutableListOf("1","2"))

        binding.dlUpload.setOnClickListener {
            ImagePicker.with(this).start(1)
        }
        binding.afUpload.setOnClickListener {
            ImagePicker.with(this).start(2)
        }
        binding.abUpload.setOnClickListener {
            ImagePicker.with(this).start(3)
        }
        binding.panUpload.setOnClickListener {
            ImagePicker.with(this).start(4)
        }
        binding.left.setOnClickListener {
            ImagePicker.with(this).start(5)
        }
        binding.right.setOnClickListener {
            ImagePicker.with(this).start(6)
        }
        binding.front.setOnClickListener {
            ImagePicker.with(this).start(7)
        }
        binding.back.setOnClickListener {
            ImagePicker.with(this).start(8)
        }
        binding.feulMeter.setOnClickListener {
            ImagePicker.with(this).start(9)
        }
        binding.withCustomer.setOnClickListener {
            ImagePicker.with(this).start(10)
        }
        binding.h1Top.setOnClickListener {
            ImagePicker.with(this).start(11)
        }
        binding.h1Bottom.setOnClickListener {
            ImagePicker.with(this).start(12)
        }
        binding.h2Top.setOnClickListener {
            ImagePicker.with(this).start(13)
        }
        binding.h2Bottom.setOnClickListener {
            ImagePicker.with(this).start(14)
        }

        return binding.root
    }

    private fun submitData() {
        API.get().submitPickup(selectedBooking.trans_id,selectedBooking.drop_date,selectedBooking.bikes_id,selectedBooking.bookedon,selectedBooking.status,binding.remainingAmount.text.toString()
            ,binding.modeOfRemainingAmount.selectedItem.toString(),binding.amountCollected.text.toString(),binding.modeOfCollectionDeposit.selectedItem.toString(),
            binding.noOfHelmets.selectedItem.toString(),binding.kmReading.text.toString(),binding.fuelMeterReading.selectedItem.toString(),binding.destination.text.toString(),binding.purpose.text.toString(),binding.idCollected.selectedItem.toString(),if(binding.changeBike.selectedItemPosition==0) "0" else "1",binding.changeBike.selectedItem.toString().split(" | ")[1],if(selectedBooking.current_address_city.isNullOrEmpty())"city" else selectedBooking.current_address_city)
            .enqueue(object : Callback<ApiResponseModel<String>>{

                override fun onResponse(

                    call: Call<ApiResponseModel<String>>,
                    response: Response<ApiResponseModel<String>>
                ) {
                    binding.pb.visibility=View.GONE
                    if(response.isSuccessful) {
                        if (response.body()?.success.equals(Constants.SUCCESS))
                            Toast.makeText(requireContext(), "Pickup done", Toast.LENGTH_SHORT)
                                .show()
                        doAsync {
                            uploadImages()
                        }.execute()
                        requireActivity().startActivity(Intent(requireContext(),TodaysPickups::class.java))
                        requireActivity().finish()
                    }
                }

                override fun onFailure(call: Call<ApiResponseModel<String>>, t: Throwable) {
                    binding.pb.visibility=View.GONE

                    Toast.makeText(requireContext(), Constants.WENT_WRONG, Toast.LENGTH_SHORT).show()

                }

            })
    }

    private fun uploadImages() {
        API.get().uploadPickupImages(selectedBooking.trans_id,selectedBooking.bikes_id,photoConvert(customerAdhaarFront),photoConvert(customerAdhaarBack),
            photoConvert(customerDriving),photoConvert(customerOfficeId),photoConvert(bikeLeft),photoConvert(bikeRight),photoConvert(bikeFront),photoConvert(bikeBack),
            photoConvert(bikeCustomer),photoConvert(bikeFuel),photoConvert(helmet_front_1),photoConvert(helmet_back_1),photoConvert(helmet_front_2),photoConvert(helmet_back_2)).enqueue(object:Callback<ApiResponseModel<String>>{
            override fun onResponse(
                call: Call<ApiResponseModel<String>>,
                response: Response<ApiResponseModel<String>>
            ) {
            }

            override fun onFailure(call: Call<ApiResponseModel<String>>, t: Throwable) {
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==Activity.RESULT_OK){
            when (requestCode) {
                1 -> {
                    binding.dlImage.setImageURI(data!!.data)
                    customerDriving=data.data!!
                }
                2 -> {
                    binding.afImage.setImageURI(data!!.data)
                    customerAdhaarFront=data.data!!
                }
                3 -> {
                    binding.abImage.setImageURI(data!!.data)
                    customerAdhaarBack=data.data!!
                }
                4 -> {
                    binding.panImage.setImageURI(data!!.data)
                    customerOfficeId=data.data!!

                }
                5 -> {
                    binding.left.setImageURI(data!!.data)
                    bikeLeft=data.data!!

                }
                6 -> {
                    binding.right.setImageURI(data!!.data)
                    bikeRight=data.data!!

                }
                7 -> {
                    binding.front.setImageURI(data!!.data)
                    bikeFront=data.data!!
                }
                8 -> {
                    binding.back.setImageURI(data!!.data)
                    bikeBack=data.data!!

                }
                9 -> {
                    binding.feulMeter.setImageURI(data!!.data)
                    bikeFuel=data.data!!
                }
                10 -> {
                    binding.withCustomer.setImageURI(data!!.data)
                    bikeCustomer=data.data!!

                }
                11 -> {
                    binding.h1Top.setImageURI(data!!.data)
                    helmet_front_1=data.data!!

                }
                12 -> {
                    binding.h1Bottom.setImageURI(data!!.data)
                    helmet_back_1=data.data!!

                }
                13 -> {
                    binding.h2Top.setImageURI(data!!.data)
                    helmet_front_2=data.data!!

                }
                14 -> {
                    binding.h2Bottom.setImageURI(data!!.data)
                    helmet_back_2=data.data!!

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
    class doAsync(val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            handler()
            return null
        }
    }

}