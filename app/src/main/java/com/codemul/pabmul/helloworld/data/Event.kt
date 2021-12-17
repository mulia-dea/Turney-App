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
    var tgl_daftar: String? = null,
    var tgl_akhir_daftar: String? = null,
    var fee: Int = 0,
    var contact: String? =null,
    var contact2: String? =null,
    var venue: String? =null,
) : Parcelable
