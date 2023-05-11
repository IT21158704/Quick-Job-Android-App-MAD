package com.example.jobportal2.activities.Reviews

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.jobportal2.R
import com.example.jobportal2.activities.Employer.EmployerPostedJobFetching
import com.example.jobportal2.models.Employer.EmployerModel
import com.example.jobportal2.models.Reviews.ReviewModel
import com.google.firebase.database.FirebaseDatabase

class ReviewsMyReviews : AppCompatActivity() {

    private lateinit var reviewid: TextView
    private lateinit var reviewName: TextView
    private lateinit var reviewBody: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviews_my_reviews)

        initView();
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("reviewID").toString(),
                intent.getStringExtra("reviewName").toString(),
                intent.getStringExtra("Review").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("reviewID").toString()
            )
        }
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Reviews").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Review Deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, ReviewsFetching::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Deleting Error ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView(){
        reviewid = findViewById(R.id.reviewID)
        reviewName = findViewById(R.id.reviewName)
        reviewBody = findViewById(R.id.Review)
        btnUpdate = findViewById(R.id.editReview)
        btnDelete = findViewById(R.id.dltReview)
    }

    private fun setValuesToViews(){

        reviewid.text = intent.getStringExtra("reviewID")
        reviewName.text = intent.getStringExtra("reviewName")
        reviewBody.text = intent.getStringExtra("Review")
    }

    @SuppressLint("MissingInflatedId")
    private fun openUpdateDialog(

        rId : String,
        rName : String,
        rBody : String,
    ){
        val sDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val sDialogView = inflater.inflate(R.layout.activity_review_edit_review, null)

        sDialog.setView(sDialogView)

        val edtreviewID = sDialogView.findViewById<TextView>(R.id.editreview_Id)
        val edtreviewName = sDialogView.findViewById<TextView>(R.id.editreview_name)
        val edtreviewBody = sDialogView.findViewById<TextView>(R.id.editreview_comment)
        val btnUpdateData = sDialogView.findViewById<Button>(R.id.editReviewSave)

        edtreviewID.setText(intent.getStringExtra("reviewID").toString())
        edtreviewName.setText(intent.getStringExtra("reviewName").toString())
        edtreviewBody.setText(intent.getStringExtra("Review").toString())

//        sDialog.setTitle("Update Details")

        val alertDialog = sDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            UpdateSdetails(

                edtreviewID.text.toString(),
                edtreviewName.text.toString(),
                edtreviewBody.text.toString(),
            )
            Toast.makeText(applicationContext, "Data Updated", Toast.LENGTH_LONG).show()

            reviewid.text = edtreviewID.text.toString()
            reviewName.text = edtreviewName.text.toString()
            reviewBody.text = edtreviewBody.text.toString()

            alertDialog.dismiss()
        }

    }
    private fun UpdateSdetails(

        rId : String,
        rName : String,
        rBody : String,
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Reviews").child(rId)
        val seekerInfo = ReviewModel(
            rId,
            rName,
            rBody,
        )
        dbRef.setValue(seekerInfo)
    }

}