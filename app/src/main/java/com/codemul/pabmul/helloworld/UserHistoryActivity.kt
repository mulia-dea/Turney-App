package com.codemul.pabmul.helloworld

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.codemul.pabmul.helloworld.data.DaftarEvent
import com.codemul.pabmul.helloworld.databinding.ActivityUserHistoryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserHistoryActivity : AppCompatActivity() {

    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val currentUser by lazy {
        firebaseAuth.currentUser
    }

    private lateinit var binding: ActivityUserHistoryBinding
    private lateinit var dataList: MutableList<DaftarEvent>
    private lateinit var adapter: UserHistoryEventActivityAdapter

    private var databaseRef: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.title = "Event History"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rvHistoryEventUser.setHasFixedSize(true)

        getDataFromDatabase()

    }

    private fun getDataFromDatabase() {
        dataList = ArrayList()

        adapter = UserHistoryEventActivityAdapter(this@UserHistoryActivity, dataList)
        binding.rvHistoryEventUser.adapter = adapter
        databaseRef =
            FirebaseDatabase.getInstance().getReference("DaftarEvent").child(currentUser!!.uid)
        databaseRef!!.addChildEventListener(object : ChildEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val data = snapshot.getValue(DaftarEvent::class.java)
                data!!.id = snapshot.key

                Log.d("data array", data.toString())
                dataList.add((data))

                adapter.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
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

        })


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}