package com.ridobiko.ridobikoPartner.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.dhaval2404.imagepicker.ImagePicker
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.activities.CustomerDetails
import com.ridobiko.ridobikoPartner.activities.ImageViewerActivity
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.FragmentCustomProfileBinding
import com.ridobiko.ridobikoPartner.models.ChangeStatusResponseModel
import com.ridobiko.ridobikoPartner.models.CustomerDetailsResponseModel
import com.ridobiko.ridobikoPartner.models.CustomerHistoryResponseModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream

class CustomProfile : Fragment() {
    private val CUSTOMER_IMGE="https://www.ridobiko.com/android_app_customer/"
    lateinit var binding:FragmentCustomProfileBinding
    var aadharFront:String?=null
    var aadharBack:String?=null
    var dlimg:String?=null
    var panimg:String?=null
lateinit var selectedCustomer: CustomerDetailsResponseModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCustomProfileBinding.inflate(layoutInflater)
        selectedCustomer=AppVendor.selectedCustomer
        binding.name.setText(selectedCustomer.name)
        binding.mobile.setText(selectedCustomer.login_mobile)

        if(selectedCustomer.email!="null"&&selectedCustomer.email!=null&&selectedCustomer.email!=""){
            binding.email.setText(selectedCustomer.email)
            binding.email.isFocusable=false
        }
        if(selectedCustomer.emergency_no!="null"&&selectedCustomer.emergency_no!=null&&selectedCustomer.emergency_no!=""){
            binding.emergency.setText(selectedCustomer.emergency_no)
            binding.emergency.isFocusable=false
        }
        if(selectedCustomer.driving_id!="null"&&selectedCustomer.driving_id!=null&&selectedCustomer.driving_id!=""){
            binding.dl.setText(selectedCustomer.driving_id)
            binding.dl.isFocusable=false
        }
        if(selectedCustomer.aadhar_id!="null"&&selectedCustomer.aadhar_id!=null&&selectedCustomer.aadhar_id!=""){
            binding.adhaar.setText(selectedCustomer.aadhar_id)
            binding.adhaar.isFocusable=false
        }
        if(selectedCustomer.pan_no!="null"&&selectedCustomer.pan_no!=null&&selectedCustomer.pan_no!=""){
            binding.pan.setText(selectedCustomer.pan_no)
            binding.pan.isFocusable=false
        }
        if(selectedCustomer.current_address_house!="null"&&selectedCustomer.current_address_house!=null&&selectedCustomer.current_address_house!=""){
            binding.house.setText(selectedCustomer.current_address_house)
            binding.house.isFocusable=false
        }
        if(selectedCustomer.current_address_area!="null"&&selectedCustomer.current_address_area!=null&&selectedCustomer.current_address_area!=""){
            binding.area.setText(selectedCustomer.current_address_area)
            binding.area.isFocusable=false
        }
        if(selectedCustomer.current_address_landmark!="null"&&selectedCustomer.current_address_landmark!=null&&selectedCustomer.current_address_landmark!=""){
            binding.landmark.setText(selectedCustomer.current_address_landmark)
            binding.landmark.isFocusable=false
        }
        if(selectedCustomer.current_address_city!="null"&&selectedCustomer.current_address_city!=null&&selectedCustomer.current_address_city!=""){
            binding.city.setText(selectedCustomer.current_address_city)
            binding.city.isFocusable=false
        }
        Picasso.get().load(CUSTOMER_IMGE + selectedCustomer.image_driving).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).into(binding.dlImg)
        Picasso.get().load(CUSTOMER_IMGE + selectedCustomer.image_aadhar_front).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).into(binding.aFront)
        Picasso.get().load(CUSTOMER_IMGE + selectedCustomer.image_aadhar_back).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).into(binding.aBack)
        Picasso.get().load(CUSTOMER_IMGE + selectedCustomer.image_pan).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).into(binding.panImg)

        binding.aFront.setOnClickListener {
            if(selectedCustomer.image_aadhar_front=="null"||selectedCustomer.image_aadhar_front==null)
                ImagePicker.with(this).start(1)
            else
                requireActivity().startActivity(
                    Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    CUSTOMER_IMGE+selectedCustomer.image_aadhar_front))
        }
        binding.aBack.setOnClickListener {
            if(selectedCustomer.image_aadhar_back=="null"||selectedCustomer.image_aadhar_back==null)
                ImagePicker.with(this).start(2)
            else
                requireActivity().startActivity(
                    Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    CUSTOMER_IMGE+selectedCustomer.image_aadhar_back))
        }
        binding.dlImg.setOnClickListener {
            if(selectedCustomer.image_driving=="null"||selectedCustomer.image_driving==null)
                ImagePicker.with(this).start(3)
            else
                requireActivity().startActivity(
                    Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    CUSTOMER_IMGE+selectedCustomer.image_driving))
        }
        binding.panImg.setOnClickListener {
            if(selectedCustomer.image_pan=="null"||selectedCustomer.image_pan==null)
                ImagePicker.with(this).start(4)
            else
                requireActivity().startActivity(
                    Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    CUSTOMER_IMGE+selectedCustomer.image_pan))
        }

        binding.update.setOnClickListener{
            binding.pb.visibility=View.VISIBLE
            API.get().setCustomerProfile(selectedCustomer.customer_mobile,binding.emergency.text.toString(),binding.house.text.toString(),binding.area.text.toString(),binding.landmark.text.toString(),binding.city.text.toString(),binding.dl.text.toString(),binding.adhaar.text.toString(),binding.pan.text.toString(),"0","0","0",aadharFront,aadharBack,dlimg,panimg).enqueue(
                object : Callback<ChangeStatusResponseModel>{
                    override fun onResponse(
                        call: Call<ChangeStatusResponseModel>,
                        response: Response<ChangeStatusResponseModel>
                    ) {
                        binding.pb.visibility=View.GONE
                        if(response.isSuccessful){
                            if(response.body()?.success==Constants.SUCCESS){
                                requireActivity().startActivity(Intent(requireContext(),CustomerDetails::class.java))
                            }
                        }
                    }

                    override fun onFailure(call: Call<ChangeStatusResponseModel>, t: Throwable) {
                        binding.pb.visibility=View.GONE
                    }

                }
            )
        }


        
        
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            when (requestCode) {
                1 -> {
                    binding.aFront.setImageURI(data!!.data)
                    aadharFront=photoConvert(data.data)
                }
                2 -> {
                    binding.aBack.setImageURI(data!!.data)
                    aadharBack=photoConvert(data.data)
                }
                3 -> {
                    binding.dlImg.setImageURI(data!!.data)
                    dlimg=photoConvert(data.data)
                }
                4 -> {
                    binding.panImg.setImageURI(data!!.data)
                    panimg=photoConvert(data.data)

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


}