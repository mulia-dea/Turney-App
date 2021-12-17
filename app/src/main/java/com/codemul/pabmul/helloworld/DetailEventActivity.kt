package com.codemul.pabmul.helloworld

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dtlNameEvent.text = dataIntent?.name
        binding.tglTournament.text =  dataIntent?.tgl_event + " - " + dataIntent?.tgl_akhir
        binding.tglDaftar.text = dataIntent?.tgl_daftar + " - " + dataIntent?.tgl_akhir_daftar
        binding.dtlFee.text = dataIntent?.fee.toString()
        binding.dtlVenue.text = dataIntent?.venue
//        binding.dtlEventImg = dataIntent?.image
        binding.dtlContact.text = dataIntent?.contact
        binding.dtlContact2.text = dataIntent?.contact2
        Picasso.get().load(dataIntent?.image).into(binding.dtlEventImg)
    }

    companion object {
        var INTENT_DETAIL = "intent_detail"
    }
}