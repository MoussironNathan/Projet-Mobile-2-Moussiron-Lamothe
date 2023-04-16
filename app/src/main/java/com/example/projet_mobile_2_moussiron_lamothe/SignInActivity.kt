package com.example.projet_mobile_2_moussiron_lamothe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator

class SignInActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        setHeaderTxt("Création de compte")

        val buttonQR = findViewById<Button>(R.id.buttonQR)
        val buttonForm = findViewById<Button>(R.id.buttonForm)

        buttonQR.setOnClickListener(View.OnClickListener {
            IntentIntegrator(this).initiateScan()
        })

        buttonForm.setOnClickListener(View.OnClickListener {
            val intent = Intent(application, FormActivity::class.java)
            startActivity(intent)
        })

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(application, FormActivity::class.java)
                intent.putExtra("result", result.contents.toString())
                startActivity(intent)
            }
        }
    }
}