package com.livecoding.learnandro.ui.learndb
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import com.codemul.pabmul.helloworld.data.User
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.FirebaseDatabase
//import com.livecoding.learnandro.databinding.ActivityLearnRealtimeDatabaseBinding
//import java.util.*
//
//class LearnRealtimeDatabase : AppCompatActivity() {
//
//    private lateinit var binding: ActivityLearnRealtimeDatabaseBinding
//
//    private val firebaeAuth by lazy {
//        FirebaseAuth.getInstance()
//    }
//
//    private val databaseRef by lazy {
//        FirebaseDatabase.getInstance()
//    }
//
//    private val currentUser by lazy {
//        firebaeAuth.currentUser
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityLearnRealtimeDatabaseBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        initView()
//
//    }
//
//    private fun initView() {
//        setupView()
//    }
//
//    private fun setupView() {
//        binding.btnDaftar.setOnClickListener {
//            var email = binding.etEmail.text.toString()
//            var password = binding.etPassword.text.toString()
//            var name = binding.etNama.text.toString()
//            signUp(email, password, name)
//        }
//
//        binding.btnSignin.setOnClickListener {
//            var email = binding.etEmailSign.text.toString()
//            var password = binding.etPasswordSign.text.toString()
//            var nama = binding.etNamaSign.text.toString()
//
//            Log.d("name", nama)
//
//            login(email, password, nama)
//        }
//    }
//
//    private fun login(email: String, password: String, nama: String) {
//        firebaeAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
//            var df = databaseRef.getReference("Users")
//            df.child(currentUser!!.uid).get().addOnSuccessListener {
//                var admin = it.child("admin").value.toString()
//                if (admin == "1") {
//                    Log.d("ADMIN", it.child("admin").value.toString())
//                    Toast.makeText(this@LearnRealtimeDatabase, "ADMIN", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(this@LearnRealtimeDatabase, "NOT ADMIN", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
//            Toast.makeText(this@LearnRealtimeDatabase, "SUCCES LOGIN", Toast.LENGTH_SHORT).show()
//        }
//            .addOnFailureListener {
//                Toast.makeText(this@LearnRealtimeDatabase, "FAILED LOGIN", Toast.LENGTH_SHORT)
//                    .show()
//            }
//    }
//
//    private fun signUp(email: String, password: String, name: String) {
//        var df = databaseRef.getReference("Users")
//
//        firebaeAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
//
//            var data = User(email, password, currentUser!!.uid, name, 0)
//
//            df.child(currentUser!!.uid).setValue(data).addOnSuccessListener {
//                binding.etEmail.text.clear()
//                binding.etPassword.text.clear()
//                binding.etNama.text.clear()
//
//                Toast.makeText(
//                    this@LearnRealtimeDatabase,
//                    "SUCCESS SAVE DATA",
//                    Toast.LENGTH_SHORT
//                )
//                    .show()
//            }
//                .addOnFailureListener {
//                    Log.d("Faild", it.toString())
//                    Toast.makeText(
//                        this@LearnRealtimeDatabase,
//                        "FAILED SAVE DATA",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//            Toast.makeText(
//                this@LearnRealtimeDatabase,
//                "SUCCESS REGIST",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//            .addOnFailureListener {
//                Toast.makeText(
//                    this@LearnRealtimeDatabase,
//                    "FAILED REGIST",
//                    Toast.LENGTH_SHORT
//                )
//                    .show()
//                Log.d("Failed", it.toString())
//            }
//    }
//
//}