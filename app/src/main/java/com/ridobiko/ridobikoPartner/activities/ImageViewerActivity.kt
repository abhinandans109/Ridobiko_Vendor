package com.ridobiko.ridobikoPartner.activities

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ravikoradiya.zoomableimageview.ZoomableImageView
import com.ridobiko.ridobikoPartner.AppVendor
import com.ridobiko.ridobikoPartner.R
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.io.File
import kotlin.math.abs


class ImageViewerActivity : AppCompatActivity() {
    private var BASE_IMAGE: String=""
    private var imageUrl=""
    var msg=""
    var lastMsg=""
    var cindex=0;
    private var scaleGestureDetector: ScaleGestureDetector? = null
    private val mScaleFactor = 1.0f
    private val imageView: ImageView? = null
    lateinit var listImages:MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_viewer)
        actionBar?.setDisplayHomeAsUpEnabled(true);
//        supportActionBar?.setIcon(R.drawable.download)
        var customerPics=AppVendor.selectedBooking.customerPictures
        var img=findViewById<ZoomableImageView>(R.id.image)
        var isCustomerImages=intent.hasExtra("Customer Images")
        var isPickupImages=intent.hasExtra("pickup")
        if(isCustomerImages){
            supportActionBar?.title="Customer Images"
            BASE_IMAGE="https://www.ridobiko.com/android_app_customer/"
            listImages= mutableListOf(BASE_IMAGE+customerPics.image_aadhar_front,BASE_IMAGE+customerPics.image_aadhar_back,BASE_IMAGE+customerPics.image_pan,BASE_IMAGE+customerPics.image_driving)
            Picasso.get().load(listImages[0]).placeholder(R.drawable.bike_placeholder).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(img)
        }else if(isPickupImages){
            supportActionBar?.title="Pickup Images"
            BASE_IMAGE="https://ridobiko.com/android_app_ridobiko_owned_store/images/"+AppVendor.selectedBooking.trans_id+"/"
            var images=AppVendor.selectedBooking.pictures
            listImages= mutableListOf(
                BASE_IMAGE+images.bike_front,
                BASE_IMAGE+images.bike_back,
                BASE_IMAGE+images.bike_left,
                BASE_IMAGE+images.bike_right,
                BASE_IMAGE+images.bike_fuel_meter,
                BASE_IMAGE+images.bike_with_customer,
                BASE_IMAGE+images.helmet_front_1,
                BASE_IMAGE+images.helmet_back_1,
                BASE_IMAGE+images.helmet_front_2,
                BASE_IMAGE+images.helmet_back_2,
                BASE_IMAGE+images.helmet_front_3,
                BASE_IMAGE+images.helmet_back_3,
                BASE_IMAGE+images.helmet_front_4,
                BASE_IMAGE+images.helmet_back_4,
            )
            Picasso.get().load(listImages[0]).placeholder(R.drawable.bike_placeholder).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(img)
        }else{
            supportActionBar?.title="Drop Images"
            BASE_IMAGE="https://ridobiko.com/android_app_ridobiko_owned_store/images/"+AppVendor.selectedBooking.trans_id+"/"
            var images=AppVendor.selectedBooking.pictures
            listImages= mutableListOf(
                BASE_IMAGE+images.bike_front_drop,
                BASE_IMAGE+images.bike_back_drop,
                BASE_IMAGE+images.bike_left_drop,
                BASE_IMAGE+images.bike_right_drop,
                BASE_IMAGE+images.bike_fuel_meter_drop,
            )
            Picasso.get().load(listImages[0]).placeholder(R.drawable.bike_placeholder).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(img)
        }

        imageUrl=listImages[0]
        findViewById<LinearLayout>(R.id.slider).setOnTouchListener(@SuppressLint("ClickableViewAccessibility")
        object:OnSwipeTouchListener(applicationContext) {
            @Override
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                imageUrl=listImages[abs((--cindex) %listImages.size)]
                Picasso.get().load( listImages[abs((cindex) %listImages.size)]).placeholder(R.drawable.bike_placeholder).into(img)


            }

            @Override
            override fun onSwipeRight() {
                super.onSwipeRight()
                imageUrl=listImages[abs((++cindex) %listImages.size)]
                Picasso.get().load( listImages[abs((cindex) %listImages.size)]).placeholder(R.drawable.bike_placeholder).into(img)


            }
        })

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.downButton) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                askPermissions()
            } else {
                downloadImage(imageUrl)
            }
        }else if(item.itemId==android.R.id.home){
            finish()
        }

        return true

    }
    @SuppressLint("Range")
    fun downloadImage(url: String) {
        val directory = File(Environment.DIRECTORY_PICTURES)

        if (!directory.exists()) {
            directory.mkdirs()
        }
        val downloadManager = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val downloadUri = Uri.parse(url)

        val request = DownloadManager.Request(downloadUri).apply {
            setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(url.substring(url.lastIndexOf("/") + 1))
                .setDescription("")
                .setDestinationInExternalPublicDir(
                    directory.toString(),
                    url.substring(url.lastIndexOf("/") + 1)
                )
        }
        val downloadId = downloadManager.enqueue(request)
        val query = DownloadManager.Query().setFilterById(downloadId)
        Thread(Runnable {
            var downloading = true
            while (downloading) {
                val cursor: Cursor = downloadManager.query(query)
                cursor.moveToFirst()
                if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                    downloading = false
                }
                val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                 msg = statusMessage(url, directory, status)!!
                if (msg != lastMsg) {
                    this.runOnUiThread {
                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                    }
                    lastMsg = msg ?: ""
                }
                cursor.close()
            }


        }).start()
    }
    private fun statusMessage(url: String, directory: File, status: Int): String? {
        var msg = ""
        msg = when (status) {
            DownloadManager.STATUS_FAILED -> "Download has been failed, please try again"
            DownloadManager.STATUS_PAUSED -> "Paused"
            DownloadManager.STATUS_PENDING -> "Pending"
            DownloadManager.STATUS_RUNNING -> "Downloading..."
            DownloadManager.STATUS_SUCCESSFUL -> "Image downloaded successfully in $directory" + File.separator + url.substring(
                url.lastIndexOf("/") + 1
            )
            else -> "There's nothing to download"
        }
        return msg
    }
    @TargetApi(Build.VERSION_CODES.M)
    fun askPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user asynchronously -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder(this)
                    .setTitle("Permission required")
                    .setMessage("Permission required to save photos from the Web.")
                    .setPositiveButton("Accept") { dialog, id ->
                        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)
                        finish()
                    }
                    .setNegativeButton("Deny") { dialog, id -> dialog.cancel() }
                    .show()
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)
                // MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE is an
                // app-defined int constant. The callback method gets the
                // result of the request.

            }
        } else {
            // Permission has already been granted
            downloadImage(imageUrl)
        }
    }



    companion object {
        private const val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1
    }
}