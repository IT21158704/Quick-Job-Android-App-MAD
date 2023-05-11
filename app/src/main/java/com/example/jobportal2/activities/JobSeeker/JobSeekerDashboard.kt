package com.example.jobportal2.activities.JobSeeker

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.jobportal2.R
import com.example.jobportal2.activities.Reviews.ReviewAllReviews
import com.example.jobportal2.activities.Reviews.ReviewsFetching
import com.example.jobportal2.activities.Signin.SigninJobSeekerSignin
import com.example.jobportal2.activities.Signin.SigninUpdateProfile
import com.example.jobportal2.activities.Signin.SigninViewProfile
import com.example.jobportal2.databinding.ActivitySeekerDashboardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JobSeekerDashboard : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var auth: FirebaseAuth
    private lateinit var binding:ActivitySeekerDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySeekerDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth


        val cardJobSearch = findViewById<androidx.cardview.widget.CardView>(R.id.cardJobSearch)

        cardJobSearch.setOnClickListener {

            val intent = Intent(this, JobSeekerSearchJobFetching::class.java)
            startActivity(intent)

        }

        val cardAppliedJobs = findViewById<androidx.cardview.widget.CardView>(R.id.cardAppliedJobs)

        cardAppliedJobs.setOnClickListener {

            val intent = Intent(this, JobSeekerApplyJobFetching::class.java)
            startActivity(intent)
        }

        val cardSeekerProfile = findViewById<androidx.cardview.widget.CardView>(R.id.cardSeekerProfile)

        cardSeekerProfile.setOnClickListener {

            val intent = Intent(this, SigninViewProfile::class.java)
            startActivity(intent)

        }

        val cardReview = findViewById<androidx.cardview.widget.CardView>(R.id.cardReview)

        cardReview.setOnClickListener {

            val intent = Intent(this, ReviewsFetching::class.java)
            startActivity(intent)

        }

        binding.cardSeekerLogout.setOnClickListener {

            auth.signOut()

            val intent = Intent(this, SigninJobSeekerSignin::class.java)
            startActivity(intent)

            Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show()

            finish()

        }
    }
}