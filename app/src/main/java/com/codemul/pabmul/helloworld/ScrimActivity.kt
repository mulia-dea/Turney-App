package com.codemul.pabmul.helloworld

import android.annotation.SuppressLint
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.codemul.pabmul.helloworld.data.Scrim
import com.codemul.pabmul.helloworld.databinding.ActivityScrimBinding
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import java.util.ArrayList

class ScrimActivity : AppCompatActivity() {

    private lateinit var adapter: ScrimAdapter
    private var storage : FirebaseStorage? = null
    private var databaseRef: DatabaseReference? = null
    private var dbListerner: ValueEventListener? = null
    private lateinit var scrimList: MutableList<Scrim>
    private lateinit var binding: ActivityScrimBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrimBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Scrim"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        scrimList = ArrayList()

        adapter = ScrimAdapter(scrimList)
        binding.rvScrimList.adapter = adapter
        adapter.setOnClickButton(object: ScrimAdapter.OnButtonJoinListener{

            override fun onButtonJoinClick(content: Scrim) {
                content.isJoin = 1
                content.jumlah_pemain_sekarang = content.jumlah_pemain_sekarang + 1
                sentDataToFirebase(content)
                getDataFromDataBase()
            }

            override fun onButtonUnjoinClick(content: Scrim) {
                content.isJoin = 0
                content.jumlah_pemain_sekarang = content.jumlah_pemain_sekarang - 1
                sentDataToFirebase(content)
                getDataFromDataBase()
            }

        })

        getDataFromDataBase()
//        Log.d("scrimList val ", scrimList.toString()) // debug

    }

    private fun getDataFromDataBase(){
        storage = FirebaseStorage.getInstance()
        databaseRef = FirebaseDatabase.getInstance().getReference("scrim")
//        Log.d("data ref", databaseRef.toString())
        dbListerner = databaseRef?.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                scrimList.clear()

                for (eventSnap in snapshot.children){
                    val upload = eventSnap.getValue(Scrim::class.java)
                    upload!!.id = eventSnap.key
                    scrimList.add(upload)
                    Log.d("scrimList val ", scrimList.toString()) // debug
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

    fun sentDataToFirebase(content:Scrim){
        val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("scrim")
        databaseRef.child(content.id!!).setValue(content)
    }
}