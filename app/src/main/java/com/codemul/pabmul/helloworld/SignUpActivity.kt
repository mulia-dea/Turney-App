package com.codemul.pabmul.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.codemul.pabmul.helloworld.databinding.ActivityMainBinding
import com.codemul.pabmul.helloworld.databinding.ActivitySignUpBinding
import com.google.android.material.textfield.TextInputEditText

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewListener()
    }


    private fun setViewListener() {
        val tvPunyaAkun: TextView = findViewById(R.id.tv_punya_akun_signup)


        tvPunyaAkun.setOnClickListener {
            val punyaAkunIntent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(punyaAkunIntent)
        }

        submitActionCreateAccount()
    }

    private fun submitActionCreateAccount() {
        binding.btnSignup.setOnClickListener {

            if (binding.etPasswordSignup.text.toString() == binding.etPasswordConfirmSignup.text.toString())
            {
                    // bagian masukkan data ke firebase di sini

                    val signUpIntent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    startActivity(signUpIntent)
            }

            else{
                Toast.makeText(this@SignUpActivity, "Password do not match", Toast.LENGTH_SHORT).show()
            }
        }


    }
}