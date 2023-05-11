package com.example.jobportal2.activities.Signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.jobportal2.R
import com.example.jobportal2.databinding.ActivitySigninEmployerSigninBinding
import com.example.jobportal2.databinding.ActivitySigninUpdateProfileBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SigninUpdateProfile : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var auth2: FirebaseAuth
    private lateinit var emailEditText: TextInputLayout
    private lateinit var binding: ActivitySigninUpdateProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySigninUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        auth2 = FirebaseAuth.getInstance()
        emailEditText = binding.textInputLayoutupdateEmail

        val currentUser = auth2.currentUser
        if (currentUser != null) {
            val email = currentUser.email
            emailEditText.editText?.setText(email)
        } else {
            emailEditText.editText?.setText("User not authenticated")
        }

        binding.emailSave.setOnClickListener {
            val password = binding.updatePass.text.toString()
            val user = auth.currentUser
            if (checkPasswordField()){
                user?.updatePassword(password)?.addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
                    }else{
                        Log.e("error : ",it.exception.toString())
                    }
                }
            }
            val email = binding.updateEmail.text.toString()
            if (checkEmailField()){
                user?.updateEmail(email)?.addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, SigninJobSeekerSignin::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "Please Login Again", Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Log.e("error : ",it.exception.toString())
                    }
                }
            }

        }
//        binding.emailSave.setOnClickListener {
//            val user = auth.currentUser
//            val email = binding.updateEmail.text.toString()
//            if (checkEmailField()){
//                user?.updateEmail(email)?.addOnCompleteListener {
//                    if(it.isSuccessful){
//                        Toast.makeText(this, "Email Updated", Toast.LENGTH_SHORT).show()
//                    }else{
//                        Log.e("error : ",it.exception.toString())
//                    }
//                }
//            }
//        }
        binding.cancel.setOnClickListener {

            val intent = Intent(this, SigninViewProfile::class.java)
            startActivity(intent)

        }
    }

    private fun checkPasswordField(): Boolean {

        if (binding.updatePass.text.toString() == ""){
            binding.textInputLayoutupdatePass.error = "Please Enter Password"
            binding.textInputLayoutupdatePass.errorIconDrawable = null
            return false
        }
        if (binding.updatePass.length() <= 6 ){
            binding.textInputLayoutupdatePass.error = "Password Should be at least 7 characters"
            binding.textInputLayoutupdatePass.errorIconDrawable = null
            return false
        }
        return true
    }

    private fun checkEmailField(): Boolean{

        val email = binding.updateEmail.text.toString()

        if(binding.updateEmail.text.toString() == ""){
            binding.textInputLayoutupdateEmail.error = "Please Enter Email"
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.textInputLayoutupdateEmail.error = "Check Email Format"
            return false
        }
        return true
    }
}