package com.example.jobportal2.activities.Signin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.example.jobportal2.R
import com.example.jobportal2.activities.Employer.EmployerDashboard
import com.example.jobportal2.activities.JobSeeker.JobSeekerDashboard
import com.example.jobportal2.activities.Reviews.ReviewAllReviews
import com.example.jobportal2.databinding.ActivitySigninEmployerSigninBinding
import com.example.jobportal2.databinding.ActivitySigninJobSeekerSigninBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SigninEmployerSignin : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySigninEmployerSigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySigninEmployerSigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.EmployersignIpbtn.setOnClickListener {
            val email = binding.seekerEmailSignup.text.toString()
            val password = binding.seekerPassSignup.text.toString()

            if(checkAllField()){
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, EmployerDashboard::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this, "Check Email And Password", Toast.LENGTH_SHORT).show()
                        Log.e("error", it.exception.toString())
                    }
                }
            }
        }

        val jobSeekerBtn = findViewById<Button>(R.id.jobSeekerBtn)
        jobSeekerBtn.setOnClickListener {
            val intent = Intent(this, SigninJobSeekerSignin::class.java)
            startActivity(intent)
        }
        val jnseekersignUpbtn = findViewById<Button>(R.id.jbseekersignUpbtn)
        jnseekersignUpbtn.setOnClickListener {
            val intent = Intent(this, SigninJobSeekerSignup::class.java)
            startActivity(intent)
        }
    }

    private fun checkAllField():Boolean {
        val email = binding.seekerEmailSignup.text.toString()

        if(binding.seekerEmailSignup.text.toString() == ""){
            binding.textInputLayoutSeekerEmailSignup.error = "Please Enter Email"
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.textInputLayoutSeekerEmailSignup.error = "Check Email Format"
            return false
        }
        if (binding.seekerPassSignup.text.toString() == ""){
            binding.textInputLayoutSeekerPasswordSignup.error = "Please Enter Password"
            binding.textInputLayoutSeekerPasswordSignup.errorIconDrawable = null
            return false
        }
        if (binding.seekerPassSignup.length() <= 6 ){
            binding.textInputLayoutSeekerPasswordSignup.error = "Password Should be at least 7 characters"
            binding.textInputLayoutSeekerPasswordSignup.errorIconDrawable = null
            return false
        }
        return true
    }
}