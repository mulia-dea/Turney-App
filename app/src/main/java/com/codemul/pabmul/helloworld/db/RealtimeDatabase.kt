package com.codemul.pabmul.helloworld.db

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

object RealtimeDatabase {
    fun instance() = Firebase.database
    fun instances() = Firebase.storage
}