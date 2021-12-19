package com.codemul.pabmul.helloworld

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codemul.pabmul.helloworld.data.Scrim
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ScrimAdapter(private val content: MutableList<Scrim>) :
    RecyclerView.Adapter<ScrimAdapter.ScrimViewHolder>() {

    private lateinit var buttonListener: OnButtonJoinListener


    interface OnButtonJoinListener {
        fun onButtonJoinClick(content: Scrim)
        fun onButtonUnjoinClick(content: Scrim)
    }

    fun setOnClickButton(listener: OnButtonJoinListener) {
        buttonListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ScrimAdapter.ScrimViewHolder {
        return ScrimViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_scrim, parent, false),
            buttonListener
        )
    }

    override fun onBindViewHolder(holder: ScrimAdapter.ScrimViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return content.size
    }

    inner class ScrimViewHolder(itemView: View, buttonListener: OnButtonJoinListener) :
        RecyclerView.ViewHolder(itemView) {

        var poster: ImageView
        var playerCount: TextView
        var gameType: TextView
        var joinButton: Button


        init {
            poster = itemView.findViewById(R.id.img_scrim)
            playerCount = itemView.findViewById(R.id.tv_player_count)
            gameType = itemView.findViewById(R.id.tv_name_game)
            joinButton = itemView.findViewById(R.id.button_join)

//            var jumlahPemainSekarang = content[bindingAdapterPosition].jumlah_pemain_sekarang
//            var jumlahPemain = content[bindingAdapterPosition].jumlah_pemain
//            Log.d("index", bindingAdapterPosition.toString())

            content.forEach {
                if (it.jumlah_pemain_sekarang == it.jumlah_pemain) {
                    joinButton.isEnabled = false
                    //ganti background warna button
                } else {
                    joinButton.isEnabled = true
                    if (it.isJoin == 0) {
                        Log.d("Masuk if", "msk if boi")
                        buttonListener.let {
                            joinButton.setOnClickListener {
                                buttonListener.onButtonJoinClick(content[bindingAdapterPosition])
                                joinButton.setText("Unjoin")
                            }
                        }
                        Log.d("join button", it.isJoin.toString())
                    } else{
                        Log.d("Masuk else", "msk else boi")
                        buttonListener.let {
                            joinButton.setOnClickListener {
                                buttonListener.onButtonUnjoinClick((content[bindingAdapterPosition]))
                                joinButton.setText("Join")
                            }
                        }
                    }
                }
            }
        }


        internal fun bind(position: Int) {
            when (content[position].jenis_game) {
                "Valorant" -> poster.setImageResource(R.drawable.valorant)
                "PUBG" -> poster.setImageResource(R.drawable.pubg_pic)
                "DOTA 2" -> poster.setImageResource(R.drawable.dota)
                "Mobile Legends" -> poster.setImageResource(R.drawable.mobilelegends)
                else -> 0
            }
            playerCount.text =
                "${content[position].jumlah_pemain_sekarang}/${content[position].jumlah_pemain}"
            gameType.setText(content[position].jenis_game)
        }
    }

//    fun setData(item: List<Scrim>){
//        this.content.clear()
//        this.content.addAll(item)
//        notifyDataSetChanged()
//    }
}