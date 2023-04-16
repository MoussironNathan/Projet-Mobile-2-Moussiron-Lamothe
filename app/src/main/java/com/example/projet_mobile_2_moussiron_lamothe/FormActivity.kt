package com.example.projet_mobile_2_moussiron_lamothe

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import org.json.JSONObject

class FormActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        setHeaderTxt("Création de compte")
        showBack()

        val resultScan = intent.extras!!.getString("result","")
        val donnees = JSONObject(resultScan)

        val editTextLastName = findViewById<EditText>(R.id.editTextLastName)
        editTextLastName.setText(donnees.get("lastName").toString())

        val editTextFirstName = findViewById<EditText>(R.id.editTextFirstName)
        editTextFirstName.setText(donnees.get("firstName").toString())

        val editTextEmailAddress = findViewById<EditText>(R.id.editTextEmailAddress)
        editTextEmailAddress.setText(donnees.get("email").toString())

        val editTextAddress = findViewById<EditText>(R.id.editTextAddress)
        editTextAddress.setText(donnees.get("address").toString())

        val editTextCodePostal = findViewById<EditText>(R.id.editTextCodePostal)
        editTextCodePostal.setText(donnees.get("zipcode").toString())

        val editTextCity = findViewById<EditText>(R.id.editTextCity)
        editTextCity.setText(donnees.get("city").toString())

        val editTextCardRef = findViewById<EditText>(R.id.editTextCodeCarte)
        editTextCardRef.setText(donnees.get("cardRef").toString())

        val button = findViewById<Button>(R.id.signUpButtonSignUp)
        button.setOnClickListener{
            (application as MyApplication).writeSharedPref("lastName", editTextLastName.text.toString())
            (application as MyApplication).writeSharedPref("firstName", editTextFirstName.text.toString())
            (application as MyApplication).writeSharedPref("email", editTextEmailAddress.text.toString())
            (application as MyApplication).writeSharedPref("address", editTextAddress.text.toString())
            (application as MyApplication).writeSharedPref("zipcode", editTextCodePostal.text.toString())
            (application as MyApplication).writeSharedPref("city", editTextCity.text.toString())
            (application as MyApplication).writeSharedPref("cardRef", editTextCardRef.text.toString())

            val intent = Intent(application, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}