package com.example.jobportal2.activities.Signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.jobportal2.R

class SigninViewProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin_view_profile)

        val update = findViewById<Button>(R.id.update)

        update.setOnClickListener {

            val intent = Intent(this, SigninUpdateProfile::class.java)
            startActivity(intent)

        }
        val delete = findViewById<Button>(R.id.delete)

        delete.setOnClickListener {

            val intent = Intent(this, SigninDeleteProfile::class.java)
            startActivity(intent)

        }
    }
}