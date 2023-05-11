package com.example.jobportal2.activities.Employer

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jobportal2.R
import com.example.jobportal2.adapters.Employer.EmployerAdapter
import com.example.jobportal2.models.Employer.EmployerModel
import com.google.firebase.database.*

class EmployerPostedJobFetching : AppCompatActivity() {

    private lateinit var postedJobRecyclerView: RecyclerView
    private lateinit var applyJobLoading: TextView
    private lateinit var employerList: ArrayList<EmployerModel>
    private lateinit var dbRef : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employer_posted_job_fetching)

        postedJobRecyclerView = findViewById(R.id.PostedJobFetch)
        postedJobRecyclerView.layoutManager = LinearLayoutManager(this)
        postedJobRecyclerView.setHasFixedSize(true)
        applyJobLoading = findViewById(R.id.fetchLoading)

        employerList = arrayListOf<EmployerModel>()

        getPostedJobData()

    }

        private fun getPostedJobData(){
            postedJobRecyclerView.visibility = View.GONE
            applyJobLoading.visibility = View.VISIBLE

            dbRef = FirebaseDatabase.getInstance().getReference("Employer")

            dbRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    employerList.clear()
                    if (snapshot.exists()){
                        for (seekerSnap in snapshot.children) {
                            val seekerData = seekerSnap.getValue(EmployerModel::class.java)
                            employerList.add(seekerData!!)
                        }
                        val employerAdapter = EmployerAdapter(employerList)
                        postedJobRecyclerView.adapter = employerAdapter

                        employerAdapter.setOnItemClickListener(object : EmployerAdapter.OnItemClickListener{
                            override fun onItemClick(position: Int) {

                                val intent = Intent (this@EmployerPostedJobFetching, EmployerViewPosedJobs::class.java)

                                //Put Extra

                                intent.putExtra("aplyjbid", employerList[position].JobID)
                                intent.putExtra("jobtitletv2", employerList[position].JobTitle)
                                intent.putExtra("jobctgrypostjb", employerList[position].JobCategory)
                                intent.putExtra("jobslrypostjb", employerList[position].JobSalary)
                                intent.putExtra("jobdescriptionjbpost", employerList[position].JobDescription)
                                startActivity(intent)

                            }

                        })

                        postedJobRecyclerView.visibility = View.VISIBLE
                        applyJobLoading.visibility = View.GONE
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        }
    }