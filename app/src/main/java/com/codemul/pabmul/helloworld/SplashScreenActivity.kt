package com.codemul.pabmul.helloworld

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
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

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        setContentView(R.layout.activity_splash_screen)

        activityScope.launch {
            delay(2000)
            if (currentUser != null) {
                val df = databaseRef.getReference("Users")
                df.child(currentUser!!.uid).get().addOnSuccessListener {
                    val admin = it.child("admin").value.toString()
                    //there is some user log in
                    if (admin == "2131296730") {
//                        Log.d("ADMIN", it.child("admin").value.toString())
                        startActivity(Intent(this@SplashScreenActivity, MainAdmin::class.java))
                        finish()
                    } else {
                        startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                        finish()
                    }
                }
            } else {
                startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
                finish()
            }
//            val intent = Intent(this@SplashScreenActivity)
//            startActivity(intent)
        }

//        val df = databaseRef.getReference("Users").child("admin")
//        df.child(currentUser!!.uid).get().addOnSuccessListener {
//            val admin = it.child("admin").value.toString()
//            if (currentUser != null) {
//                //there is some user log in
//                if (admin == "2131296730") {
//                    Log.d("ADMIN", it.child("admin").value.toString())
//                    startActivity(Intent(this, MainAdmin::class.java))
//                } else {
//                    startActivity(Intent(this, MainActivity::class.java))
//                }
//            } else {
//                startActivity(Intent(this, LoginActivity::class.java))
//                finish()
//            }
//        }

//    override fun onStart() {
//        if (currentUser!=null){
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//            //there is some user log in
//        } else{
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
//        }
//        super.onStart()
//    }
    }
}