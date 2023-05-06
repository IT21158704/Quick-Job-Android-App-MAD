package com.example.jobportal2.activities.JobSeeker

import android.content.Intent
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.jobportal2.R

class JobSeekerSearchJobs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_jobs)

        val jobAplyBtn = findViewById<Button>(R.id.jobAplyBtn)

        jobAplyBtn.setOnClickListener {

            val intent = Intent(this, JobSeekerApplyJobForm::class.java)
            startActivity(intent)

        }

    }
}