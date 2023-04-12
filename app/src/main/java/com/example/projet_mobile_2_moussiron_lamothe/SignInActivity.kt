package com.example.projet_mobile_2_moussiron_lamothe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

class SignInActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        setHeaderTxt("Création de compte")

        val buttonQR = findViewById<Button>(R.id.buttonQR)
        val buttonForm = findViewById<Button>(R.id.buttonForm)

        buttonQR.setOnClickListener(View.OnClickListener {
            val intent = Intent(application, QRcodeActivity::class.java)
            startActivity(intent)
        })

        buttonForm.setOnClickListener(View.OnClickListener {
            val intent = Intent(application, FormActivity::class.java)
            startActivity(intent)
        })

    }
}