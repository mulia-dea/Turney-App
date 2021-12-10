package com.codemul.pabmul.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.codemul.pabmul.helloworld.db.DatabaseHelper

class DaftarEventActivity : AppCompatActivity() {

    private val btnAddEvent : Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_event)
        val db = DatabaseHelper(this)

        btnAddEvent?.setOnClickListener {

        }
    }
}