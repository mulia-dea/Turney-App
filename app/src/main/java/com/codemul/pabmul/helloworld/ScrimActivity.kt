package com.codemul.pabmul.helloworld

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemul.pabmul.helloworld.data.Quest
import com.codemul.pabmul.helloworld.data.Scrim
import com.codemul.pabmul.helloworld.databinding.ActivityScrimBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import java.util.ArrayList

class ScrimActivity : AppCompatActivity() {

    private lateinit var adapter: ScrimAdapter
    private var storage: FirebaseStorage? = null
    private var databaseRef: DatabaseReference? = null
    private var dbListerner: ValueEventListener? = null
    private lateinit var scrimList: MutableList<Scrim>
    private var quests: MutableList<Quest> = ArrayList()
    private lateinit var binding: ActivityScrimBinding

    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val dataBase by lazy {
        FirebaseDatabase.getInstance()
    }

    private val currentUser by lazy {
        firebaseAuth.currentUser
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrimBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Scrim"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rvScrimList.setHasFixedSize(true)
        binding.rvScrimList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        scrimList = ArrayList()

        adapter = ScrimAdapter(scrimList)
        binding.rvScrimList.adapter = adapter
        adapter.setOnClickButton(object : ScrimAdapter.OnButtonJoinListener {
            override fun onButtonJoinClick(content: Scrim, position: Int) {
                sentDataToFirebase(content)
                getDataFromDataBase()

                // add data to user object
                dataBase.getReference("Users").child(currentUser!!.uid).child("scrimTerdaftar")
                    .child(content.id!!).setValue(content).addOnSuccessListener {
                        retrieveDataQuest(content.jenis_game.toString())
                        Toast.makeText(
                            this@ScrimActivity,
                            "Berhasil ikut scrim",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }

            override fun onButtonUnjoinClick(content: Scrim, position: Int) {
                sentDataToFirebase(content)
                getDataFromDataBase()
                // data dihapus dari database
                dataBase.getReference("Users").child(currentUser!!.uid).child("scrimTerdaftar")
                    .child(content.id!!).removeValue().addOnSuccessListener {
                        Toast.makeText(
                            this@ScrimActivity,
                            "Berhasil batal ikut scrim",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }

        })

        getDataFromDataBase()

    }

    private fun getDataFromDataBase() {
        storage = FirebaseStorage.getInstance()
        databaseRef = FirebaseDatabase.getInstance().getReference("scrim")
        dbListerner = databaseRef?.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                scrimList.clear()
                for (eventSnap in snapshot.children) {
                    val upload = eventSnap.getValue(Scrim::class.java)
                    upload!!.id = eventSnap.key
                    scrimList.add(upload)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ScrimActivity, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun sentDataToFirebase(content: Scrim) {
        val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("scrim")
        databaseRef.child(content.id!!).setValue(content)
    }

    private fun retrieveDataQuest(jenisGame: String) {
        val ref: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Users").child(currentUser!!.uid)
                .child("activeQuest")
        ref.addChildEventListener((object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val data = snapshot.getValue(Quest::class.java)
                data!!.id = snapshot.key

                Log.i("snap value", snapshot.getValue(Quest::class.java).toString())

                if (data.progressCount < data.progressTarget && data.namaQuest == "Hello Stranger!"){
                    data.progressCount += 1

                    dataBase.getReference("Users").child(currentUser!!.uid)
                        .child("activeQuest")
                        .child(data.id!!).setValue(data).addOnSuccessListener {
                            Log.i("Status", "berhasil ditambah")
                        }
                }

                if(data.progressCount < data.progressTarget && jenisGame == "PUBG"){
                    data.progressCount += 1

                    dataBase.getReference("Users").child(currentUser!!.uid)
                        .child("activeQuest")
                        .child(data.id!!).setValue(data).addOnSuccessListener {
                            Log.i("Status", "berhasil ditambah")
                        }
                }

            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val data = snapshot.getValue(Quest::class.java)
                data!!.id = snapshot.key
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        }))

    }
}