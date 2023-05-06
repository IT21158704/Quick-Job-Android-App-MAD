package com.example.jobportal2.activities.Reviews

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.jobportal2.R

class ReviewAllReviews : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_all_reviews)

        val yourReview = findViewById<Button>(R.id.yourReview)

        yourReview.setOnClickListener {

            val intent = Intent(this, ReviewsMyReviews::class.java)
            startActivity(intent)
        }

        val submitReview = findViewById<Button>(R.id.submitReview)

        submitReview.setOnClickListener {

            val intent = Intent(this, ReviewSubmitReview::class.java)
            startActivity(intent)
        }
    }
}