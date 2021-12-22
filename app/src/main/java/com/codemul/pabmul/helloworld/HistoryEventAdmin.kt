package com.codemul.pabmul.helloworld

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemul.pabmul.helloworld.data.Event
import com.codemul.pabmul.helloworld.databinding.ActivityHistoryEventAdminBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage

class HistoryEventAdmin : AppCompatActivity() {
    private lateinit var adapterEvent: EventAdapter
    private lateinit var binding: ActivityHistoryEventAdminBinding
    private lateinit var eventList: MutableList<Event>
    private var storage: FirebaseStorage? = null
    private var databaseRef: DatabaseReference? = null

//    lateinit var idEvent: String
    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val currentUser by lazy {
        firebaseAuth.currentUser
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryEventAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
    }

    private fun getData() {
        binding.rvHistoryEvent.setHasFixedSize(true)
        binding.rvHistoryEvent.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        eventList = ArrayList()
        adapterEvent = EventAdapter(this, eventList)
        binding.rvHistoryEvent.adapter = adapterEvent

        storage = FirebaseStorage.getInstance()
//        Log.d("Data", event.id.toString())
        databaseRef = FirebaseDatabase.getInstance().getReference("event")
        databaseRef!!.addChildEventListener(object : ChildEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("key", snapshot.key.toString())
                val data = snapshot.getValue(Event::class.java)
//                idEvent = previousChildName.toString()
                data!!.id = snapshot.key

                if (data!!.id_penyelenggara == currentUser!!.uid) {
                    eventList.add(data)

                    Log.d("Data List", eventList.toString())
                }

                adapterEvent.notifyDataSetChanged()

                Log.d("previouschildname", previousChildName.toString())

            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

        adapterEvent.setOnItemClickCallback(object : EventAdapter.OnItemClickCallback {
            override fun onItemClicked(event: Event) {
                startActivity(Intent(this@HistoryEventAdmin,
                    DetailHistoryEventAdmin::class.java).putExtra(DetailHistoryEventAdmin.id_event, event))
            }
//
//            override fun onItemHistory() {
//
//            }


        })

    }
}