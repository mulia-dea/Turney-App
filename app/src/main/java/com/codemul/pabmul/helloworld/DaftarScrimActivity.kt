package com.codemul.pabmul.helloworld

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.codemul.pabmul.helloworld.data.Scrim
import com.codemul.pabmul.helloworld.databinding.ActivityDaftarScrimBinding
import com.codemul.pabmul.helloworld.db.RealtimeDatabase
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class DaftarScrimActivity : AppCompatActivity() {
    lateinit var tglScrim: String
    lateinit var binding: ActivityDaftarScrimBinding

    private var databaseRef: DatabaseReference? = null
    private var database : FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarScrimBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        databaseRef = Firebase.database.reference.child("scrim")

        setViewListener()

        binding.buttonSubmitScrim.setOnClickListener {
            setDataToFireBase()
        }
    }

    private fun setViewListener() {
        addItemToSpinner()
        setDateTime()
    }

    private fun addItemToSpinner() {
        val jenisGame = arrayOf("DOTA 2", "PUBG", "Valorant", "Mobile Legends")

        val jenisGameAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, jenisGame)
        jenisGameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerJenisGameScrim.adapter = jenisGameAdapter
    }

    private fun setDateTime() {
        binding.etTglPelaksanaanScrim.setOnClickListener {
            val cal = Calendar.getInstance()
            val date = DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    val newDate = Calendar.getInstance()
                    newDate[year, monthOfYear] = dayOfMonth
                    val bulan = arrayOf(
                        "Januari", "Februari", "Maret", "April", "Mei",
                        "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"
                    )
                    tglScrim = dayOfMonth.toString() + " " + bulan[monthOfYear] + " " + year
                    binding.etTglPelaksanaanScrim.setText(tglScrim)
                }, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )

            date.show()
        }

    }

    private fun covertToAppropiateDateFormat(date: String){
        val bulan = arrayOf(
            "Januari", "Februari", "Maret", "April", "Mei",
            "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"
        )

        if (binding.etTglPelaksanaanScrim.text.toString().contains('/')){

        }
    }

    fun setDataToFireBase(){
        val scrim = Scrim()

        scrim.id = UUID.randomUUID().toString()
        scrim.jenis_game = binding.spinnerJenisGameScrim.selectedItem.toString()
        scrim.jumlah_pemain = binding.etJumlahPemainScrim.text.toString().toInt()
        scrim.nama_penyelenggara = binding.etNamaPenyelenggaraScrim.text.toString()
        scrim.tgl_pelaksanaan_scrim = tglScrim
        scrim.informasi_scrim = binding.etInformationScrim.text.toString()

        val newPost = databaseRef!!.push() // add new item to database
        newPost.child("id").setValue(scrim.id)
        newPost.child("nama_penyelenggara").setValue(scrim.nama_penyelenggara)
        newPost.child("jenis_game").setValue(scrim.jenis_game)
        newPost.child("tgl_akhir").setValue(scrim.tgl_pelaksanaan_scrim)
        newPost.child("jumlah_pemain").setValue(scrim.jumlah_pemain)
        newPost.child("information").setValue(scrim.informasi_scrim)
    }
}