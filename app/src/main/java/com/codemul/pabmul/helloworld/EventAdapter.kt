package com.codemul.pabmul.helloworld

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EventAdapter : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    inner class EventViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EventAdapter.EventViewHolder {
        return EventViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_event, parent, false))
    }

    override fun onBindViewHolder(holder: EventAdapter.EventViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}