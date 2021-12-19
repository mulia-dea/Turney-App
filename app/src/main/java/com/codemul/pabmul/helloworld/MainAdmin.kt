package com.codemul.pabmul.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codemul.pabmul.helloworld.databinding.ActivityMainAdminBinding

class MainAdmin : AppCompatActivity() {
    private lateinit var binding: ActivityMainAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showBuatEvent()
    }

    private fun showBuatEvent(){
        binding.ivBuatEventAdmin.setOnClickListener {
            startActivity(Intent(this, CreateEventActivity::class.java))
        }
    }


}