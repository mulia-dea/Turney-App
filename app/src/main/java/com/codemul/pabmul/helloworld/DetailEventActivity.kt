package com.codemul.pabmul.helloworld

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
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dtlNameEvent.text = dataIntent?.name
        binding.dtlTglAkhir.text = dataIntent?.tgl_akhir
        binding.dtlContact.text = dataIntent?.contact
        binding.dtlTglEvent.text = dataIntent?.tgl_event
        binding.dtlVenue.text = dataIntent?.venue
        binding.dtlFee.text = dataIntent?.fee.toString()
//        binding.dtlEventImg = dataIntent?.image
        Picasso.get().load(dataIntent?.image).into(binding.dtlEventImg)
    }

    companion object {
        var INTENT_DETAIL = "intent_detail"
    }
}