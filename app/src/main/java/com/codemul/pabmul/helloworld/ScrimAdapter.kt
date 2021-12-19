package com.codemul.pabmul.helloworld

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codemul.pabmul.helloworld.data.Scrim
import com.codemul.pabmul.helloworld.databinding.ListScrimBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ScrimAdapter(private val content: MutableList<Scrim>) :
    RecyclerView.Adapter<ScrimAdapter.ScrimViewHolder>() {

    private lateinit var buttonListener: OnButtonJoinListener

    fun setOnClickButton(listener: OnButtonJoinListener) {
        buttonListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ScrimAdapter.ScrimViewHolder = ScrimViewHolder(
        ListScrimBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ScrimAdapter.ScrimViewHolder, position: Int) {
        holder.bind(content[position])
    }

    override fun getItemCount(): Int {
        return content.size
    }

    inner class ScrimViewHolder(val view: ListScrimBinding) :
        RecyclerView.ViewHolder(view.root) {

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

            view.buttonJoin.setOnClickListener {
                buttonListener.let {
                    if (item.jumlah_pemain_sekarang != item.jumlah_pemain) {
                        view.buttonJoin.isEnabled = true
                        if (item.isJoin == 0) {
                            Log.d("Masuk if", "msk if boi")
                            item.isJoin = 1
                            item.jumlah_pemain_sekarang += 1
                            view.buttonJoin.setText("Unjoin")
                            buttonListener.onButtonJoinClick(item, absoluteAdapterPosition)
                        } else {
                            item.jumlah_pemain_sekarang -= 1
                            item.isJoin = 0
                            Log.d("Masuk else", "msk else boi")
                            view.buttonJoin.setText("Join")
                            buttonListener.onButtonUnjoinClick(item, absoluteAdapterPosition)
                        }

                    } else {
                        view.buttonJoin.isEnabled = false
                    }
                }
            }
        }
    }


    interface OnButtonJoinListener {
        fun onButtonJoinClick(content: Scrim, position: Int)
        fun onButtonUnjoinClick(content: Scrim, position: Int)
    }

//    fun setData(item: List<Scrim>){
//        this.content.clear()
//        this.content.addAll(item)
//        notifyDataSetChanged()
//    }
}