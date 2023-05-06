package com.example.jobportal2.activities.Reviews

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.jobportal2.R

class ReviewsMyReviews : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviews_my_reviews)

        val editReview = findViewById<Button>(R.id.editReview)

        editReview.setOnClickListener {

            val intent = Intent(this, ReviewEditReview::class.java)
            startActivity(intent)
        }

        val submitReview = findViewById<Button>(R.id.dltReview)

        submitReview.setOnClickListener {

            Toast.makeText(this, "Review Delete clicked", Toast.LENGTH_SHORT).show()
        }
    }
}