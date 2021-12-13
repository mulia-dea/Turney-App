package com.codemul.pabmul.helloworld

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codemul.pabmul.helloworld.data.DataEvent

class EventAdapter() : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private val eventList=  ArrayList<DataEvent>()
//    var listEvents = ArrayList<DataEvent>()
//    set(listEvents) {
//        if (listEvents.size >0){
//            this.listEvents.clear()
//        }
//        this.listEvents.addAll(listEvents)
//    }

//    fun addItem(event: DataEvent){
//        this.listEvents.add(event)
//        notifyItemInserted(this.listEvents.size - 1)
//    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EventAdapter.EventViewHolder {
        return EventViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_event, parent, false))
    }

    override fun onBindViewHolder(holder: EventAdapter.EventViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    inner class EventViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var tvName : TextView = itemView.findViewById(R.id.tv_name_game)
        var tvTglEvent : TextView = itemView.findViewById(R.id.tv_tgl_event)
        var image : ImageView = itemView.findViewById(R.id.img_list_event)

        fun bind(event: DataEvent) {
            tvName.text = event.name
            tvTglEvent.text = event.tgl_akhir

            if (event.image != null) {
                image.setImageBitmap(BitmapFactory.decodeFile(event.image))
//                holder.roundedImageView.visibility = View.VISIBLE
//            }
                //            else {
//                holder.roundedImageView.visibility = View.GONE
//            }

            }
        }

//        init {
//            var tvName : EditText = itemView.findViewById(R.id.name_event)
//            var tglEvent : EditText = itemView.findViewById(R.id.tgl_event)
//            var venue: EditText = itemView.findViewById(R.id.venue)
//            var fee : EditText = itemView.findViewById(R.id.fee)
//            var cp : EditText = itemView.findViewById(R.id.cp)
//            var image : ImageView = itemView.findViewById(R.id.img_event)
//        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAddEvent(event: List<DataEvent>){
        eventList.clear()
        eventList.addAll(event)
        notifyDataSetChanged()
    }
}