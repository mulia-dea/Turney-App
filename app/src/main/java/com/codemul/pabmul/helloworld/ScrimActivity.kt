package com.codemul.pabmul.helloworld

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.codemul.pabmul.helloworld.data.Scrim
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import java.util.ArrayList

class ScrimActivity : AppCompatActivity() {

    private lateinit var adapter: CustomAdapter
    private var storage : FirebaseStorage? = null
    private var databaseRef: DatabaseReference? = null
    private var dbListerner: ValueEventListener? = null
    private lateinit var scrimList: MutableList<Scrim>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrim)

        supportActionBar?.title = "Scrim"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewList: RecyclerView = findViewById(R.id.rv_scrim_list)
        scrimList = ArrayList()

        val scrimAdapter = ScrimAdapter(scrimList)
        viewList.adapter = scrimAdapter
        scrimAdapter.setOnClickButton(object: ScrimAdapter.OnButtonJoinListener{
            override fun buttonClick(contentPosition: Int) {
                // Kosong boi
            }

        })

        getDataFromDataBase()

    }

    private fun getDataFromDataBase(){
        storage = FirebaseStorage.getInstance()
        databaseRef = FirebaseDatabase.getInstance().getReference("scrim")
        dbListerner = databaseRef?.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                scrimList.clear()

                for (eventSnap in snapshot.children){
                    val upload = eventSnap.getValue(Scrim::class.java)
                    upload!!.id = eventSnap.key
                    scrimList.add(upload)
                }
//                adapter.notifyDataSetChanged()
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
}