package com.example.jobportal2.activities.Reviews

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jobportal2.R
import com.example.jobportal2.activities.Employer.EmployerViewAppliers
import com.example.jobportal2.adapters.JobSeeker.EmployerApplierAdapter
import com.example.jobportal2.adapters.Reviews.ReviewAdapter
import com.example.jobportal2.models.Reviews.ReviewModel
import com.example.jobportal2.models.applyJobModel
import com.google.firebase.database.*

class ReviewsFetching : AppCompatActivity() {

    private lateinit var reviewRecyclerView: RecyclerView
    private lateinit var applyJobLoading: TextView
    private lateinit var reviewList: ArrayList<ReviewModel>
    private lateinit var dbRef : DatabaseReference
    private lateinit var submitbtn : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviews_fetching)

        reviewRecyclerView = findViewById(R.id.reviewFetch)
        reviewRecyclerView.layoutManager = LinearLayoutManager(this)
        reviewRecyclerView.setHasFixedSize(true)
        applyJobLoading = findViewById(R.id.fetchLoading)
        submitbtn = findViewById(R.id.submitReview)

        reviewList = arrayListOf<ReviewModel>()

        getAppliedJobData()

    }

    private fun getAppliedJobData(){
        reviewRecyclerView.visibility = View.GONE
        applyJobLoading.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Reviews")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                reviewList.clear()
                if (snapshot.exists()){
                    for (seekerSnap in snapshot.children) {
                        val seekerData = seekerSnap.getValue(ReviewModel::class.java)
                        reviewList.add(seekerData!!)
                    }
                    val reviewAdapter = ReviewAdapter(reviewList)
                    reviewRecyclerView.adapter = reviewAdapter

                    reviewAdapter.setOnItemClickListener(object : ReviewAdapter.OnItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent (this@ReviewsFetching, ReviewsMyReviews::class.java)

                            //Put Extra
                            intent.putExtra("reviewID", reviewList[position].ReviewID)
                            intent.putExtra("reviewName", reviewList[position].ReviewName)
                            intent.putExtra("Review", reviewList[position].Review)
                            startActivity(intent)

                        }

                    })

                    reviewRecyclerView.visibility = View.VISIBLE
                    applyJobLoading.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        )
        submitbtn.setOnClickListener {

            val intent = Intent(this, ReviewSubmitReview::class.java)
            startActivity(intent)
        }
    }
}