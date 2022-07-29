package com.sourabhverma.careercarveandroidapplication.dashboard.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sourabhverma.careercarveandroidapplication.R
import com.sourabhverma.careercarveandroidapplication.dashboard.data.remote.dto.Data

class MeetingListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var listOfMeetings : List<Data> = listOf()
    private var context : Context? = null
    private var type : Int? = null

    fun setMeetingListData(list : List<Data>, context: Context, type : Int){
        this.context = context
        this.listOfMeetings = list
        this.type = type
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.meeting_list,
                    parent,
                    false
                )
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as MyViewHolder
        if (type == 0) {
            viewHolder.name_mentor.text = listOfMeetings[position].mentor_name
            viewHolder.email_mentor.text = listOfMeetings[position].mentor_email
            viewHolder.timming.text = "${listOfMeetings[position].schedule_start} - ${listOfMeetings[position].schedule_end}"
        } else {
            viewHolder.name_mentor.text = listOfMeetings[position].student_name
            viewHolder.email_mentor.text = listOfMeetings[position].student_email
            viewHolder.timming.text = "${listOfMeetings[position].schedule_start} - ${listOfMeetings[position].schedule_end}"
        }

        if (context!=null) {
            Glide.with(context!!)
                .load(ResourcesCompat.getDrawable(context!!.resources, R.drawable.ic_baseline_face_24, context!!.theme))
                .apply (RequestOptions.placeholderOf(R.drawable.ic_baseline_face_24))
                .circleCrop()
                .into(viewHolder.profile_image_of_mentor)
        }
    }

    override fun getItemCount(): Int {
        return listOfMeetings.size
    }

    inner class MyViewHolder(v: View):RecyclerView.ViewHolder(v){
        var profile_image_of_mentor : ImageView = v.findViewById(R.id.profile_image_of_mentor)
        var name_mentor : TextView = v.findViewById(R.id.name_mentor)
        var email_mentor : TextView = v.findViewById(R.id.email_mentor)
        var timming : TextView = v.findViewById(R.id.timming)
    }
}