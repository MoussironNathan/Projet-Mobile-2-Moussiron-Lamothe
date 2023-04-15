package com.example.projet_mobile_2_moussiron_lamothe

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {

    fun setHeaderTxt(txt:String) {
        val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
        textViewTitle.text = txt
    }

    fun setHeaderLogoOn(){
        val imageViewHeader = findViewById<ImageView>(R.id.imageView2)
        imageViewHeader.setImageResource(R.drawable.logo)
    }

    fun setHeaderUserOn(){
        val imageViewUser = findViewById<ImageView>(R.id.imageViewUser)
        imageViewUser.visibility=View.VISIBLE
        imageViewUser.setImageResource(R.drawable.user)
        imageViewUser.setOnClickListener {
            val newIntent = Intent(application, UserActivity::class.java)
            startActivity(newIntent)
        }
    }

    fun showBack(){
        val imageViewBack=findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.visibility=View.VISIBLE
        imageViewBack.setImageResource(R.drawable.icon_back)
        imageViewBack.setOnClickListener {
            finish()
        }
    }


}