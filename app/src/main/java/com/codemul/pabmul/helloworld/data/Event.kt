package com.codemul.pabmul.helloworld.data

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    var id: String? = null,
    var image: String? = null,
    var name: String? =null,
    var tgl_event: String? =null,
    var tgl_akhir: String? =null,
    var fee: Int = 0,
    var contact: String? =null,
    var venue: String? =null,
) : Parcelable
