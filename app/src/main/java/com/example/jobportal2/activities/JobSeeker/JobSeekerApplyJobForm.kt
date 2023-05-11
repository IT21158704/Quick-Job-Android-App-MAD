package com.example.jobportal2.activities.JobSeeker

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jobportal2.models.applyJobModel
import com.example.jobportal2.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class JobSeekerApplyJobForm : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    private lateinit var applyName: EditText
    private lateinit var applyAge: EditText
    private lateinit var applyAddress: EditText
    private lateinit var applyMobile: EditText
    private lateinit var applyEmail: EditText
    private lateinit var jobfrmaplybtn: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_job_form)

        applyName = findViewById(R.id.applyName)
        applyAge = findViewById(R.id.applyAge)
        applyAddress = findViewById(R.id.applyAddress)
        applyMobile = findViewById(R.id.applyMobile)
        applyEmail = findViewById(R.id.applyEmail)
        jobfrmaplybtn = findViewById(R.id.jobfrmaplybtn)

        dbRef = FirebaseDatabase.getInstance().getReference("JobSeeker")

        jobfrmaplybtn.setOnClickListener {
            saveSeekerData()
            val intent = Intent(this, JobSeekerSearchJobs::class.java)
            finish()
            startActivity(intent)
        }
    }
    private fun saveSeekerData(){

        //getting values

        val seekerName = applyName.text.toString()
        val seekerAge = applyAge.text.toString()
        val seekerAddress = applyAddress.text.toString()
        val seekerMobile = applyMobile.text.toString()
        val seekerEmail = applyEmail.text.toString()

        if(seekerName.isEmpty()){
            applyName.error = ("Please Enter Name")
        }
        if(seekerAge.isEmpty()){
            applyAge.error = ("Please Enter Age")
        }
        if(seekerAddress.isEmpty()){
            applyAddress.error = ("Please Enter Address")
        }
        if(seekerMobile.isEmpty()){
            applyMobile.error = ("Please Enter Mobile")
        }
        if(seekerEmail.isEmpty()){
            applyEmail.error = ("Please Enter Email")
        }

        val seekerID = dbRef.push().key!!

        val applySeeker = applyJobModel(seekerID, seekerName, seekerAge, seekerAddress,  seekerMobile, seekerEmail)

        dbRef.child(seekerID).setValue(applySeeker)
            .addOnCompleteListener{
                Toast.makeText(this, "Job Request Submitted", Toast.LENGTH_LONG).show()

                applyName.text.clear()
                applyAge.text.clear()
                applyAddress.text.clear()
                applyMobile.text.clear()
                applyEmail.text.clear()

            }.addOnFailureListener{err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}