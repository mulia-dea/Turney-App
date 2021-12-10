package com.codemul.pabmul.helloworld

import android.content.Context
import android.widget.Toast

class ScrimListContent(type: String, totalPlayer: Int, private val context: Context) {
    var type: String
    var image: Int
    var playerJoined: Int = 0
    var totalPlayer: Int
    var canBeJoined: Boolean = true

    init {
        this.type = type
        this.totalPlayer = totalPlayer

        image = when(this.type){
            "Valorant" -> R.drawable.valorant
            "PUBG" -> R.drawable.pubg_pic
            "DOTA 2" -> R.drawable.dota
            "Mobile Legend" -> R.drawable.mobilelegends
            else -> 0
        }

    }

    public fun addPlayer(){

        if (canBeJoined){
            this.playerJoined += 1
        }
        if(playerJoined > totalPlayer){
            playerJoined -= 1
            canBeJoined = false
            Toast.makeText(context,"Scrim is Full", Toast.LENGTH_SHORT).show()
        }

    }

}