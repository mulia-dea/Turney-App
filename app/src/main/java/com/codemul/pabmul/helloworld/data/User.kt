package com.codemul.pabmul.helloworld.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val email: String,
    val password: String,
    val id: String,
    val name: String,
    val isAdmin: Int
) : Parcelable