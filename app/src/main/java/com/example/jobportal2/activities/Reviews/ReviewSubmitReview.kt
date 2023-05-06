package com.example.jobportal2.activities.Reviews

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.jobportal2.R
import com.example.jobportal2.models.applyJobModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReviewSubmitReview : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    private lateinit var review_name: EditText
    private lateinit var review_comment: EditText
    private lateinit var btnSubmit: Button
    private lateinit var btnCancel: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_submit_review)

        btnSubmit = findViewById(R.id.button5)
        btnCancel = findViewById(R.id.button6)
        review_name = findViewById(R.id.review_name)
        review_comment = findViewById(R.id.review_comment)

        dbRef = FirebaseDatabase.getInstance().getReference("Reviews")

        btnSubmit.setOnClickListener {
            saveReview()
            val intent = Intent(this, ReviewAllReviews::class.java)
            finish()
            startActivity(intent)
        }
    }
    private fun saveReview(){

        //getting values

        val name = review_name.text.toString()
        val comment = review_comment.text.toString()

        if(name.isEmpty()){
            review_name.error = ("Please Enter Name")
        }
        if(comment.isEmpty()){
            review_comment.error = ("Please Enter Comment")
        }

        val reviewID = dbRef.push().key!!

        val applyReview = applyJobModel(reviewID, name, comment)

        dbRef.child(reviewID).setValue(applyReview)
            .addOnCompleteListener{
                Toast.makeText(this, "Review Submitted", Toast.LENGTH_LONG).show()

                review_name.text.clear()
                review_comment.text.clear()

            }.addOnFailureListener{err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
        }
    }
}