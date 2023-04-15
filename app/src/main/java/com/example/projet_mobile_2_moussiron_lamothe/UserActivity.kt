package com.example.projet_mobile_2_moussiron_lamothe

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

        val editTextFirstName = findViewById<EditText>(R.id.editTextFirstName)
        editTextFirstName.hint = (application as MyApplication).readSharedPref("firstName")

        val editTextLastName = findViewById<EditText>(R.id.editTextLastName)
        editTextLastName.hint = (application as MyApplication).readSharedPref("lastName")

        val editTextEmailAddress2 = findViewById<EditText>(R.id.editTextEmailAddress2)
        editTextEmailAddress2.hint = (application as MyApplication).readSharedPref("email")

        val editTextAddress = findViewById<EditText>(R.id.editTextAddress)
        editTextAddress.hint = (application as MyApplication).readSharedPref("address")

        val editTextCodePostal = findViewById<EditText>(R.id.editTextCodePostal)
        editTextCodePostal.hint = (application as MyApplication).readSharedPref("zipcode")

        val editTextCity = findViewById<EditText>(R.id.editTextCity)
        editTextCity.hint = (application as MyApplication).readSharedPref("city")

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            (application as MyApplication).writeSharedPref("firstName", editTextFirstName.text.toString())
            (application as MyApplication).writeSharedPref("lastName", editTextLastName.text.toString())
            (application as MyApplication).writeSharedPref("email", editTextEmailAddress2.text.toString())
            (application as MyApplication).writeSharedPref("address", editTextAddress.text.toString())
            (application as MyApplication).writeSharedPref("zipcode", editTextCodePostal.text.toString())
            (application as MyApplication).writeSharedPref("city", editTextCity.text.toString())

            Toast.makeText(applicationContext, "Vos informations ont été mise à jour", Toast.LENGTH_SHORT).show()
        }
    }
}