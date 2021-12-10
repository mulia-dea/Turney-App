package com.codemul.pabmul.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewList = findViewById<RecyclerView>(R.id.rv_featured_events)

        val eventList = ArrayList<EsportEvent>()
        // type nentuin output gambar yg dipakai
        eventList.add(EsportEvent("valorant"))
        eventList.add(EsportEvent("porsematik"))

        val adapter = CustomAdapter(this, eventList)
        viewList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewList.adapter = adapter
        adapter.setOnItemClickListener(object: CustomAdapter.OnItemListClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity, "No. $position", Toast.LENGTH_LONG).show()
            }

        })

        onClickComponentListener()

    }

    private fun onClickComponentListener(){
        val scrimComponent: CardView = findViewById(R.id.cv_list_scrim)
        scrimComponent.setOnClickListener {
            val scrimIntent = Intent(this, ScrimActivity::class.java)
            startActivity(scrimIntent)
        }
    }
}