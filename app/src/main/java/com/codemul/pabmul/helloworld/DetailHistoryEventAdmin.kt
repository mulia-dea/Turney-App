package com.codemul.pabmul.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemul.pabmul.helloworld.data.DaftarEvent
import com.codemul.pabmul.helloworld.data.Event
import com.codemul.pabmul.helloworld.databinding.ActivityDetailHistoryEventAdminBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage

class DetailHistoryEventAdmin : AppCompatActivity() {
    private var storage: FirebaseStorage? = null
    private var databaseRef: DatabaseReference? = null

    private val idEvent by lazy {
        intent.getStringExtra(id_event)
    }

    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

//    private val databaseRef by lazy {
//        FirebaseDatabase.getInstance()
//    }

    private val currentUser by lazy {
        firebaseAuth.currentUser
    }

    private lateinit var binding: ActivityDetailHistoryEventAdminBinding
    private lateinit var adapterDetailEvent : DetailHistoryEventAdapter
    private lateinit var eventDetailList: MutableList<DaftarEvent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHistoryEventAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
    }

    private fun getData() {
        binding.rvDetailEventAdmin.setHasFixedSize(true)

        binding.rvDetailEventAdmin.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        eventDetailList = ArrayList()
        adapterDetailEvent = DetailHistoryEventAdapter(this, eventDetailList)
        binding.rvDetailEventAdmin.adapter = adapterDetailEvent

        databaseRef = FirebaseDatabase.getInstance().getReference("DaftarEvent")
        databaseRef!!.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("snapshot", snapshot.toString())
                snapshot.children.forEach {
                    Log.d("snapshot childrem", snapshot.children.toString())
                    val dataPeserta = it.child(idEvent.toString()).getValue(DaftarEvent::class.java)

                    //bikin variabel untuk menampung data peserta
                    if (dataPeserta != null) {
                        eventDetailList.add(dataPeserta)
                    }

                    Log.d("data peserta", dataPeserta.toString())

                }
                //set data to dapter
                adapterDetailEvent.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    companion object {
        var id_event = "id_Event"
        var id_fee = "id_fee"
    }
}