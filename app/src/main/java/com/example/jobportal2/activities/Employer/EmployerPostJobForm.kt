package com.example.jobportal2.activities.Employer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.jobportal2.R
import com.example.jobportal2.activities.Reviews.ReviewAllReviews
import com.example.jobportal2.models.Employer.EmployerModel
import com.example.jobportal2.models.applyJobModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EmployerPostJobForm : AppCompatActivity() {

    private lateinit var applyJobTitle: EditText
    private lateinit var applyCategory: EditText
    private lateinit var applySalary: EditText
    private lateinit var applyDescription: EditText
    private lateinit var jobpostaplybtn: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employer_post_job_form)

        applyJobTitle = findViewById(R.id.applyJobTitle)
        applyCategory = findViewById(R.id.applyCategory)
        applySalary = findViewById(R.id.applySalary)
        applyDescription = findViewById(R.id.applyDescription)
        jobpostaplybtn = findViewById(R.id.jobpostaplybtn)

        dbRef = FirebaseDatabase.getInstance().getReference("Employer")

        jobpostaplybtn.setOnClickListener {
            saveEmployee()
            val intent = Intent(this, EmployerAppliersFetching::class.java)
            finish()
            startActivity(intent)
        }
    }
    private fun saveEmployee(){

        //getting values

        val Title = applyJobTitle.text.toString()
        val Category = applyCategory.text.toString()
        val Salary = applySalary.text.toString()
        val Description = applyDescription.text.toString()

        if(Title.isEmpty()){
            applyJobTitle.error = ("Please Enter Title")
        }
        if(Category.isEmpty()){
            applyCategory.error = ("Please Enter Category")
        }
        if(Salary.isEmpty()){
            applySalary.error = ("Please Enter Salary")
        }
        if(Description.isEmpty()){
            applyDescription.error = ("Please Enter Description")
        }

        val EmployerID = dbRef.push().key!!

        val applyReview = EmployerModel(EmployerID, Title, Category, Salary, Description)

        dbRef.child(EmployerID).setValue(applyReview)
            .addOnCompleteListener{
                Toast.makeText(this, "Job Posted", Toast.LENGTH_LONG).show()

                applyJobTitle.text.clear()
                applyCategory.text.clear()
                applySalary.text.clear()
                applyDescription.text.clear()

            }.addOnFailureListener{err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}