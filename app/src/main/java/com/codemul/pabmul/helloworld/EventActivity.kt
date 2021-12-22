package com.codemul.pabmul.helloworld

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codemul.pabmul.helloworld.data.DaftarEvent
import com.codemul.pabmul.helloworld.data.Event
import com.codemul.pabmul.helloworld.databinding.ActivityEventBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class EventActivity : AppCompatActivity() {
    private lateinit var adapterEvent: EventAdapter
    private lateinit var rvEvent: RecyclerView

    private lateinit var binding: ActivityEventBinding
    private var storage: FirebaseStorage? = null
    private var databaseRef: DatabaseReference? = null
    private var database: FirebaseDatabase? = null
    private var dbListener: ValueEventListener? = null
    private val listKey = mutableListOf<String>()
    private val listEvent = mutableListOf<Event>()
    private lateinit var eventList: MutableList<Event>

    //nampung id event
    lateinit var idEvent: String

    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

//    private val databaseRef by lazy {
//        FirebaseDatabase.getInstance()
//    }

    private val currentUser by lazy {
        firebaseAuth.currentUser
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "List Event"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setContentView(R.layout.activity_event)

        rvEvent = findViewById(R.id.rv_event)

        getData()
    }

    private fun getData() {
        rvEvent.setHasFixedSize(true)
        rvEvent.layoutManager =
            LinearLayoutManager(this@EventActivity, LinearLayoutManager.VERTICAL, false)

        eventList = ArrayList()
        adapterEvent = EventAdapter(this, eventList)
        rvEvent.adapter = adapterEvent

        val event = Event()
        storage = FirebaseStorage.getInstance()
//        Log.d("Data", event.id.toString())
//        databaseRef = FirebaseDatabase.getInstance().getReference("event")
//        databaseRef!!.addChildEventListener(object : ChildEventListener {
//            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
//                val data = snapshot.getValue(Event::class.java)
//                idEvent = previousChildName.toString()
//
//                if (data!!.id_penyelenggara == currentUser!!.uid) {
//                    eventList.add(data)
//
//                    Log.d("Data List", eventList.toString())
//                }
//
//                adapterEvent.notifyDataSetChanged()
//
//                adapterEvent.setOnItemClickCallback(object : EventAdapter.OnItemClickCallback {
//                    override fun onItemClicked(event: Event) {
//
//                        startActivity(Intent(this@EventActivity,
//                            detail::class.java).putExtra("idEvent", previousChildName))
//                    }
//
//                })
//            }
//
//            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
//            }
//
//            override fun onChildRemoved(snapshot: DataSnapshot) {
//            }
//
//            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//            }
//
//        })



            //nampilin user peserta tiap event ke dalam detail (untuk admin)
//        databaseRef!!.addChildEventListener(object: ChildEventListener{
//            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
//                snapshot.children.forEach {
//                    val dataPeerta = it.child(idEvent).getValue(DaftarEvent::class.java)
//
//                    //bikin variabel untuk menampung data peserta
//                    eventList.add(dataPeerta)
//
//                }
//                //set data to dapter
//                adapterEvent.notifyDataSetChanged()
//            }
//
//            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onChildRemoved(snapshot: DataSnapshot) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })

        dbListener = databaseRef?.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                eventList.clear()
                for (eventSnapshot in snapshot.children){
                    val upload = eventSnapshot.getValue(Event::class.java)
                    upload!!.id = eventSnapshot.key
                    eventList.add(upload)
                }

                adapterEvent.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@EventActivity,error.message, Toast.LENGTH_SHORT).show()
            }

        })

        adapterEvent.setOnItemClickCallback(object : EventAdapter.OnItemClickCallback {
            override fun onItemClicked(event: Event) {
                showDetailEvent(event)
            }

        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showDetailEvent(event: Event) {
//        var intent = Intent(this, DetailEventActivity::class.java)
        startActivity(Intent(this,
            DetailEventActivity::class.java).putExtra(DetailEventActivity.INTENT_DETAIL, event))
    }


//    private fun initRecylerView() {
//        rvEvent.apply {
//            layoutManager = LinearLayoutManager(this@EventActivity, LinearLayoutManager.VERTICAL, false)
//            adapter = adapterEvent
//        }
//    }
}