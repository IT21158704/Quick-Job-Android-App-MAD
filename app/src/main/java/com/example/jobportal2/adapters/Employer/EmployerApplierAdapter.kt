package com.example.jobportal2.adapters.JobSeeker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobportal2.R
import com.example.jobportal2.models.applyJobModel

class EmployerApplierAdapter (private val seekerList: ArrayList<applyJobModel>) :
    RecyclerView.Adapter<EmployerApplierAdapter.ViewHolder>() {

    private lateinit var slistner : OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener){
        slistner = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_employer_view_appliers, parent, false)
        return ViewHolder(itemView, slistner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAppliedJob = seekerList[position]
        holder.Applierjobfrmname.text = currentAppliedJob.applyName
        holder.Applierjobfrmage.text = currentAppliedJob.applyAge
        holder.Applierjobfrmaddress.text = currentAppliedJob.applyAddress
        holder.Applierjobfrmmble.text = currentAppliedJob.applyEmail
        holder.jApplierobfrmemail.text = currentAppliedJob.applyMobile
    }

    override fun getItemCount(): Int {
        return seekerList.size
    }

    class ViewHolder (itemView: View, clickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView)  {


        val Applierjobfrmname : TextView = itemView.findViewById(R.id.Applierjobfrmname)
        val Applierjobfrmage : TextView = itemView.findViewById(R.id.Applierjobfrmage)
        val Applierjobfrmaddress : TextView = itemView.findViewById(R.id.Applierjobfrmaddress)
        val Applierjobfrmmble : TextView = itemView.findViewById(R.id.Applierjobfrmmble)
        val jApplierobfrmemail : TextView = itemView.findViewById(R.id.jApplierobfrmemail)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }


}