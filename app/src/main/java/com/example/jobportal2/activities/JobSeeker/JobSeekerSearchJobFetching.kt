package com.example.jobportal2.activities.JobSeeker

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
import com.example.jobportal2.activities.Employer.EmployerViewPosedJobs
import com.example.jobportal2.adapters.Employer.EmployerAdapter
import com.example.jobportal2.adapters.Employer.JobSeekerSearchJobAdapter
import com.example.jobportal2.adapters.JobSeeker.EmployerApplierAdapter
import com.example.jobportal2.models.Employer.EmployerModel
import com.example.jobportal2.models.applyJobModel
import com.google.firebase.database.*

class JobSeekerSearchJobFetching : AppCompatActivity() {
    private lateinit var searchJobRecyclerView: RecyclerView
    private lateinit var applyJobLoading: TextView
    private lateinit var employerList: ArrayList<EmployerModel>
    private lateinit var dbRef : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_seeker_search_job_fetching)

        searchJobRecyclerView = findViewById(R.id.searchjobFetch)
        searchJobRecyclerView.layoutManager = LinearLayoutManager(this)
        searchJobRecyclerView.setHasFixedSize(true)
        applyJobLoading = findViewById(R.id.fetchLoading)

        employerList = arrayListOf<EmployerModel>()

        getAppliedJobData()

    }

    private fun getAppliedJobData(){
        searchJobRecyclerView.visibility = View.GONE
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
                    val employerAdapter = JobSeekerSearchJobAdapter(employerList)
                    searchJobRecyclerView.adapter = employerAdapter

                    employerAdapter.setOnItemClickListener(object : JobSeekerSearchJobAdapter.OnItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent (this@JobSeekerSearchJobFetching, JobSeekerApplyJobForm::class.java)

                            //Put Extra

//                            intent.putExtra("searchjbid", employerList[position].JobID)
                            intent.putExtra("searchjobtitletv2", employerList[position].JobTitle)
//                            intent.putExtra("searchjobctgrypostjb", employerList[position].JobCategory)
//                            intent.putExtra("searchjobslrypostjb", employerList[position].JobSalary)
//                            intent.putExtra("searchjobdescriptionjbpost", employerList[position].JobDescription)
                            startActivity(intent)

                        }

                    })

                    searchJobRecyclerView.visibility = View.VISIBLE
                    applyJobLoading.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        )
    }
}



