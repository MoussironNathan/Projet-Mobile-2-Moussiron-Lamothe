package com.example.projet_mobile_2_moussiron_lamothe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class UserActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        setHeaderTxt("Compte")
        showBack()

        val editTextLastName = findViewById<EditText>(R.id.editTextLastName)
        editTextLastName.setText((application as MyApplication).readSharedPref("firstName").toString())

        val editTextFirstName = findViewById<EditText>(R.id.editTextFirstName)
        editTextFirstName.setText((application as MyApplication).readSharedPref("lastName").toString())

        val editTextEmailAddress2 = findViewById<EditText>(R.id.editTextEmailAddress2)
        editTextEmailAddress2.setText((application as MyApplication).readSharedPref("email").toString())

        val editTextAddress = findViewById<EditText>(R.id.editTextAddress)
        editTextAddress.setText((application as MyApplication).readSharedPref("address").toString())

        val editTextCodePostal = findViewById<EditText>(R.id.editTextPostalAddress)
        editTextCodePostal.setText((application as MyApplication).readSharedPref("zipcode").toString())

        val editTextCity = findViewById<EditText>(R.id.editTextCity)
        editTextCity.setText((application as MyApplication).readSharedPref("city").toString())

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            (application as MyApplication).writeSharedPref("firstName", editTextFirstName.text.toString())
            (application as MyApplication).writeSharedPref("lastName", editTextLastName.text.toString())
            (application as MyApplication).writeSharedPref("email", editTextEmailAddress2.text.toString())
            (application as MyApplication).writeSharedPref("address", editTextAddress.text.toString())
            (application as MyApplication).writeSharedPref("zipcode", editTextCodePostal.text.toString())
            (application as MyApplication).writeSharedPref("city", editTextCity.text.toString())
            Toast.makeText(applicationContext, "Vos informations ont été mise à jour", Toast.LENGTH_SHORT).show()
            val newIntent = Intent(application, HomeActivity::class.java)
            startActivity(newIntent)
            finish()
        }
    }
}