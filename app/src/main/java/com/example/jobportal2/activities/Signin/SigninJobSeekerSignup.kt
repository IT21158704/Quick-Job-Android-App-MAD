package com.example.jobportal2.activities.Signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.jobportal2.activities.JobSeeker.JobSeekerApplyJobFetching
import com.example.jobportal2.databinding.ActivitySigninJobSeekerSignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SigninJobSeekerSignup : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySigninJobSeekerSignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySigninJobSeekerSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.JobSeekerSignUpBtn.setOnClickListener {
            val email = binding.seekerEmailSignup.text.toString()
            val password = binding.seekerPassSignup.text.toString()

            if (checkAllField()){
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                    if (it.isSuccessful){
                        auth.signOut()
                        Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, SigninJobSeekerSignin::class.java)
                        finish()
                        startActivity(intent)
                    }
                    else{
                        Log.e("error", it.exception.toString())
                    }
                }
            }
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
        if (binding.seekerConfirmSignup.text.toString() == ""){
            binding.textInputLayoutSeekerConfirmSignup.error = "Please Enter Confirm Password"
            binding.textInputLayoutSeekerConfirmSignup.errorIconDrawable = null
            return false
        }
        if (binding.textInputLayoutSeekerPasswordSignup.editText?.text.toString() != binding.textInputLayoutSeekerConfirmSignup.editText?.text.toString()) {
            binding.textInputLayoutSeekerPasswordSignup.error = "Passwords do not match!"
        }

        return true
    }
}