package com.codemul.pabmul.helloworld.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quest(
    var id: String? = null,
    var namaQuest: String? = null,
    var questDesc: String? = null,
    var progressCount: Int = 0,
    var porgressTarget: Int = 0,
    var reward: String? = null
): Parcelable
