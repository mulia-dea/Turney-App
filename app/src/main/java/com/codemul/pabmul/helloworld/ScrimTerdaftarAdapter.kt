package com.codemul.pabmul.helloworld

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codemul.pabmul.helloworld.data.Scrim
import com.codemul.pabmul.helloworld.databinding.ListScrimBinding
import com.codemul.pabmul.helloworld.databinding.ListScrimTerdaftarBinding

class ScrimTerdaftarAdapter () :
    RecyclerView.Adapter<ScrimTerdaftarAdapter.ScrimTerdaftarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ScrimTerdaftarViewHolder = ScrimTerdaftarViewHolder(ListScrimTerdaftarBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    )
    )

    override fun onBindViewHolder(holder: ScrimTerdaftarAdapter.ScrimTerdaftarViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ScrimTerdaftarViewHolder(val view: ListScrimTerdaftarBinding) : RecyclerView.ViewHolder(view.root){
        @SuppressLint("SetTextI18n")
        fun bind(item: Scrim) {
            when (item.jenis_game) {
                "Valorant" -> view.imgScrim.setImageResource(R.drawable.valorant)
                "PUBG" -> view.imgScrim.setImageResource(R.drawable.pubg_pic)
                "DOTA 2" -> view.imgScrim.setImageResource(R.drawable.dota)
                "Mobile Legends" -> view.imgScrim.setImageResource(R.drawable.mobilelegends)
                else -> 0
            }

            view.tvPlayerCount.text = "${item.jumlah_pemain_sekarang}/${item.jumlah_pemain}"
            view.tvNameGame.text = item.jenis_game
            view.tvNamaPenyelenggara.text = item.nama_penyelenggara
            view.tvTglScrim.text = item.tgl_akhir
        }
    }


}