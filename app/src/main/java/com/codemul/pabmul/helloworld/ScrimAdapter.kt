package com.codemul.pabmul.helloworld

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ScrimAdapter(private val content: ArrayList<ScrimListContent>) : RecyclerView.Adapter<ScrimAdapter.ScrimViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ScrimAdapter.ScrimViewHolder {
        return ScrimViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_scrim, parent, false))
    }

    override fun onBindViewHolder(holder: ScrimAdapter.ScrimViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return content.size
    }

    inner class ScrimViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var poster: ImageView
        var playerCount: TextView
        var gameType: TextView

        init {
            poster =
                itemView.findViewById(R.id.img_scrim)
            playerCount = itemView.findViewById(R.id.tv_player_count)
            gameType = itemView.findViewById(R.id.tv_name_game)
        }

        internal fun bind(position: Int) {
            poster.setImageResource(content[position].image)
            playerCount.setText(content[position].playerJoined.toString() + "/" + content[position].totalPlayer.toString() )
            gameType.setText(content[position].type)
        }
    }
}