package com.codemul.pabmul.helloworld

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codemul.pabmul.helloworld.data.Event
import com.squareup.picasso.Picasso

class EventAdapter(private val context: Context, private val listEvent: List<Event>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EventAdapter.EventViewHolder {
        return EventViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_event, parent, false))
    }

    override fun onBindViewHolder(holder: EventAdapter.EventViewHolder, position: Int) {
        val listEvent = listEvent[position]
        holder.tvName.text = listEvent.name
        holder.tvTglEvent.text = listEvent.tgl_event

        var imageUri : String?= null
        imageUri = listEvent.image
        Picasso.get().load(imageUri).into(holder.image)
    }

    override fun getItemCount(): Int {
        return listEvent.size
    }

    inner class EventViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var tvName : TextView = itemView.findViewById(R.id.tv_name_game)
        var tvTglEvent : TextView = itemView.findViewById(R.id.tv_tgl_event)
        var image : ImageView = itemView.findViewById(R.id.img_list_event)
    }
}