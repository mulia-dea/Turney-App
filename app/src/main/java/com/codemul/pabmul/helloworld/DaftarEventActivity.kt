package com.codemul.pabmul.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.codemul.pabmul.helloworld.data.DaftarEvent
import com.codemul.pabmul.helloworld.data.Event
import com.codemul.pabmul.helloworld.databinding.ActivityDaftarEventBinding
import com.codemul.pabmul.helloworld.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DaftarEventActivity : AppCompatActivity() {
    private val firebaeAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val databaseRef by lazy {
        FirebaseDatabase.getInstance()
    }

    private val currentUser by lazy {
        firebaeAuth.currentUser
    }

    private val dataIntent by lazy{
        intent.getStringExtra(DetailEventActivity.id_event)
    }

    private lateinit var binding: ActivityDaftarEventBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Daftar Event"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityDaftarEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        submitDaftar()

    }

    private fun submitDaftar() {
        binding.btnDaftarEvent.setOnClickListener{
            setDataToFirebase()
        }
    }

    private fun setDataToFirebase(){

        val df = databaseRef.getReference("DaftarEvent")
        val daftarEvent = DaftarEvent()
        val event = Event()

        daftarEvent.namaTim = binding.nameTeam.text.toString().trim()
        daftarEvent.anggota = binding.nameAnggota.text.toString().trim()
        daftarEvent.noPerwakilan = binding.noPerwakilan.text.toString().trim()
        daftarEvent.id = currentUser?.uid
        daftarEvent.idEvent = dataIntent.toString()

//        val dfEvent = databaseRef.getReference("event")
//        daftarEvent.idEvent = dfEvent.child("id")

        df.child(currentUser!!.uid).setValue(daftarEvent).addOnSuccessListener {
            binding.nameTeam.text?.clear()
            binding.nameAnggota.text?.clear()
            binding.noPerwakilan.text?.clear()

            Toast.makeText(this, "Daftar Event Berhasil", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this, "Daftar Event Gagal", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}