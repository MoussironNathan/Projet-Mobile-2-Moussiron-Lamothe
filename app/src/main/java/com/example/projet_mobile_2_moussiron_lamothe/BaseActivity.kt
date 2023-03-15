package com.example.projet_mobile_2_moussiron_lamothe

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {

    fun setHeaderTxt(txt:String) {
        val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
        textViewTitle.setText(txt)
    }

    fun showBack(){
        val imageViewBack=findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.visibility=View.VISIBLE
        imageViewBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }


}