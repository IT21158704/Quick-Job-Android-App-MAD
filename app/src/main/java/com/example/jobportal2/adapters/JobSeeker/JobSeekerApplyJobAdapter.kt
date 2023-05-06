package com.example.jobportal2.adapters.JobSeeker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobportal2.R
import com.example.jobportal2.models.applyJobModel

class JobSeekerApplyJobAdapter (private val seekerList: ArrayList<applyJobModel>) :
    RecyclerView.Adapter<JobSeekerApplyJobAdapter.ViewHolder>() {

    private lateinit var slistner : OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener){
        slistner = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_applied_jobs, parent, false)
        return ViewHolder(itemView, slistner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAppliedJob = seekerList[position]
        holder.tvSeekerName.text = currentAppliedJob.applyName
    }

    override fun getItemCount(): Int {
        return seekerList.size
    }

    class ViewHolder (itemView: View, clickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView)  {

        val tvSeekerName : TextView = itemView.findViewById(R.id.jobfrmname)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }


}