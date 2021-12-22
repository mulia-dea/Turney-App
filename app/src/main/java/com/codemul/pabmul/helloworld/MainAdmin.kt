package com.codemul.pabmul.helloworld

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codemul.pabmul.helloworld.databinding.ActivityMainAdminBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainAdmin : AppCompatActivity() {
    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val databaseRef by lazy {
        FirebaseDatabase.getInstance()
    }

    private val currentUser by lazy {
        firebaseAuth.currentUser
    }

    private lateinit var binding: ActivityMainAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showProfile()
        toProfile()
        showBuatEvent()
        toDaftarScrim()
        listScrim()
        toHistoryEvent()
        toScrimHistory()
    }

    private fun showBuatEvent(){
        binding.ivBuatEventAdmin.setOnClickListener {
            startActivity(Intent(this, CreateEventActivity::class.java))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showProfile() {
        val df = databaseRef.getReference("Users")
        df.child(currentUser!!.uid).get().addOnSuccessListener {
            val username = it.child("name").value.toString()
            val id = it.child("id").value.toString()
            binding.tvIdNama.text = username
            binding.tvIdAkun.text = "ID: $id"

        }
    }

    private fun toProfile() {
        binding.relativeProfileAdmin.setOnClickListener {
            startActivity(Intent(this, ProfileAdminActivity::class.java))
        }
    }

    private fun toDaftarScrim() {
        binding.ivDaftarScrimAdmin.setOnClickListener {
            startActivity(Intent(this, DaftarScrimActivity::class.java))
        }
    }

    private fun toHistoryEvent() {
        binding.llEventAdmin.setOnClickListener {
            startActivity(Intent(this, HistoryEventAdmin::class.java))
        }
    }

    private fun listScrim() {
        binding.ivScrimAdmin.setOnClickListener {
            startActivity(Intent(this, ScrimActivity::class.java))
        }
    }

    private fun toScrimHistory() {
        binding.ivHistoryScrim.setOnClickListener {
            startActivity(Intent(this, HistoryScrimTerdaftar::class.java))
        }
    }
}