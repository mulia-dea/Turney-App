package com.codemul.pabmul.helloworld.db

import com.codemul.pabmul.helloworld.db.DatabaseContract.EventCclumns.Companion.TABLE_NAME

internal class Database {
    companion object{
        private const val DATABASE_NAME = "dbTurneyApp"

        private const val DATABASE_VERSION = 1

        private const val SQL_CREATE_TABLE_EVENT = "CREATE TABLE $TABLE_NAME"

//        private const val SQL_CREATE_TABLE_SCRIM = "CREATE TABLE $TABLE_NAME"

    }
}

//Masih belum fix, bingung