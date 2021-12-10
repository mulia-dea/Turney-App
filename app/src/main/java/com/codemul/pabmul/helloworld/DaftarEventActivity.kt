package com.codemul.pabmul.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.codemul.pabmul.helloworld.db.DatabaseHelper
import java.text.SimpleDateFormat
import java.util.*
import android.widget.DatePicker

import android.app.DatePickerDialog
import android.content.ContentValues
import android.text.InputType
import android.view.View
import com.codemul.pabmul.helloworld.data.DataEvent


class DaftarEventActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var btnAddEvent : Button
    private lateinit var edtTglEvent : EditText
    private lateinit var edtTglAkhir : EditText
    private lateinit var edtName : EditText
    private lateinit var edtFee : EditText
    private lateinit var edtVenue : EditText
    private lateinit var edtCp : EditText
    private lateinit var AwalTanggal : String
    private lateinit var AkhirTanggal : String
    private var event: DataEvent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_event)
        val db = DatabaseHelper(this)
        db.open()

        event = DataEvent()

        edtName = findViewById(R.id.name_event)
        edtTglEvent = findViewById(R.id.tgl_event)
        edtTglAkhir = findViewById(R.id.tgl_akhir)
        edtFee = findViewById(R.id.fee)
        edtVenue = findViewById(R.id.venue)
        edtCp = findViewById(R.id.cp)
        btnAddEvent = findViewById(R.id.btn_add_event)
//
//        val name = edtName.text.toString()
//        val fee = edtFee.text.toString()
//        val venue = edtVenue.text.toString()
//        val cp = edtCp.text.toString()
//        edtTglEvent.inputType = InputType.TYPE_NULL;
//        edtTglAkhir.inputType = InputType.TYPE_NULL;
//        edtTglEvent.requestFocus();

//        edtTglEvent.setOnClickListener{
//            val cal = Calendar.getInstance()
//            val date = DatePickerDialog(this,
//            { _, year, monthOfYear, dayOfMonth ->
//                val newDate = Calendar.getInstance()
//                newDate[year, monthOfYear] = dayOfMonth
//                val bulan = arrayOf("Januari", "Februari", "Maret", "April", "Mei",
//                    "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")
//                AwalTanggal = dayOfMonth.toString() + " " + bulan[monthOfYear] + " " + year
//                edtTglEvent.setText(AwalTanggal)
//            },cal.get(Calendar.YEAR),
//            cal.get(Calendar.MONTH),
//            cal.get(Calendar.DAY_OF_MONTH))
//
//            date.show()
//        }
//
//        edtTglAkhir.setOnClickListener{
//            val cal = Calendar.getInstance()
//            val date = DatePickerDialog(this,
//                { _, year, monthOfYear, dayOfMonth ->
//                    val newDate = Calendar.getInstance()
//                    newDate[year, monthOfYear] = dayOfMonth
//                    val bulan = arrayOf("Januari", "Februari", "Maret", "April", "Mei",
//                        "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")
//                    AkhirTanggal = dayOfMonth.toString() + " " + bulan[monthOfYear] + " " + year
//                    edtTglAkhir.setText(AkhirTanggal)
//                },cal.get(Calendar.YEAR),
//                cal.get(Calendar.MONTH),
//                cal.get(Calendar.DAY_OF_MONTH))
//
//            date.show()
//        }

//        val name = edtName.text.toString()
////        val fee = edtFee.text.toString()
//        val venue = edtVenue.text.toString()
//        val cp = edtCp.text.toString()
//        val tgl_event = edtTglEvent.text.toString()
//        val tgl_akhir = edtTglAkhir.text.toString()
//
//        event?.name = name
////        event?.fee = Integer.valueOf(fee)
//        event?.venue = venue
//        event?.contact = cp
//        event?.tgl_event = tgl_event
//        event?.tgl_akhir = tgl_akhir

//        val values = ContentValues()
//        values.put(DatabaseHelper.EventColumns.KEY_NAME, name)
////        values.put(DatabaseHelper.EventColumns.KEY_FEE, fee)
//        values.put(DatabaseHelper.EventColumns.KEY_TGL_EVENT, tgl_event)
//        values.put(DatabaseHelper.EventColumns.KEY_TGL_AKHIR, tgl_akhir)
//        values.put(DatabaseHelper.EventColumns.KEY_VENUE, venue)
//        values.put(DatabaseHelper.EventColumns.KEY_CP, cp)
//
//        btnAddEvent.setOnClickListener {
//            db.insertEvent(values)
//        }
//        edtTglAkhir.inputType = InputType.TYPE_NULL;
//        edtTglAkhir.requestFocus();

//        btnAddEvent.setOnClickListener {
//            db.addEvent(name, edtTglEvent.toString(), edtTglAkhir.toString(), fee.toInt(), venue, cp)
//        }

        btnAddEvent.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.btn_add_event) {
            val name = edtName.text.toString()
//        val fee = edtFee.text.toString()
        val venue = edtVenue.text.toString()
        val cp = edtCp.text.toString()
        val tgl_event = edtTglEvent.text.toString()
        val tgl_akhir = edtTglAkhir.text.toString()

            edtTglEvent.setOnClickListener{
                val cal = Calendar.getInstance()
                val date = DatePickerDialog(this,
                    { _, year, monthOfYear, dayOfMonth ->
                        val newDate = Calendar.getInstance()
                        newDate[year, monthOfYear] = dayOfMonth
                        val bulan = arrayOf("Januari", "Februari", "Maret", "April", "Mei",
                            "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")
                        AwalTanggal = dayOfMonth.toString() + " " + bulan[monthOfYear] + " " + year
                        edtTglEvent.setText(AwalTanggal)
                    },cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH))

                date.show()
            }

            edtTglAkhir.setOnClickListener{
                val cal = Calendar.getInstance()
                val date = DatePickerDialog(this,
                    { _, year, monthOfYear, dayOfMonth ->
                        val newDate = Calendar.getInstance()
                        newDate[year, monthOfYear] = dayOfMonth
                        val bulan = arrayOf("Januari", "Februari", "Maret", "April", "Mei",
                            "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")
                        AkhirTanggal = dayOfMonth.toString() + " " + bulan[monthOfYear] + " " + year
                        edtTglAkhir.setText(AkhirTanggal)
                    },cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH))

                date.show()
            }


            event?.name = name
//        event?.fee = Integer.valueOf(fee)
        event?.venue = venue
        event?.contact = cp
        event?.tgl_event = tgl_event
        event?.tgl_akhir = tgl_akhir

            val values = ContentValues()
            values.put(DatabaseHelper.EventColumns.KEY_NAME, name)
//        values.put(DatabaseHelper.EventColumns.KEY_FEE, fee)
            values.put(DatabaseHelper.EventColumns.KEY_TGL_EVENT, tgl_event)
            values.put(DatabaseHelper.EventColumns.KEY_TGL_AKHIR, tgl_akhir)
            values.put(DatabaseHelper.EventColumns.KEY_VENUE, venue)
            values.put(DatabaseHelper.EventColumns.KEY_CP, cp)

            val result = DatabaseHelper(this).insertEvent(values)
        }

    }

}