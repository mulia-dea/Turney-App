package com.codemul.pabmul.helloworld

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.codemul.pabmul.helloworld.data.DataEvent

class EventAdapter : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    var listEvents = ArrayList<DataEvent>()
    set(listEvents) {
        if (listEvents.size >0){
            this.listEvents.clear()
        }
        this.listEvents.addAll(listEvents)
    }

    fun addItem(event: DataEvent){
        this.listEvents.add(event)
        notifyItemInserted(this.listEvents.size - 1)
    }
    inner class EventViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var tvName : EditText = itemView.findViewById(R.id.name_event)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EventAdapter.EventViewHolder {
        return EventViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_event, parent, false))
    }

    override fun onBindViewHolder(holder: EventAdapter.EventViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}