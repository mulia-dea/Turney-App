package com.codemul.pabmul.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codemul.pabmul.helloworld.data.Quest
import com.codemul.pabmul.helloworld.data.User
import com.codemul.pabmul.helloworld.databinding.ActivityCreateEventBinding
import com.codemul.pabmul.helloworld.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val databaseRef by lazy {
        FirebaseDatabase.getInstance()
    }

    private val currentUser by lazy {
        firebaseAuth.currentUser
    }

    private val dataIntent by lazy {
        intent.getParcelableExtra<Quest>(INTENT_DETAIL)
    }

    private var user: User? = null
    private lateinit var binding: ActivityMainBinding

    //    private var firebaseAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "HOME"
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        firebaseAuth = FirebaseAuth.getInstance()

        val viewList = findViewById<RecyclerView>(R.id.rv_featured_events)

        val eventList = ArrayList<EsportEvent>()
        // type nentuin output gambar yg dipakai
        eventList.add(EsportEvent("valorant"))
        eventList.add(EsportEvent("porsematik"))
        eventList.add(EsportEvent("DOTA 2"))


        val adapter = CustomAdapter(this, eventList)
        viewList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewList.adapter = adapter
        adapter.setOnItemClickListener(object : CustomAdapter.OnItemListClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity, "No. $position", Toast.LENGTH_LONG).show()
            }

        })
        onClickComponentListener()

        profile()
        setQuest()
    }

    //masih bingung
    override fun onStart() {
//        val firebaseUser: FirebaseUser = firebaseAuth.currentUser!!
        if (currentUser!=null){
            //there is some user log in
        } else{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        super.onStart()
    }

    private fun profile() {
        val df = databaseRef.getReference("Users")
        df.child(currentUser!!.uid).get().addOnSuccessListener {
            val username = it.child("name").value.toString()
            val id = it.child("id").value.toString()
            binding.tvIdNama.text = username
            binding.tvIdAkun.text = "ID: ${id}"

        }
//        currentUser?.uid.let {
//            val username = user?.name
//            val email = user?.email
//            val id = user?.id
//
//            binding.tvIdNama.text = username.toString()
//            binding.tvIdAkun.text= id.toString()
//        }
    }

    private fun onClickComponentListener() {
        val scrimComponent: LinearLayout = findViewById(R.id.ll_list_scrim)
        val daftarScrimComponent: LinearLayout = findViewById(R.id.ll_daftar_scrim)
        val daftarEventComponent: LinearLayout = findViewById(R.id.ll_list_event)
        val profileComponent: CardView = findViewById(R.id.cv_profile_card)
        val questComponent: CardView = findViewById(R.id.cv_user_quest)

        binding.relativeProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        scrimComponent.setOnClickListener {
            val scrimIntent = Intent(this, ScrimActivity::class.java)
            startActivity(scrimIntent)
        }

        daftarScrimComponent.setOnClickListener {
            val daftarScrimIntent = Intent(this, DaftarScrimActivity::class.java)
            startActivity(daftarScrimIntent)
        }

        daftarEventComponent.setOnClickListener {
            val eventIntent = Intent(this, EventActivity::class.java)
            startActivity(eventIntent)
        }

        profileComponent.setOnClickListener {
            val intentProfile = Intent(this, ProfileActivity::class.java)
            startActivity(intentProfile)
        }

        questComponent.setOnClickListener {
            val intentQuest = Intent(this, QuestActivity::class.java)
            startActivity(intentQuest)
        }
    }

    private fun setQuest() {
//        Log.d("intent data", dataIntent.toString())

        if (dataIntent == null){
            binding.tvQuestName.setText("#Quest Name")
            binding.tvQuestDesc.setText("#Quest Description")
        }
        else{
            binding.tvQuestName.setText(dataIntent?.namaQuest)
            binding.tvQuestDesc.setText(dataIntent?.questDesc)
        }
    }

    companion object {
        var INTENT_DETAIL = "intent_detail"
//        var id_event = "id_Event"
    }
}