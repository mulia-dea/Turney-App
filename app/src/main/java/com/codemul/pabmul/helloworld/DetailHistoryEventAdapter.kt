package com.codemul.pabmul.helloworld

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codemul.pabmul.helloworld.data.DaftarEvent
import com.codemul.pabmul.helloworld.data.Event

class DetailHistoryEventAdapter(private val context: Context, private val listDetailEvent: List<DaftarEvent>): RecyclerView.Adapter<DetailHistoryEventAdapter.HistoryEventViewHolder>() {
    inner class HistoryEventViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var tvName : TextView = itemView.findViewById(R.id.dtl_nama_tim)
        var tvAnggota : TextView = itemView.findViewById(R.id.dtl_nama_anggota)
        var tvHp : TextView = itemView.findViewById(R.id.dtl_no_hp)

        fun bind(detailEvent : DaftarEvent) {
            tvName.text = detailEvent.namaTim
            tvAnggota.text = detailEvent.anggota
            tvHp.text = detailEvent.noPerwakilan
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHistoryEventAdapter.HistoryEventViewHolder {
        return HistoryEventViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_detail_history_event_admin, parent, false))
    }

    override fun onBindViewHolder(holder: HistoryEventViewHolder, position: Int) {
        holder.bind(listDetailEvent[position])
    }

    override fun getItemCount(): Int {
        return listDetailEvent.size
    }
}