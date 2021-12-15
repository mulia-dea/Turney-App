package com.codemul.pabmul.helloworld.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Scrim(
    var id: Int = 0,
    var nama_penyelenggara: String? = null,
    var tgl_pelaksanaan_scrim: String? = null,
    var jenis_game: String? = null,
    var jumlah_pemain: Int = 0,
    var informasi_scrim: String? = null,
): Parcelable
