package com.example.projet_mobile_2_moussiron_lamothe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import fr.epsi.mobile.Tab1Fragment
import fr.epsi.mobile.Tab2Fragment
import fr.epsi.mobile.Tab3Fragment

class HomeActivity : BaseActivity() {

    val tab1Fragment = Tab1Fragment.newInstance("", "")
    val tab2Fragment = Tab2Fragment.newInstance("", "")
    val tab3Fragment = Tab3Fragment.newInstance("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setHeaderImgOn()
        val textViewTab1 = findViewById<TextView>(R.id.textViewTab1)
        val textViewTab2 = findViewById<TextView>(R.id.textViewTab2)
        val textViewTab3 = findViewById<TextView>(R.id.textViewTab3)

        textViewTab1.setBackgroundColor(Color.parseColor("#113858"))
        textViewTab2.setBackgroundColor(Color.parseColor("#19507e"))
        textViewTab3.setBackgroundColor(Color.parseColor("#19507e"))

        showTab1()

        textViewTab1.setOnClickListener(View.OnClickListener {
            textViewTab1.setBackgroundColor(Color.parseColor("#113858"))
            textViewTab2.setBackgroundColor(Color.parseColor("#19507e"))
            textViewTab3.setBackgroundColor(Color.parseColor("#19507e"))
            showTab1()
        })
        textViewTab2.setOnClickListener(View.OnClickListener {
            textViewTab2.setBackgroundColor(Color.parseColor("#113858"))
            textViewTab1.setBackgroundColor(Color.parseColor("#19507e"))
            textViewTab3.setBackgroundColor(Color.parseColor("#19507e"))
            showTab2()
        })
        textViewTab3.setOnClickListener(View.OnClickListener {
            textViewTab3.setBackgroundColor(Color.parseColor("#113858"))
            textViewTab1.setBackgroundColor(Color.parseColor("#19507e"))
            textViewTab2.setBackgroundColor(Color.parseColor("#19507e"))
            showTab3()
        })
    }

    fun showTab1() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack("Tab1")
        fragmentTransaction.replace(R.id.fragmentContent, tab1Fragment)
        fragmentTransaction.commit()
    }

    fun showTab2() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack("Tab2")
        fragmentTransaction.replace(R.id.fragmentContent, tab2Fragment)
        fragmentTransaction.commit()
    }

    fun showTab3() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack("Tab3")
        fragmentTransaction.replace(R.id.fragmentContent, tab3Fragment)
        fragmentTransaction.commit()
    }
}