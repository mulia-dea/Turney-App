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
    private var onItemClickCallback: OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(event: Event)
    }
//    private val eventList=  ArrayList<DataEvent>()
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
        holder.bind(listEvent[position])
//        val listEvent = listEvent[position]
//        holder.tvName.text = listEvent.name
//        holder.tvTglEvent.text = listEvent.tgl_event
//
//        var imageUri : String?= null
//        imageUri = listEvent.image
//        Picasso.get().load(imageUri).into(holder.image)
//        Glide.with(holder.itemView.context)
//            .load(listEvent.image)
//            .apply(RequestOptions().override(200, 100))
//            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return listEvent.size
    }

    inner class EventViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var tvName : TextView = itemView.findViewById(R.id.tv_name_event)
        var image : ImageView = itemView.findViewById(R.id.img_list_event)

        var imageUri : String?= null
        fun bind(event: Event) {
            tvName.text = event.name
            imageUri = event.image
            Picasso.get().load(imageUri).into(image)
            itemView.setOnClickListener{ onItemClickCallback?.onItemClicked(event)}

//            val option = RequestOptions().placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//            Glide.with(itemView.context)
//                .load(event.image)
//                .apply(RequestOptions().override(200, 100))
//                .into(image)

        }

//            if (event.image != null) {
//                image.setImageBitmap(BitmapFactory.decodeFile(event.image))
////                holder.roundedImageView.visibility = View.VISIBLE
////            }
                //            else {
//                holder.roundedImageView.visibility = View.GONE
//            }

//            }
//        }

//        init {
//            var tvName : EditText = itemView.findViewById(R.id.name_event)
//            var tglEvent : EditText = itemView.findViewById(R.id.tgl_event)
//            var venue: EditText = itemView.findViewById(R.id.venue)
//            var fee : EditText = itemView.findViewById(R.id.fee)
//            var cp : EditText = itemView.findViewById(R.id.cp)
//            var image : ImageView = itemView.findViewById(R.id.img_event)
//        }

    }

//    @SuppressLint("NotifyDataSetChanged")
//    fun setAddEvent(event: List<DataEvent>){
//        eventList.clear()
//        eventList.addAll(event)
//        notifyDataSetChanged()
//    }
}