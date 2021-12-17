package com.codemul.pabmul.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.codemul.pabmul.helloworld.data.User
import com.codemul.pabmul.helloworld.databinding.ActivityMainBinding
import com.codemul.pabmul.helloworld.databinding.ActivitySignUpBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private val firebaeAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val databaseRef by lazy {
        FirebaseDatabase.getInstance()
    }

    private val currentUser by lazy {
        firebaeAuth.currentUser
    }

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setViewListener()
        submitActionCreateAccount()
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
            var email = binding.etEmailSignup.text.toString()
            var password = binding.etPasswordSignup.text.toString()
            var name = binding.etUsernameSignup.text.toString()
            signUp(email, password, name)
//            if (binding.etPasswordSignup.text.toString() == binding.etPasswordConfirmSignup.text.toString()) {
//                // bagian masukkan data ke firebase di sini
//
//                val signUpIntent = Intent(this@SignUpActivity, LoginActivity::class.java)
//                startActivity(signUpIntent)
//            } else {
//                Toast.makeText(this@SignUpActivity, "Password do not match", Toast.LENGTH_SHORT)
//                    .show()
//            }
        }
    }

    private fun signUp(email: String, password: String, name: String) {
        var selectId: Int = binding.radioGrup.checkedRadioButtonId
        var radioUmum : Int = binding.radioButtonUmum.id

        var df = databaseRef.getReference("Users")

        firebaeAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {

            var data = User(email, password, currentUser!!.uid, name, selectId)

            df.child(currentUser!!.uid).setValue(data).addOnSuccessListener {
                binding.etEmailSignup.text?.clear()
                binding.etPasswordSignup.text?.clear()
                binding.etUsernameSignup.text?.clear()

                Toast.makeText(
                    this,
                    "SUCCESS SAVE DATA",
                    Toast.LENGTH_SHORT
                ).show()

                val signUpIntent = Intent(this@SignUpActivity, LoginActivity::class.java)
                startActivity(signUpIntent)

            }
                .addOnFailureListener {
                    Log.d("Faild", it.toString())
                    Toast.makeText(
                        this,
                        "FAILED SAVE DATA",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            Toast.makeText(
                this,
                "SUCCESS REGIST",
                Toast.LENGTH_SHORT
            ).show()
        }
            .addOnFailureListener {
                Toast.makeText(
                    this,
                    "FAILED REGIST",
                    Toast.LENGTH_SHORT
                )
                    .show()
                Log.d("Failed", it.toString())
            }
    }
}