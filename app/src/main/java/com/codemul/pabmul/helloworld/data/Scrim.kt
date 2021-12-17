package com.codemul.pabmul.helloworld.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Scrim(
    var id: String? = null,
    var nama_penyelenggara: String? = null,
    var tgl_akhir: String? = null,
    var jenis_game: String? = null,
    var jumlah_pemain: Int = 0,
    var jumlah_pemain_sekarang: Int = 0,
    var information: String? = null,
): Parcelable
