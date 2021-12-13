package com.codemul.pabmul.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codemul.pabmul.helloworld.data.DataEvent

class EventActivity : AppCompatActivity() {
    private lateinit var adapter: EventAdapter
    private lateinit var rvEvent : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        rvEvent = findViewById(R.id.rv_event)

        showRecycleEvent()
    }

    private fun showRecycleEvent(){
        rvEvent.setHasFixedSize(true)
        adapter = EventAdapter()

        rvEvent.layoutManager = LinearLayoutManager(this)
        rvEvent.adapter = adapter
    }
}