package com.example.jobportal2.adapters.Employer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobportal2.R
import com.example.jobportal2.models.Employer.EmployerModel

class EmployerAdapter (private val employerList: ArrayList<EmployerModel>) :
    RecyclerView.Adapter<EmployerAdapter.ViewHolder>() {

    private lateinit var slistner : OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener){
        slistner = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_employer_all_jobs, parent, false)
        return ViewHolder(itemView, slistner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAppliedJob = employerList[position]
        holder.tvSeekerName.text = currentAppliedJob.JobTitle
    }

    override fun getItemCount(): Int {
        return employerList.size
    }

    class ViewHolder (itemView: View, clickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView)  {

        val tvSeekerName : TextView = itemView.findViewById(R.id.EmpJbTitle)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }


}