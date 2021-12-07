package com.codemul.pabmul.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewList = findViewById<RecyclerView>(R.id.rv_featured_events)

        val eventList = ArrayList<EsportEvent>()
        eventList.add(EsportEvent(R.drawable.porsematik))
        eventList.add(EsportEvent(R.drawable.valorant))

        val adapter = CustomAdapter(this, eventList)
        viewList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewList.adapter = adapter
    }

    private fun showEvent(){

    }
}