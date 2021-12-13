package com.codemul.pabmul.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codemul.pabmul.helloworld.data.Event
import com.codemul.pabmul.helloworld.db.RealtimeDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class EventActivity : AppCompatActivity() {
    private lateinit var adapterEvent: EventAdapter
    private lateinit var rvEvent : RecyclerView

    private val db = RealtimeDatabase.instance()
    private val listKey = mutableListOf<String>()
    private val listEvent = mutableListOf<Event>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        rvEvent = findViewById(R.id.rv_event)

        getDataEvent()
    }

//    private fun showRecycleEvent(){
//        rvEvent.setHasFixedSize(true)
//        adapter = EventAdapter()
//
//        rvEvent.layoutManager = LinearLayoutManager(this)
//        rvEvent.adapter = adapter
//    }

    private fun getDataEvent(){
        db.getReference("event").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                listKey.clear()
                listEvent.clear()

                //get key(user id) from user
                snapshot.children.map {
                    it.key?.let { eventId ->
                        listKey.add(eventId)
                    }
                }

                listKey.map {
                    listEvent.add(
                        Event(
                            id = snapshot.child(it).child("id").value.toString(),
                            name = snapshot.child(it).child("name").value.toString(),
                            tgl_event = snapshot.child(it).child("tgl_event").value.toString(),
                            image = snapshot.child(it).child("image").value.toString()
                        )
                    )
                }

                adapterEvent = EventAdapter(listEvent.sortedBy { it.name })
                initRecylerView()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DB ERROR", error.message)
            }

        })
    }

    private fun initRecylerView() {
        rvEvent.apply {
            layoutManager = LinearLayoutManager(this@EventActivity, LinearLayoutManager.VERTICAL, false)
            adapter = adapterEvent
        }
    }
}