package com.ridobiko.ridobikoPartner.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.ridobiko.ridobikoPartner.R
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class ImageViewerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_viewer)
        supportActionBar?.hide()
        var img=findViewById<ImageView>(R.id.image)
        Picasso.get().load(intent.getStringExtra("image")).placeholder(R.drawable.bike_placeholder).into(img)



    }
}