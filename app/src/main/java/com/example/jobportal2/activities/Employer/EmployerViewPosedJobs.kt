package com.example.jobportal2.activities.Employer

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.jobportal2.R
import com.example.jobportal2.models.Employer.EmployerModel
import com.example.jobportal2.models.applyJobModel
import com.google.firebase.database.FirebaseDatabase

class EmployerViewPosedJobs : AppCompatActivity() {

    private lateinit var tvapplyId: TextView
    private lateinit var tvapplyName: TextView
    private lateinit var tvapplyAge: TextView
    private lateinit var tvapplyAddress: TextView
    private lateinit var tvapplyMobile: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employer_view_posed_jobs)

        initView();
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("aplyjbid").toString(),
                intent.getStringExtra("jobtitletv2").toString(),
                intent.getStringExtra("jobctgrypostjb").toString(),
                intent.getStringExtra("jobslrypostjb").toString(),
                intent.getStringExtra("jobdescriptionjbpost").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("aplyjbid").toString()
            )
        }
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Employer").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Job Post Deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, EmployerPostedJobFetching::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Deleting Error ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView(){
        tvapplyId = findViewById(R.id.aplyjbid)
        tvapplyName = findViewById(R.id.jobtitletv2)
        tvapplyAge = findViewById(R.id.jobctgrypostjb)
        tvapplyAddress = findViewById(R.id.jobslrypostjb)
        tvapplyMobile = findViewById(R.id.jobdescriptionjbpost)
        btnUpdate = findViewById(R.id.pstjbedt)
        btnDelete = findViewById(R.id.pstjbdlt)
    }

    private fun setValuesToViews(){

        tvapplyId.text = intent.getStringExtra("aplyjbid")
        tvapplyName.text = intent.getStringExtra("jobtitletv2")
        tvapplyAge.text = intent.getStringExtra("jobctgrypostjb")
        tvapplyAddress.text = intent.getStringExtra("jobslrypostjb")
        tvapplyMobile.text = intent.getStringExtra("jobdescriptionjbpost")
    }

    @SuppressLint("MissingInflatedId")
    private fun openUpdateDialog(

        sId : String,
        sName : String,
        sAge : String,
        sAddress : String,
        sMble : String,
    ){
        val sDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val sDialogView = inflater.inflate(R.layout.activity_employer_edit_posted_jobs, null)

        sDialog.setView(sDialogView)

        val edtseekerID = sDialogView.findViewById<TextView>(R.id.applyJobid)
        val edtseekerName = sDialogView.findViewById<TextView>(R.id.applyJobTitle)
        val edtseekerAge = sDialogView.findViewById<TextView>(R.id.applyCategory)
        val edtseekerAddress = sDialogView.findViewById<TextView>(R.id.applySalary)
        val edtseekerMble = sDialogView.findViewById<TextView>(R.id.applyDescription)
        val btnUpdateData = sDialogView.findViewById<Button>(R.id.savebtnedtaplyjob)

        edtseekerID.setText(intent.getStringExtra("aplyjbid").toString())
        edtseekerName.setText(intent.getStringExtra("jobtitletv2").toString())
        edtseekerAge.setText(intent.getStringExtra("jobctgrypostjb").toString())
        edtseekerAddress.setText(intent.getStringExtra("jobslrypostjb").toString())
        edtseekerMble.setText(intent.getStringExtra("jobdescriptionjbpost").toString())

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
            )
            Toast.makeText(applicationContext, "Data Updated", Toast.LENGTH_LONG).show()

            tvapplyName.text = edtseekerName.text.toString()
            tvapplyAge.text = edtseekerAge.text.toString()
            tvapplyAddress.text = edtseekerAddress.text.toString()
            tvapplyMobile.text = edtseekerMble.text.toString()

            alertDialog.dismiss()
        }

    }
    private fun UpdateSdetails(
        id : String,
        sName : String,
        sAge : String,
        sAddress : String,
        sMble : String,
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Employer").child(id)
        val seekerInfo = EmployerModel(
            id,
            sName,
            sAge,
            sAddress,
            sMble,
        )
        dbRef.setValue(seekerInfo)
    }

}