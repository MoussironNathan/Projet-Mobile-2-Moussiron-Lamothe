package com.example.projet_mobile_2_moussiron_lamothe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat

class FormActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        setHeaderTxt("Création de compte")
        showBack()

    }
}