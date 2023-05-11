package com.example.jobportal2.activities.Employer

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.jobportal2.R
import com.example.jobportal2.activities.JobSeeker.JobSeekerApplyJobFetching
import com.example.jobportal2.models.applyJobModel
import com.google.firebase.database.FirebaseDatabase

class EmployerViewAppliers : AppCompatActivity() {

    private lateinit var tvapplyId: TextView
    private lateinit var tvapplyName: TextView
    private lateinit var tvapplyAge: TextView
    private lateinit var tvapplyAddress: TextView
    private lateinit var tvapplyMobile: TextView
    private lateinit var tvapplyEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employer_view_appliers)

        initView();
        setValuesToViews()

    }

    private fun initView(){
        tvapplyId = findViewById(R.id.Applierjobfrmid)
        tvapplyName = findViewById(R.id.Applierjobfrmname)
        tvapplyAge = findViewById(R.id.Applierjobfrmage)
        tvapplyAddress = findViewById(R.id.Applierjobfrmaddress)
        tvapplyMobile = findViewById(R.id.Applierjobfrmmble)
        tvapplyEmail = findViewById(R.id.jApplierobfrmemail)
    }

    private fun setValuesToViews(){

        tvapplyId.text = intent.getStringExtra("jobSid")
        tvapplyName.text = intent.getStringExtra("jobfrmname")
        tvapplyAge.text = intent.getStringExtra("jobfrmage")
        tvapplyAddress.text = intent.getStringExtra("jobfrmaddress")
        tvapplyMobile.text = intent.getStringExtra("jobfrmmble")
        tvapplyEmail.text = intent.getStringExtra("jobfrmemail")
    }
}