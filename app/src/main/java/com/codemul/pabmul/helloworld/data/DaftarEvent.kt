package com.codemul.pabmul.helloworld.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DaftarEvent(
    var id: String? =null,
    var idEvent: String? =null,
    var namaTim: String? = null,
    var anggota : String? =null,
    var noPerwakilan: String? =null
): Parcelable
