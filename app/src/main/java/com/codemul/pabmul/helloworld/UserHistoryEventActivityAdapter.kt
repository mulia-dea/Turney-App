package com.codemul.pabmul.helloworld

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codemul.pabmul.helloworld.data.DaftarEvent

class UserHistoryEventActivityAdapter(
    private val context: Context,
    private val listEvent: List<DaftarEvent>
) : RecyclerView.Adapter<UserHistoryEventActivityAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.history_event_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(listEvent[position])
    }

    override fun getItemCount(): Int {
        return listEvent.size
    }

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTim: TextView = itemView.findViewById(R.id.tv_nama_tim_history)
        var tvAnggota: TextView = itemView.findViewById(R.id.tv_anggota_tim_history)
        var tvNoPerwakilan: TextView = itemView.findViewById(R.id.tv_no_history)

        fun bind(event: DaftarEvent) {
            tvTim.text = event.namaTim
            tvAnggota.text = event.anggota
            tvNoPerwakilan.text = event.noPerwakilan
        }

    }
}