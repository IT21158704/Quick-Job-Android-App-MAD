package com.example.jobportal2.activities.JobSeeker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.jobportal2.R
import com.example.jobportal2.models.applyJobModel
import com.google.firebase.database.FirebaseDatabase

class JobSeekerAppliedJobDetails : AppCompatActivity() {

    private lateinit var tvapplyId: TextView
    private lateinit var tvapplyName: TextView
    private lateinit var tvapplyAge: TextView
    private lateinit var tvapplyAddress: TextView
    private lateinit var tvapplyMobile: TextView
    private lateinit var tvapplyEmail: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applied_job_details)

        initView();
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("jobSid").toString(),
                intent.getStringExtra("jobfrmname").toString(),
                intent.getStringExtra("jobfrmage").toString(),
                intent.getStringExtra("jobfrmaddress").toString(),
                intent.getStringExtra("jobfrmmble").toString(),
                intent.getStringExtra("jobfrmemail").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("jobSid").toString()
            )
        }
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("JobSeeker").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Job Request Deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, JobSeekerApplyJobFetching::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Deleting Error ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView(){
        tvapplyId = findViewById(R.id.jobid)
        tvapplyName = findViewById(R.id.jobfrmname)
        tvapplyAge = findViewById(R.id.jobfrmage)
        tvapplyAddress = findViewById(R.id.jobfrmaddress)
        tvapplyMobile = findViewById(R.id.jobfrmmble)
        tvapplyEmail = findViewById(R.id.jobfrmemail)
        btnUpdate = findViewById(R.id.apljbedt)
        btnDelete = findViewById(R.id.apljbdlt)
    }

    private fun setValuesToViews(){

        tvapplyId.text = intent.getStringExtra("jobSid")
        tvapplyName.text = intent.getStringExtra("jobfrmname")
        tvapplyAge.text = intent.getStringExtra("jobfrmage")
        tvapplyAddress.text = intent.getStringExtra("jobfrmaddress")
        tvapplyMobile.text = intent.getStringExtra("jobfrmmble")
        tvapplyEmail.text = intent.getStringExtra("jobfrmemail")
    }

    private fun openUpdateDialog(

        sId : String,
        sName : String,
        sAge : String,
        sAddress : String,
        sMble : String,
        sEmail : String
    ){
        val sDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val sDialogView = inflater.inflate(R.layout.activity_edit_applied_job_form, null)

        sDialog.setView(sDialogView)

        val edtseekerID = sDialogView.findViewById<TextView>(R.id.applyJobID)
        val edtseekerName = sDialogView.findViewById<TextView>(R.id.applyJobName)
        val edtseekerAge = sDialogView.findViewById<TextView>(R.id.applyAge)
        val edtseekerAddress = sDialogView.findViewById<TextView>(R.id.applyAddress)
        val edtseekerMble = sDialogView.findViewById<TextView>(R.id.applyMobile)
        val edtseekerEmail = sDialogView.findViewById<TextView>(R.id.applyEmail)
        val btnUpdateData = sDialogView.findViewById<Button>(R.id.savebtnedtaplyjob)

        edtseekerID.setText(intent.getStringExtra("jobSid").toString())
        edtseekerName.setText(intent.getStringExtra("jobfrmname").toString())
        edtseekerAge.setText(intent.getStringExtra("jobfrmage").toString())
        edtseekerAddress.setText(intent.getStringExtra("jobfrmaddress").toString())
        edtseekerMble.setText(intent.getStringExtra("jobfrmmble").toString())
        edtseekerEmail.setText(intent.getStringExtra("jobfrmemail").toString())

//        sDialog.setTitle("Update Details")

        val alertDialog = sDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            UpdateSdetails(

                edtseekerID.text.toString(),
                edtseekerName.text.toString(),
                edtseekerAge.text.toString(),
                edtseekerAddress.text.toString(),
                edtseekerMble.text.toString(),
                edtseekerEmail.text.toString(),
            )
            Toast.makeText(applicationContext, "Data Updated", Toast.LENGTH_LONG).show()

            tvapplyName.text = edtseekerName.text.toString()
            tvapplyAge.text = edtseekerAge.text.toString()
            tvapplyAddress.text = edtseekerAddress.text.toString()
            tvapplyMobile.text = edtseekerMble.text.toString()
            tvapplyEmail.text = edtseekerEmail.text.toString()

            alertDialog.dismiss()
        }

    }
    private fun UpdateSdetails(
        id : String,
        sName : String,
        sAge : String,
        sAddress : String,
        sMble : String,
        sEmail : String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("JobSeeker").child(id)
        val seekerInfo = applyJobModel(
            id,
            sName,
            sAge,
            sAddress,
            sMble,
            sEmail
        )
        dbRef.setValue(seekerInfo)
    }

}