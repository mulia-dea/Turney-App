package com.codemul.pabmul.helloworld

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class DaftarScrimActivity : AppCompatActivity() {
    lateinit var namaPenyelenggara: TextInputEditText
    lateinit var spinnerJenisGame: Spinner
    lateinit var jumlahPemain: TextInputEditText
    lateinit var datePickScrim: ImageView
    lateinit var moreInfo: TextInputEditText
    lateinit var tglPelaksanaanScrim: TextInputEditText

    lateinit var tglScrim: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_scrim)

        findViewById()
        setViewListener()
    }

    private fun findViewById() {
        namaPenyelenggara = findViewById(R.id.et_nama_penyelenggara_scrim)
        spinnerJenisGame = findViewById(R.id.spinner_jenis_game_scrim)
        jumlahPemain = findViewById(R.id.et_jumlah_pemain_scrim)
        tglPelaksanaanScrim = findViewById(R.id.et_tgl_pelaksanaan_scrim)
        datePickScrim = findViewById(R.id.dp_scrim)
        moreInfo = findViewById(R.id.et_information_scrim)
    }

    private fun setViewListener() {
        addItemToSpinner()
        setDateTime()
    }

    private fun addItemToSpinner() {
        val jenisGame = arrayOf("DOTA 2", "PUBG", "Valorant", "Mobile Legends")

        var jenisGameAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, jenisGame)
        jenisGameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerJenisGame.adapter = jenisGameAdapter
    }

    private fun setDateTime() {
        datePickScrim.setOnClickListener {
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
                    tglPelaksanaanScrim.setText(tglScrim)
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

        if (tglPelaksanaanScrim.text.toString().contains('/')){

        }
    }

    fun setDataToFireBase(){

    }
}