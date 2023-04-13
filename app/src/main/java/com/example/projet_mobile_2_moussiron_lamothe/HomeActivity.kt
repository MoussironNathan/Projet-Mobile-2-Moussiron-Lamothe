package com.example.projet_mobile_2_moussiron_lamothe

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import fr.epsi.mobile.Tab1Fragment
import fr.epsi.mobile.Tab2Fragment
import fr.epsi.mobile.Tab3Fragment
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class HomeActivity : BaseActivity() {

    val tab1Fragment = Tab1Fragment.newInstance("", "")
    val tab2Fragment = Tab2Fragment.newInstance("", "")
    val tab3Fragment = Tab3Fragment.newInstance("", "")
    var cities = "{\"cities\":["

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setHeaderImgOn()

        setCities()

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

    fun setCities() {
        val magasins = arrayListOf<Magasins>()
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestUrl = "https://www.ugarit.online/epsi/stores.json"
        val request =
            Request.Builder().url(mRequestUrl).cacheControl(CacheControl.FORCE_NETWORK).build()

        println("debut")
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: okhttp3.Call, response: Response) {
                println("toto")
                val data = response.body?.string()
                if (data != null) {
                    val jsMagasins = JSONObject(data)
                    val jsArrayMagasin = jsMagasins.getJSONArray("stores")
                    for (i in 0 until jsArrayMagasin.length()) {
                        val jsMagasin = jsArrayMagasin.getJSONObject(i)
                        val magasin = Magasins(
                            jsMagasin.optInt("storeId"),
                            jsMagasin.optString("name", "Not found"),
                            jsMagasin.optString("description", "Not found"),
                            jsMagasin.optString("pictureStore", "Not found"),
                            jsMagasin.optString("address", "Not found"),
                            jsMagasin.optString("zipcode", "Not found"),
                            jsMagasin.optString("city", "Not found"),
                            jsMagasin.optDouble("longitude"),
                            jsMagasin.optDouble("latitude"),
                        )
                        magasins.add(magasin)
                        println(i)
                        println(jsArrayMagasin.length())
                        if(i != jsArrayMagasin.length()-1)
                            cities = cities + "{\"city\":\"" + jsMagasin.optString("city", "Not found") +
                                    "\",\"lan\":"+ jsMagasin.optDouble("latitude") +
                                    ",\"lng\":" + jsMagasin.optDouble("longitude") + "}, \n"
                        else
                            cities = cities + "{\"city\":\"" + jsMagasin.optString("city", "Not found") +
                                    "\",\"lan\":"+ jsMagasin.optDouble("latitude") +
                                    ",\"lng\":" + jsMagasin.optDouble("longitude") + "}]} \n"

                    }
                    Log.e("WS", data)
                }
            }
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                runOnUiThread(Runnable {
                    Toast.makeText(application, e.message, Toast.LENGTH_SHORT).show()
                })
            }
        })
    }
}