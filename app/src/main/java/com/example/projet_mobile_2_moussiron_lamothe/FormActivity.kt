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

        val editTextLastName = findViewById<EditText>(R.id.editTextLastName)
        val editTextFirstName = findViewById<EditText>(R.id.editTextFirstName)
        val editTextEmailAddress = findViewById<EditText>(R.id.editTextEmailAddress)
        val editTextAddress = findViewById<EditText>(R.id.editTextAddress)
        val editTextCodePostal = findViewById<EditText>(R.id.editTextCodePostal)
        val editTextCity = findViewById<EditText>(R.id.editTextCity)
        val editTextCardRef = findViewById<EditText>(R.id.editTextCodeCarte)

        if(intent.extras != null){
            val resultScan = intent.extras!!.getString("result","")
            val donnees = JSONObject(resultScan)

            editTextLastName.setText(donnees.get("lastName").toString())
            editTextFirstName.setText(donnees.get("firstName").toString())
            editTextEmailAddress.setText(donnees.get("email").toString())
            editTextAddress.setText(donnees.get("address").toString())
            editTextCodePostal.setText(donnees.get("zipcode").toString())
            editTextCity.setText(donnees.get("city").toString())
            editTextCardRef.setText(donnees.get("cardRef").toString())
        }


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
            finish()
        }
    }
}