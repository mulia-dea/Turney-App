package com.codemul.pabmul.helloworld

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codemul.pabmul.helloworld.data.Event
import com.codemul.pabmul.helloworld.data.Quest
import com.codemul.pabmul.helloworld.databinding.ActivityQuestBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlin.math.log

class QuestActivity : AppCompatActivity() {

    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val currentUser by lazy {
        firebaseAuth.currentUser
    }

    private val dataBase by lazy {
        FirebaseDatabase.getInstance()
    }

    private var dataBaseRef: DatabaseReference? = null
    private var dbListener: ValueEventListener? = null
    private lateinit var questList: MutableList<Quest>
    private lateinit var binding: ActivityQuestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFromDataBase()
    }

    private fun getDataFromDataBase() {
//        binding.rvQuestSelection.setHasFixedSize(true)
//        binding.rvQuestSelection.layoutManager = LinearLayoutManager(this@QuestActivity, LinearLayoutManager.VERTICAL, false)

        questList = ArrayList()
        val questAdapter = QuestAdapter(this, questList)
        binding.rvQuestSelection.adapter = questAdapter

        dataBaseRef = FirebaseDatabase.getInstance().getReference("quest")
        Log.d("ref ", dataBaseRef.toString()) // debug
        dbListener = dataBaseRef?.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                questList.clear()
                for (questSnap in snapshot.children) {
                    val upload = questSnap.getValue(Quest::class.java)
                    Log.d("upload val ", upload.toString()) // debug
                    upload!!.id = questSnap.key
                    questList.add(upload)
                    Log.d("questList val ", questList.toString()) // debug
                }

                questAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@QuestActivity, error.message, Toast.LENGTH_SHORT).show()
            }

        })

        questAdapter.setOnItemClickCallback(object : QuestAdapter.OnItemClickCallback {
            override fun onItemClicked(quest: Quest) {
//                dataBaseRef =
//                    FirebaseDatabase.getInstance().getReference("Users").child(currentUser!!.uid)
//                        .child("activeQuest").child(quest.id!!).setValue(quest)

                dataBase.getReference("Users").child(currentUser!!.uid).child("activeQuest")
                    .child(quest.id!!).setValue(quest).addOnSuccessListener {
                        Toast.makeText(this@QuestActivity, "dimasukkan sebagai main quest", Toast.LENGTH_SHORT).show()
                    }
                showDetailQuest(quest)
            }

        })
    }

    private fun showDetailQuest(quest: Quest) {
//        var intent = Intent(this, DetailEventActivity::class.java)
        startActivity(
            Intent(
                this,
                MainActivity::class.java
            ).putExtra(DetailEventActivity.INTENT_DETAIL, quest)
        )
    }

}