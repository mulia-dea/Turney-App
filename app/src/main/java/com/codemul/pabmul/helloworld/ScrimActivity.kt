package com.codemul.pabmul.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ScrimActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrim)

        val viewList: RecyclerView = findViewById(R.id.rv_scrim_list)
        val scrimList =  ArrayList<ScrimListContent>()

        scrimList.add(ScrimListContent("DOTA 2", 4, this@ScrimActivity))
        scrimList.add(ScrimListContent("Valorant", 4, this@ScrimActivity))

        val scrimAdapter = ScrimAdapter(scrimList)
        viewList.adapter = scrimAdapter
        scrimAdapter.setOnClickButton(object: ScrimAdapter.OnButtonJoinListener{
            override fun buttonClick(contentPosition: Int) {
                // Kosong boi
            }

        })
    }
}