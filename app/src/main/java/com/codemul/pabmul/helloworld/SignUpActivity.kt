package com.codemul.pabmul.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setViewListener()
    }

    fun setViewListener(){
        val signUpBtn: Button = findViewById(R.id.btn_signup)
        val tvPunyaAkun: TextView = findViewById(R.id.tv_punya_akun_signup)

        signUpBtn.setOnClickListener {
            val signUpIntent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(signUpIntent)
        }
            tvPunyaAkun.setOnClickListener {
            val punyaAkunIntent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(punyaAkunIntent)
        }
    }
}