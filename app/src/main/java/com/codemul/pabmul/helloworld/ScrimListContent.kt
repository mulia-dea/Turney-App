package com.codemul.pabmul.helloworld

class ScrimListContent(type: String, totalPlayer: Int) {
    var type: String
    var image: Int
    var playerJoined: Int = 0
    var totalPlayer: Int

    init {
        this.type = type
        this.totalPlayer = totalPlayer

        when(this.type){
            "Valorant" -> image = R.drawable.valorant
            "PUBG" -> image = R.drawable.pubg_pic
            "DOTA 2" -> image = R.drawable.dota
            "Mobile Legend" -> image = R.drawable.mobilelegends
            else -> image = 0
        }
    }

}