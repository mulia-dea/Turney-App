package com.codemul.pabmul.helloworld

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
        intent.getParcelableExtra<Event>(id_event)
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
    private var mutableDetail = mutableListOf<DaftarEvent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHistoryEventAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
    }

    private fun getData() {
        binding.rvDetailEventAdmin.setHasFixedSize(true)

        binding.rvDetailEventAdmin.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        eventDetailList = ArrayList()
        adapterDetailEvent = DetailHistoryEventAdapter(this, mutableDetail)
        binding.rvDetailEventAdmin.adapter = adapterDetailEvent

        databaseRef = FirebaseDatabase.getInstance().getReference("DaftarEvent")

        databaseRef!!.addChildEventListener(object: ChildEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("snapshot", snapshot.toString())

                //value keluar tapi cuman awal, id event taruh mana?
                for(evenSnapshot in snapshot.children){
                    val data = evenSnapshot.getValue(DaftarEvent::class.java)

                    if(data?.idEvent == idEvent?.id) {
                        var simpan = mutableDetail.add(data!!)
                        Log.d("event detail", simpan.toString())
                    }
                }
                adapterDetailEvent.notifyDataSetChanged()
//                snapshot.children.forEach {
//                    Log.d("snappppp", it.value.toString()) //it.value = {namaTim=coba lagi, anggota=aaaa, idEvent=-MqxMESC3aCoDjF58x0S, noPerwakilan=123456789098}
//                    Log.d("snappppp", it.key.toString())    //MqxMESC3aCoDjF58x0S
////                    Log.d("idEvent", idEvent.toString()) //-MrT8ZSQUgJGHCcgp2zl
//
//                    if(idEvent!!.id == it.key) {
//                        val data = it.getValue(DaftarEvent::class.java)
//                        idEvent!!.id = it.key
//                        Log.d("dataa", data.toString())
//                        eventDetailList.add(data)
//                    }
//                    adapterDetailEvent.notifyDataSetChanged()
//                }


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