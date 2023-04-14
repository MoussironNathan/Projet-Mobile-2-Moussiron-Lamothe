package com.example.projet_mobile_2_moussiron_lamothe

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetailMagasinActivity : BaseActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_magasin)

        showBack()

        val image = findViewById<ImageView>(R.id.imageViewMagasin)
        val address = findViewById<TextView>(R.id.textViewAdresse)
        val cityTextView = findViewById<TextView>(R.id.textViewVille)
        val description = findViewById<TextView>(R.id.textViewDescription)

        if(intent.extras!=null){
            val title=intent.extras!!.getString("name","XXX")
            println(title)
            setHeaderTxt(title)

            val url=intent.extras!!.getString("urlImage","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj0AEwRdUSWfs2LPDlLKn9kI-KvverDKfy0w&usqp=CAU")
            println(url)
            Picasso.get().load(url).into(image)

            val addr=intent.extras!!.getString("adresse","XXX")
            println(addr)
            address.text = addr

            val city=intent.extras!!.getString("city","XXX")
            val zipcode=intent.extras!!.getString("zipcode","XXX")
            println("$city $zipcode")
            cityTextView.text = "$city $zipcode"

            val desc=intent.extras!!.getString("description","XXX")
            println(desc)
            description.text = desc
        }

    }
}