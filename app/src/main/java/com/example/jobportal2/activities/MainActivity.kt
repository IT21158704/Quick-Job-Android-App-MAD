package com.example.jobportal2.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.jobportal2.R
import com.example.jobportal2.activities.Signin.SigninJobSeekerSignin


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        val cardJobSearch = findViewById<ImageView>(R.id.imageView7)

        cardJobSearch.setOnClickListener {

            val intent = Intent(this, SigninJobSeekerSignin::class.java)
            startActivity(intent)

        }
    }
}
