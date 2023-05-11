package com.example.jobportal2.adapters.Reviews


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobportal2.R
import com.example.jobportal2.models.Reviews.ReviewModel

class ReviewAdapter ( private val reviewList: ArrayList<ReviewModel>)
    : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    private lateinit var slistner : OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener){
        slistner = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_review_all_reviews, parent, false)
        return ViewHolder(itemView, slistner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAppliedJob = reviewList[position]
        holder.ReviewIDd.text = currentAppliedJob.ReviewID
        holder.ReviewNamed.text = currentAppliedJob.ReviewName
        holder.Reviewd.text = currentAppliedJob.Review
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    class ViewHolder (itemView: View, clickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView)  {

        val ReviewIDd : TextView = itemView.findViewById(R.id.allreviewId)
        val ReviewNamed : TextView = itemView.findViewById(R.id.allreviewName)
        val Reviewd : TextView = itemView.findViewById(R.id.allreviewReview)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}