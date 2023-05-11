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
import com.example.jobportal2.activities.JobSeeker.JobSeekerAppliedJobDetails
import com.example.jobportal2.adapters.JobSeeker.EmployerApplierAdapter
import com.example.jobportal2.adapters.JobSeeker.JobSeekerApplyJobAdapter
import com.example.jobportal2.models.applyJobModel
import com.google.firebase.database.*

class EmployerAppliersFetching : AppCompatActivity() {

    private lateinit var appliedJobRecyclerView: RecyclerView
    private lateinit var applyJobLoading: TextView
    private lateinit var seekerList: ArrayList<applyJobModel>
    private lateinit var dbRef : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employer_appliers_fetching)

        appliedJobRecyclerView = findViewById(R.id.applierFetch)
        appliedJobRecyclerView.layoutManager = LinearLayoutManager(this)
        appliedJobRecyclerView.setHasFixedSize(true)
        applyJobLoading = findViewById(R.id.fetchLoading)

        seekerList = arrayListOf<applyJobModel>()

        getAppliedJobData()

    }

    private fun getAppliedJobData(){
        appliedJobRecyclerView.visibility = View.GONE
        applyJobLoading.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("JobSeeker")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                seekerList.clear()
                if (snapshot.exists()){
                    for (seekerSnap in snapshot.children) {
                        val seekerData = seekerSnap.getValue(applyJobModel::class.java)
                        seekerList.add(seekerData!!)
                    }
                    val seekerAdapter = EmployerApplierAdapter(seekerList)
                    appliedJobRecyclerView.adapter = seekerAdapter

                    seekerAdapter.setOnItemClickListener(object : EmployerApplierAdapter.OnItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent (this@EmployerAppliersFetching, EmployerViewAppliers::class.java)

                            //Put Extra
                            intent.putExtra("jobSid", seekerList[position].seekerID)
                            intent.putExtra("jobfrmname", seekerList[position].applyName)
                            intent.putExtra("jobfrmage", seekerList[position].applyAge)
                            intent.putExtra("jobfrmaddress", seekerList[position].applyAddress)
                            intent.putExtra("jobfrmmble", seekerList[position].applyMobile)
                            intent.putExtra("jobfrmemail", seekerList[position].applyEmail)
                            startActivity(intent)

                        }

                    })

                    appliedJobRecyclerView.visibility = View.VISIBLE
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