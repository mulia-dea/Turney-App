package com.codemul.pabmul.helloworld

class EsportEvent(type: String) {

    var gameType: String
    var posterImg: Int

    init {

        this.gameType = type

        when(gameType){
            "valorant" -> posterImg = R.drawable.valorant
            "porsematik" -> posterImg = R.drawable.porsematik
            else -> posterImg = 0
        }
    }


}