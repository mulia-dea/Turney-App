package com.codemul.pabmul.helloworld

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.codemul.pabmul.helloworld.data.Scrim
import com.codemul.pabmul.helloworld.databinding.ActivityDaftarScrimBinding
//import com.codemul.pabmul.helloworld.db.RealtimeDatabase
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
    private var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarScrimBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Daftar Scrim"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        database = FirebaseDatabase.getInstance()
        databaseRef = Firebase.database.reference.child("scrim")

        convertToAppropiateDateFormat()
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

        binding.dpScrim.setOnClickListener {
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

    private fun convertToAppropiateDateFormat() {
        val dateText: String = binding.etTglPelaksanaanScrim.text.toString()
        Log.d("date val", dateText)

        val bulan = arrayOf(
            "Januari", "Februari", "Maret", "April", "Mei",
            "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"
        )

//        val getBulan: Int = date.substring(3, 4).toInt()
        Log.d("get bulan", dateText)
    }

    fun setDataToFireBase() {
        val namaPenyelenggara: String = binding.etNamaPenyelenggaraScrim.text.toString()
        val jumlahPemain: String = binding.etJumlahPemainScrim.text.toString()

        if (namaPenyelenggara.isNotEmpty() && jumlahPemain.isNotEmpty()){
            val scrim = Scrim(
                id = UUID.randomUUID().toString(),
                jenis_game = binding.spinnerJenisGameScrim.selectedItem.toString(),
                jumlah_pemain = jumlahPemain.toInt(),
                nama_penyelenggara = namaPenyelenggara,
                tgl_akhir = tglScrim,
                information = binding.etInformationScrim.text.toString(),
                jumlah_pemain_sekarang = 0,
                isJoin = 0
            )

            database!!.getReference("scrim").child(scrim.id!!).setValue(scrim).addOnSuccessListener {
                Toast.makeText(this@DaftarScrimActivity, "Data Saved", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this@DaftarScrimActivity, "Data Failed to Save", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this@DaftarScrimActivity, "Fill the form first", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}