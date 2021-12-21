package com.codemul.pabmul.helloworld

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.codemul.pabmul.helloworld.data.DaftarEvent
import com.codemul.pabmul.helloworld.data.Event
import com.codemul.pabmul.helloworld.databinding.ActivityDaftarEventBinding
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

    private val dataIntent by lazy {
        intent.getStringExtra(DetailEventActivity.id_event)
    }

    private val dataFee by lazy {
        intent.getIntExtra(DetailEventActivity.id_fee, 0)
    }
//    var daftarDialog: AlertDialog? = null

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
        binding.btnDaftarEvent.setOnClickListener {
            setDataToFirebase()
        }
    }

    private fun setDataToFirebase() {
//        val df = databaseRef.getReference("DaftarEvent").child(currentUser!!.uid)
        val df = databaseRef.getReference("Users").child(currentUser!!.uid).child("eventTerdaftar")
        val daftarEvent = DaftarEvent()

        daftarEvent.namaTim = binding.nameTeam.text.toString().trim()
        daftarEvent.anggota = binding.nameAnggota.text.toString().trim()
        daftarEvent.noPerwakilan = binding.noPerwakilan.text.toString().trim()
        daftarEvent.idEvent = dataIntent.toString()

//        val dfEvent = databaseRef.getReference("event")
//        daftarEvent.idEvent = dfEvent.child("id")

        df.child(daftarEvent.idEvent!!).setValue(daftarEvent).addOnSuccessListener {
            binding.nameTeam.text?.clear()
            binding.nameAnggota.text?.clear()
            binding.noPerwakilan.text?.clear()

            if(dataFee == 0) {
                Toast.makeText(this, "Daftar Event Berhasil", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
                //intent ketempat lain (history)
            } else {
                showPopUp()
            }

        }.addOnFailureListener {
            Toast.makeText(this, "Daftar Event Gagal", Toast.LENGTH_SHORT).show()
        }

    }

    private fun showPopUp() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_popup)
        val btnClose: Button = dialog.findViewById(R.id.btn_popup)
        btnClose.setOnClickListener {
            dialog.dismiss()
            finish()
        }
        dialog.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}