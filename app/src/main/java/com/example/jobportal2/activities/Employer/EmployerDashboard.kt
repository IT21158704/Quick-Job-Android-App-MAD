package com.example.jobportal2.activities.Employer

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.jobportal2.R
import com.example.jobportal2.activities.Reviews.ReviewAllReviews
import com.example.jobportal2.activities.Reviews.ReviewsFetching
import com.example.jobportal2.activities.Signin.SigninJobSeekerSignin
import com.example.jobportal2.activities.Signin.SigninViewProfile
import com.example.jobportal2.databinding.ActivityEmployerDashboardBinding
import com.example.jobportal2.databinding.ActivitySeekerDashboardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class EmployerDashboard : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityEmployerDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEmployerDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        val cardPostJob = findViewById<androidx.cardview.widget.CardView>(R.id.cardPostJob)

        cardPostJob.setOnClickListener {

            val intent = Intent(this, EmployerPostJobForm::class.java)
            startActivity(intent)
        }

        val cardPostedJobs = findViewById<androidx.cardview.widget.CardView>(R.id.cardPostedJobs)

        cardPostedJobs.setOnClickListener {

            val intent = Intent(this, EmployerPostedJobFetching::class.java)
            startActivity(intent)
        }

        val cardViewAppliers = findViewById<androidx.cardview.widget.CardView>(R.id.cardViewAppliers)

        cardViewAppliers.setOnClickListener {

            val intent = Intent(this, EmployerAppliersFetching::class.java)
            startActivity(intent)
        }

        val cardReview = findViewById<androidx.cardview.widget.CardView>(R.id.cardEmpReview)

        cardReview.setOnClickListener {

            val intent = Intent(this, ReviewsFetching::class.java)
            startActivity(intent)
        }

        val cardSeekerProfile = findViewById<androidx.cardview.widget.CardView>(R.id.cardEmpProfile)

        cardSeekerProfile.setOnClickListener {

            val intent = Intent(this, SigninViewProfile::class.java)
            startActivity(intent)

        }

        binding.cardEmpLogout.setOnClickListener {

            auth.signOut()

            val intent = Intent(this, SigninJobSeekerSignin::class.java)
            startActivity(intent)

            Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show()

            finish()

        }
    }
}