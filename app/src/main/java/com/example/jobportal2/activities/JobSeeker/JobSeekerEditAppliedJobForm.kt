package com.example.jobportal2.activities.JobSeeker

//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jobportal2.R


class JobSeekerEditAppliedJobForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_applied_job_form)

        val savebtnedtaplyjob = findViewById<Button>(R.id.savebtnedtaplyjob)

        savebtnedtaplyjob.setOnClickListener {

            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()

        }
    }
}