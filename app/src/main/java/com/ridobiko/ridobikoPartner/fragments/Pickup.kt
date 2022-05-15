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
import com.ridobiko.ridobikoPartner.activities.ImageViewerActivity
import com.ridobiko.ridobikoPartner.activities.TodaysPickups
import com.ridobiko.ridobikoPartner.api.API
import com.ridobiko.ridobikoPartner.constants.Constants
import com.ridobiko.ridobikoPartner.databinding.FragmentPickupBinding
import com.ridobiko.ridobikoPartner.models.ApiResponseModel
import com.ridobiko.ridobikoPartner.models.BikesResponseModel
import com.ridobiko.ridobikoPartner.models.BookingResponseModel
import com.ridobiko.ridobikoPartner.models.Pictures
import com.squareup.picasso.Picasso
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream

class Pickup : Fragment() {
    lateinit var binding: FragmentPickupBinding
    lateinit var selectedBooking:BookingResponseModel
     var customerAdhaarFront:Uri?=null
     var customerAdhaarBack:Uri?=null
     var customerDriving:Uri?=null
     var customerOfficeId:Uri?=null
     var bikeLeft:Uri?=null
     var bikeRight:Uri?=null
     var bikeFront:Uri?=null
     var bikeBack:Uri?=null
     var bikeCustomer:Uri?=null
     var bikeFuel:Uri?=null
     var helmet_front_1:Uri?=null
     var helmet_back_1:Uri?=null
     var helmet_front_2:Uri?=null
     var helmet_back_2:Uri?=null
     var BASE_IMAGE="https://ridobiko.com/android_app_ridobiko_owned_store/images/"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentPickupBinding.inflate(inflater,container,false)
        binding.showDropDown.tag = R.drawable.drop_down
        selectedBooking=AppVendor.selectedBooking
        binding.remainingAmount.text = "Rs "+selectedBooking.amount_left
        binding.amountInWallet.text = "Rs 0"
        binding.securityDeposit.text = "Rs "+selectedBooking.security_deposit
        selectedBooking=AppVendor.selectedBooking
        binding.cusAddress.text=selectedBooking.customer_address
        BASE_IMAGE+=selectedBooking.trans_id+"/"
        binding.pickupStatus.setText(selectedBooking.pickup)
        if(selectedBooking.pictures==null) selectedBooking.pictures= Pictures()
        Picasso.get().load(selectedBooking.bike_image).placeholder(R.drawable.bike_placeholder).into(binding.bikeImage)
        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.bike_back).placeholder(R.drawable.bike_placeholder).into(binding.back)
        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.bike_front).placeholder(R.drawable.bike_placeholder).into(binding.front)
        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.bike_right).placeholder(R.drawable.bike_placeholder).into(binding.right)
        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.bike_left).placeholder(R.drawable.bike_placeholder).into(binding.left)
        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.customer_driving).placeholder(R.drawable.bike_placeholder).into(binding.dlImage)
        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.customer_aadhar_front).placeholder(R.drawable.bike_placeholder).into(binding.afImage)
        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.customer_aadhar_back).placeholder(R.drawable.bike_placeholder).into(binding.abImage)
        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.customer_office_id).placeholder(R.drawable.bike_placeholder).into(binding.panImage)
        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.bike_fuel_meter).placeholder(R.drawable.bike_placeholder).into(binding.feulMeter)
        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.bike_with_customer).placeholder(R.drawable.bike_placeholder).into(binding.withCustomer)
        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.helmet_front_1).placeholder(R.drawable.bike_placeholder).into(binding.h1Top)
        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.helmet_front_2).placeholder(R.drawable.bike_placeholder).into(binding.h2Top)
        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.helmet_back_1).placeholder(R.drawable.bike_placeholder).into(binding.h1Bottom)
        Picasso.get().load(BASE_IMAGE+selectedBooking.pictures.helmet_back_2).placeholder(R.drawable.bike_placeholder).into(binding.h2Bottom)



        //not editable
//        selectedBooking.pickup="Done"

        if (selectedBooking.pickup=="Done"){
            binding.amountCollected.isEnabled = false
            binding.modeOfCollectionDeposit.isEnabled = false
            binding.modeOfRemainingAmount.isEnabled = false
            binding.changeBike.isEnabled = false
            binding.fuelMeterReading.isEnabled = false
            binding.kmReading.isEnabled = false
            binding.noOfHelmets.isEnabled = false
            binding.idCollected.isEnabled = false
            binding.purpose.isEnabled = false
            binding.destination.isEnabled = false
            binding.yes.isEnabled = false
            binding.no.isEnabled = false
            binding.submit.isEnabled = false
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
//            uploadImages()
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

        binding.abImage.setOnClickListener{
            requireActivity().startActivity(Intent(requireContext(),
                ImageViewerActivity::class.java).putExtra("image",
                BASE_IMAGE+selectedBooking.pictures.customer_aadhar_back))
        }
        binding.afImage.setOnClickListener{
            requireActivity().startActivity(Intent(requireContext(),
                ImageViewerActivity::class.java).putExtra("image",
                BASE_IMAGE+selectedBooking.pictures.customer_aadhar_front))
        }
        binding.dlImage.setOnClickListener{
            requireActivity().startActivity(Intent(requireContext(),
                ImageViewerActivity::class.java).putExtra("image",
                BASE_IMAGE+selectedBooking.pictures.customer_driving))
        }
        binding.panImage.setOnClickListener{
            requireActivity().startActivity(Intent(requireContext(),
                ImageViewerActivity::class.java).putExtra("image",
                BASE_IMAGE+selectedBooking.pictures.customer_office_id))
        }

        binding.dlUpload.setOnClickListener {
            if(selectedBooking.pickup=="Not Done")
                ImagePicker.with(this).start(1)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.customer_driving))
        }
        binding.afUpload.setOnClickListener {
            if(selectedBooking.pickup=="Not Done")
                ImagePicker.with(this).start(2)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.customer_aadhar_front))
        }
        binding.abUpload.setOnClickListener {
            if(selectedBooking.pickup=="Not Done")
                ImagePicker.with(this).start(3)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.customer_aadhar_back))
        }
        binding.panUpload.setOnClickListener {
            if(selectedBooking.pickup=="Not Done")
                ImagePicker.with(this).start(4)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.customer_office_id))
        }
        binding.left.setOnClickListener {
            if(selectedBooking.pickup=="Not Done")
                ImagePicker.with(this).start(5)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.bike_left))
        }
        binding.right.setOnClickListener {
            if(selectedBooking.pickup=="Not Done")
                ImagePicker.with(this).start(6)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.bike_right))
        }
        binding.front.setOnClickListener {
            if(selectedBooking.pickup=="Not Done")
                ImagePicker.with(this).start(7)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.bike_front))
        }
        binding.back.setOnClickListener {
            if(selectedBooking.pickup=="Not Done")
                ImagePicker.with(this).start(8)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.bike_back))
        }
        binding.feulMeter.setOnClickListener {
            if(selectedBooking.pickup=="Not Done")
                ImagePicker.with(this).start(9)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.bike_fuel_meter))
        }
        binding.withCustomer.setOnClickListener {
            if(selectedBooking.pickup=="Not Done")
                ImagePicker.with(this).start(10)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.bike_with_customer))
        }
        binding.h1Top.setOnClickListener {
            if(selectedBooking.pickup=="Not Done")
                ImagePicker.with(this).start(11)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.helmet_front_1))
        }
        binding.h1Bottom.setOnClickListener {
            if(selectedBooking.pickup=="Not Done")
                ImagePicker.with(this).start(12)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.helmet_back_1))
        }
        binding.h2Top.setOnClickListener {
            if(selectedBooking.pickup=="Not Done")
                ImagePicker.with(this).start(13)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.helmet_front_2))
        }
        binding.h2Bottom.setOnClickListener {
            if(selectedBooking.pickup=="Not Done")
                ImagePicker.with(this).start(14)
            else
                requireActivity().startActivity(Intent(requireContext(),
                    ImageViewerActivity::class.java).putExtra("image",
                    BASE_IMAGE+selectedBooking.pictures.helmet_back_2))
        }

        return binding.root
    }

    private fun submitData() {
//        uploadImages()
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
            photoConvert(bikeCustomer),photoConvert(bikeFuel),photoConvert(helmet_front_1),photoConvert(helmet_back_1),photoConvert(helmet_front_2),photoConvert(helmet_back_2)).
        enqueue(object:Callback<ResponseBody>{
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
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