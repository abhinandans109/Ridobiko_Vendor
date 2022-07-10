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
import android.widget.Toast
import com.github.dhaval2404.imagepicker.ImagePicker
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.ridobiko.ridobikoPartner.activities.ImageViewerActivity
import com.ridobiko.ridobikoPartner.activities.MainActivity
import com.ridobiko.ridobikoPartner.activities.MyBikeActivity
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.databinding.FragmentDashBoardBinding
import com.ridobiko.ridobikoPartner.databinding.FragmentDocumentsBinding
import com.ridobiko.ridobikoPartner.models.ChangeStatusResponseModel
import com.ridobiko.ridobikoPartner.models.MyBikesResponseModel
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream

class Documents : Fragment() {
    lateinit var selectedMyBike: MyBikesResponseModel
    lateinit var binding: FragmentDocumentsBinding
    var BASE_IMAGE="https://www.ridobiko.com/android_app_ridobiko_owned_store/vehicle_images/"

    var rcImage:String?=null
    var inImage:String?=null
    var perAImage:String?=null
    var perBImage:String?=null
    var pucImage:String?=null
    var billImage:String?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDocumentsBinding.inflate(layoutInflater)
        selectedMyBike=AppVendor.selectedMyBike
        BASE_IMAGE+=selectedMyBike.vendor_email_id+"/"
        Picasso.get().load(BASE_IMAGE + selectedMyBike.RC).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(
                MemoryPolicy.NO_CACHE).into(binding.RcImage)
        Picasso.get().load(BASE_IMAGE + selectedMyBike.Insurance).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(
                MemoryPolicy.NO_CACHE).into(binding.InsuranceImage)
        Picasso.get().load(BASE_IMAGE + selectedMyBike.PUC).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(
                MemoryPolicy.NO_CACHE).into(binding.PucImage)
        Picasso.get().load(BASE_IMAGE + selectedMyBike.Permit).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(
                MemoryPolicy.NO_CACHE).into(binding.permitAImage)
        Picasso.get().load(BASE_IMAGE + selectedMyBike.permit_b).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(
                MemoryPolicy.NO_CACHE).into(binding.permitBImage)
        Picasso.get().load(BASE_IMAGE + selectedMyBike.Purchase_Bill).resize(180,200)
            .placeholder(R.drawable.bike_placeholder).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(
                MemoryPolicy.NO_CACHE).into(binding.purchaseImage)
        binding.engineNo.hint = selectedMyBike.engineNo
        binding.chassisNo.hint = selectedMyBike.chassisNo

//        imageViwerActivity
        binding.RcImage.setOnClickListener{
            requireActivity().startActivity(Intent(requireContext(),
                ImageViewerActivity::class.java).putExtra("documents",
                BASE_IMAGE + selectedMyBike.RC))
        }
        binding.InsuranceImage.setOnClickListener{
            requireActivity().startActivity(Intent(requireContext(),
                ImageViewerActivity::class.java).putExtra("documents",
                BASE_IMAGE + selectedMyBike.Insurance))
        }

        binding.PucImage.setOnClickListener{
            requireActivity().startActivity(Intent(requireContext(),
                ImageViewerActivity::class.java).putExtra("documents",
                BASE_IMAGE + selectedMyBike.PUC))
        }
        binding.permitAImage.setOnClickListener{
            requireActivity().startActivity(Intent(requireContext(),
                ImageViewerActivity::class.java).putExtra("documents",
                BASE_IMAGE + selectedMyBike.Permit))
        }
        binding.permitBImage.setOnClickListener{
            requireActivity().startActivity(Intent(requireContext(),
                ImageViewerActivity::class.java).putExtra("documents",
                BASE_IMAGE + selectedMyBike.permit_b))
        }

        binding.purchaseImage.setOnClickListener{
            requireActivity().startActivity(Intent(requireContext(),
                ImageViewerActivity::class.java).putExtra("documents",
                BASE_IMAGE + selectedMyBike.Purchase_Bill))
        }




        binding.rcUpload.setOnClickListener {

            ImagePicker.with(this).start(1)

        }
        binding.inUpload.setOnClickListener {

            ImagePicker.with(this).start(2)

        }
        binding.perAUpload.setOnClickListener {

            ImagePicker.with(this).start(3)

        }
        binding.perBUpload.setOnClickListener {

            ImagePicker.with(this).start(4)

        }
        binding.pucUpload.setOnClickListener {

            ImagePicker.with(this).start(5)

        }
        binding.purchaseUpload.setOnClickListener {

            ImagePicker.with(this).start(6)

        }
        binding.upload.setOnClickListener{

               API.get().setMyBikeDocument(selectedMyBike.bike_id,selectedMyBike.vendor_email_id,rcImage,inImage,pucImage,perAImage,perBImage,billImage,binding.engineNo.text.toString(),binding.chassisNo.text.toString())
                   .enqueue(object:Callback<ChangeStatusResponseModel>{
                       override fun onResponse(
                           call: Call<ChangeStatusResponseModel>,
                           response: Response<ChangeStatusResponseModel>
                       ) {

                       }

                       override fun onFailure(call: Call<ChangeStatusResponseModel>, t: Throwable) {
                       }

                   })

//            requireActivity().runOnUiThread {
//                requireActivity().startActivity(
//                    Intent(
//                        requireContext(),
//                        MyBikeActivity::class.java
//                    )
//                )
//                Toast.makeText(context, "Documents will be uploaded...", Toast.LENGTH_SHORT).show()
//                requireActivity().finish()
//
//
//            }
        }


        return binding.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            when (requestCode) {
                1 -> {
                    binding.RcImage.setImageURI(data!!.data)
                    rcImage=photoConvert(data.data)
                }
                2 -> {
                    binding.InsuranceImage.setImageURI(data!!.data)
                    inImage=photoConvert(data.data)
                }
                3 -> {
                    binding.permitAImage.setImageURI(data!!.data)
                    perAImage=photoConvert(data.data)
                }
                4 -> {
                    binding.permitBImage.setImageURI(data!!.data)
                    perBImage=photoConvert(data.data)

                }
                5 -> {
                    binding.PucImage.setImageURI(data!!.data)
                    pucImage=photoConvert(data.data)

                }
                6 -> {
                    binding.purchaseImage.setImageURI(data!!.data)
                    billImage=photoConvert(data.data)

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