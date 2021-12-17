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

class ScrimAdapter(private val content: MutableList<Scrim>) :
    RecyclerView.Adapter<ScrimAdapter.ScrimViewHolder>() {

    private lateinit var buttonListener: OnButtonJoinListener

    interface OnButtonJoinListener {
        fun buttonClick(contentPosition: Int)
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

//            joinButton.setOnClickListener {
//                buttonListener.buttonClick(bindingAdapterPosition)
//                content[bindingAdapterPosition].addPlayer()
//                playerCount.setText(content[bindingAdapterPosition].playerJoined.toString() + "/" + content[bindingAdapterPosition].totalPlayer.toString() )
//                Log.d("playerJoined: ", content[bindingAdapterPosition].playerJoined.toString())
//            }
        }


        internal fun bind(position: Int) {
            when(content[bindingAdapterPosition].jenis_game){
                "Valorant" -> poster.setImageResource(R.drawable.valorant)
                "PUBG" -> poster.setImageResource(R.drawable.pubg_pic)
                "DOTA 2" -> poster.setImageResource(R.drawable.dota)
                "Mobile Legends" -> poster.setImageResource(R.drawable.mobilelegends)
                else -> 0
            }
            playerCount.setText(content[position].jumlah_pemain_sekarang.toString() + "/" + content[position].jumlah_pemain.toString())
            gameType.setText(content[position].jenis_game)
        }
    }
}