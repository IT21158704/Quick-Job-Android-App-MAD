package com.example.jobportal2.activities.Signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.jobportal2.R
import com.example.jobportal2.databinding.ActivitySigninUpdateProfileBinding
import com.example.jobportal2.databinding.ActivitySigninViewProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SigninViewProfile : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var auth2: FirebaseAuth
    private lateinit var emailTextView: TextView
    private lateinit var binding: ActivitySigninViewProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySigninViewProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        auth2 = FirebaseAuth.getInstance()
        emailTextView = binding.viewEmail

        val currentUser = auth2.currentUser
        if (currentUser != null) {
            val email = currentUser.email
            emailTextView.text = email.toString()
        } else {
            emailTextView.text = "User not authenticated"
        }

        binding.update.setOnClickListener {

            val intent = Intent(this, SigninUpdateProfile::class.java)
            startActivity(intent)

        }

        binding.delete.setOnClickListener {
            val user = auth.currentUser
            user?.delete()?.addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this, "Account Deleted", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, SigninJobSeekerSignin::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Log.e("error : ",it.exception.toString())
                }
            }
        }
    }
}