package com.codemul.pabmul.helloworld

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemul.pabmul.helloworld.data.Scrim
import com.codemul.pabmul.helloworld.databinding.ActivityHistoryScrimTerdaftarBinding
import com.codemul.pabmul.helloworld.databinding.ActivityScrimBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import java.util.ArrayList

class HistoryScrimTerdaftar : AppCompatActivity() {

    private lateinit var adapter: ScrimTerdaftarAdapter
    private var storage: FirebaseStorage? = null
    private var databaseRef: DatabaseReference? = null
    private var dbListerner: ValueEventListener? = null
    private lateinit var scrimList: MutableList<Scrim>
    private lateinit var binding: ActivityHistoryScrimTerdaftarBinding

    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val dataBase by lazy {
        FirebaseDatabase.getInstance()
    }

    private val currentUser by lazy {
        firebaseAuth.currentUser
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryScrimTerdaftarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "History Scrim"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rvScrimHistory.setHasFixedSize(true)
        binding.rvScrimHistory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        scrimList = ArrayList()
        adapter = ScrimTerdaftarAdapter(scrimList)
        binding.rvScrimHistory.adapter = adapter

        getDataHistoryFromDataBase()
    }

    private fun getDataHistoryFromDataBase() {
        storage = FirebaseStorage.getInstance()
        databaseRef = FirebaseDatabase.getInstance().getReference("Users").child(currentUser!!.uid).child("scrimTerdaftar")
        Log.d("dataaaa", databaseRef.toString())
//        Log.d("data ref", databaseRef.toString())
        dbListerner = databaseRef?.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                scrimList.clear()
                for (eventSnap in snapshot.children) {
                    val upload = eventSnap.getValue(Scrim::class.java)
                    upload!!.id = eventSnap.key
                    scrimList.add(upload)
                    Log.d("scrimList val ", scrimList.toString())
                     // debug
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HistoryScrimTerdaftar, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}