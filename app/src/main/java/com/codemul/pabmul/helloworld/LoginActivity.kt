package com.codemul.pabmul.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.codemul.pabmul.helloworld.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private val firebaeAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val databaseRef by lazy {
        FirebaseDatabase.getInstance()
    }

    private val currentUser by lazy {
        firebaeAuth.currentUser
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        setViewListener()
        setUp()
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

    private fun setUp() {
        loginBinding.btnLogin.setOnClickListener {
            var email = loginBinding.etUsernameEmailLogin.text.toString()
            var password = loginBinding.etPasswordLogin.text.toString()
            var username = loginBinding.etUsernameEmailLogin.text.toString()

            Log.d("name", email)

            login(email, password, username)
        }
    }
        private fun login(email: String, password: String, username: String) {
            firebaeAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                val df = databaseRef.getReference("Users")
                df.child(currentUser!!.uid).get().addOnSuccessListener {
                    val admin = it.child("admin").value.toString()
                    if (admin == "2131296731") {
                        Log.d("ADMIN", it.child("admin").value.toString())
                        Toast.makeText(this, "ADMIN", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "NOT ADMIN", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                Toast.makeText(this, "SUCCES LOGIN", Toast.LENGTH_SHORT).show()
            }
                .addOnFailureListener {
                    Toast.makeText(this, "FAILED LOGIN", Toast.LENGTH_SHORT)
                        .show()
                }
        }
    }