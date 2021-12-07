package com.codemul.pabmul.helloworld

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DaftarScrim(
    var id: Int = 0,
    var name: String? = null,
    var description: String? = null,
    var tgl_pelaksanaan: String?=null
) : Parcelable
