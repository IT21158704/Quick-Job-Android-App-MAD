package com.example.jobportal2.adapters.Employer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobportal2.R
import com.example.jobportal2.models.Employer.EmployerModel


class JobSeekerSearchJobAdapter (private val employerList: ArrayList<EmployerModel>) :
    RecyclerView.Adapter<JobSeekerSearchJobAdapter.ViewHolder>() {

    private lateinit var slistner : OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener){
        slistner = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_search_jobs, parent, false)
        return ViewHolder(itemView, slistner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAppliedJob = employerList[position]
        holder.searchjbtitle.text = currentAppliedJob.JobTitle
        holder.searchjbctgry.text = currentAppliedJob.JobCategory
        holder.searchjbslry.text = currentAppliedJob.JobSalary
        holder.searchjbdscption.text = currentAppliedJob.JobDescription
        holder.searchjbid.text = currentAppliedJob.JobID
    }

    override fun getItemCount(): Int {
        return employerList.size
    }

    class ViewHolder (itemView: View, clickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView)  {

        val searchjbtitle : TextView = itemView.findViewById(R.id.searchjbtitle)
        val searchjbctgry : TextView = itemView.findViewById(R.id.searchjbctgry)
        val searchjbslry : TextView = itemView.findViewById(R.id.searchjbslry)
        val searchjbdscption : TextView = itemView.findViewById(R.id.searchjbdscption)
        val searchjbid : TextView = itemView.findViewById(R.id.searchjbid)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }


}
