package com.codemul.pabmul.helloworld

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codemul.pabmul.helloworld.data.Event
import com.squareup.picasso.Picasso

class EventAdapter(private val context: Context, private val listEvent: List<Event>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(event: Event)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EventAdapter.EventViewHolder {
        return EventViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_event, parent, false))
    }

    override fun onBindViewHolder(holder: EventAdapter.EventViewHolder, position: Int) {
        holder.bind(listEvent[position])
    }

    override fun getItemCount(): Int {
        return listEvent.size
    }

    inner class EventViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var tvName : TextView = itemView.findViewById(R.id.tv_name_event)
        var image : ImageView = itemView.findViewById(R.id.img_list_event)
        var btn : Button = itemView.findViewById(R.id.btn_detail)
        var imageUri : String?= null

        fun bind(event: Event) {
            tvName.text = event.name
            imageUri = event.image
            Picasso.get().load(imageUri).into(image)
            btn.setOnClickListener{ onItemClickCallback?.onItemClicked(event)}
//            btn.setOnClickListener { onItemClickCallback?.onItemHistory() }
        }

    }
}