package com.codemul.pabmul.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SplashScreenActivity : AppCompatActivity() {
    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val databaseRef by lazy {
        FirebaseDatabase.getInstance()
    }

    private val currentUser by lazy {
        firebaseAuth.currentUser
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        setContentView(R.layout.activity_splash_screen)
    }

    override fun onStart() {
        if (currentUser!=null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            //there is some user log in
        } else{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        super.onStart()
    }
}