package com.codemul.pabmul.helloworld

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import com.codemul.pabmul.helloworld.data.Event
import com.codemul.pabmul.helloworld.databinding.ActivityDetailEventBinding
import com.squareup.picasso.Picasso

class DetailEventActivity : AppCompatActivity() {
    private val dataIntent by lazy{
        intent.getParcelableExtra<Event>(INTENT_DETAIL)
    }
    private lateinit var binding: ActivityDetailEventBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = dataIntent?.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.dtlNameEvent.text = dataIntent?.name
        binding.tglTournament.text =  dataIntent?.tgl_event + " - " + dataIntent?.tgl_akhir
        binding.tglDaftar.text = dataIntent?.tgl_daftar + " - " + dataIntent?.tgl_akhir_daftar

        val fee = dataIntent?.fee

        if(fee == 0){
            binding.dtlFee.text = "Gratis"
        } else {
            binding.dtlFee.text = fee.toString()
        }

        binding.dtlVenue.text = dataIntent?.venue
        binding.dtlContact.text = dataIntent?.contact

        var contact2 = dataIntent?.contact2
        if(contact2  == null){
            binding.dtlContact2.visibility = View.GONE
            binding.contact2.visibility = View.GONE
        } else {
            binding.dtlContact2.text = dataIntent?.contact2
        }
        Picasso.get().load(dataIntent?.image).into(binding.dtlEventImg)

        daftar(dataIntent?.id, dataIntent?.fee)
    }

    private fun daftar(event: String?, idfee: Int?){
        binding.btnDaftar.setOnClickListener {
            var intent = Intent(this, DaftarEventActivity::class.java)
                intent.putExtra(id_event, event)
                intent.putExtra(id_fee, idfee)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        var INTENT_DETAIL = "intent_detail"
        var id_event = "id_Event"
        var id_fee = "id_fee"
    }
}