package com.codemul.pabmul.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setViewListener()
    }

    fun setViewListener() {
        val btnLogin: Button = findViewById(R.id.btn_login)
        val tvLupaSandi: TextView = findViewById(R.id.tv_lupa_sandi_login)
        val tvNoAkun: TextView = findViewById(R.id.tv_belum_punya_akun_login)

        btnLogin.setOnClickListener {
            val loginIntent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(loginIntent)
        }

        tvNoAkun.setOnClickListener {
            val noAkunIntent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(noAkunIntent)
        }
    }
}