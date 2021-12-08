package com.codemul.pabmul.helloworld.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.codemul.pabmul.helloworld.data.DataEvent

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {

        // creating required tables
        db.execSQL(CREATE_TABLE_EVENT)
//        db.execSQL(DatabaseHelper.Companion.CREATE_TABLE_TAG)
//        db.execSQL(DatabaseHelper.Companion.CREATE_TABLE_TODO_TAG)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS $TABLE_EVENT")
//        db.execSQL("DROP TABLE IF EXISTS " + DatabaseHelper.Companion.TABLE_TAG)
//        db.execSQL("DROP TABLE IF EXISTS " + DatabaseHelper.Companion.TABLE_TODO_TAG)

        // create new tables
        onCreate(db)
    }

    fun addEvent(event: DataEvent){
        val db: SQLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, event.name)
        contentValues.put(KEY_TGL_EVENT, event.tgl_event)
        contentValues.put(KEY_TGL_AKHIR, event.tgl_akhir)
        contentValues.put(KEY_FEE, event.fee)
        contentValues.put(KEY_VENUE, event.venue)
        contentValues.put(KEY_CP, event.contact)

        //insert row
        db.insert(TABLE_EVENT, null, contentValues)

        db.close()
    }

    companion object {
        // Logcat tag
//        private const val LOG = "DatabaseHelper"

        // Database Version
        private const val DATABASE_VERSION = 1

        // Database Name
        private const val DATABASE_NAME = "dbTurneyApp"

        // Table Names
        private const val TABLE_EVENT = "events"
        private const val TABLE_GAMES = "games"
//        private const val TABLE_TODO_TAG = "todo_tags"

        // Common column names
        private const val KEY_ID = "id"
//        private const val KEY_CREATED_AT = "created_at"

        // EVENTS Table - column nmaes
        private const val KEY_NAME = "name_event"
        private const val KEY_TGL_EVENT = "tgl_event"
        private const val KEY_TGL_AKHIR = "tgl_akhir"
        private const val KEY_FEE = "fee"
        private const val KEY_VENUE = "fee"
        private const val KEY_CP = "contact_person"

        //UBAH SENDIRI
//        // TAGS Table - column names
//        private const val KEY_TAG_NAME = "tag_name"
//
//        // NOTE_TAGS Table - column names
//        private const val KEY_TODO_ID = "todo_id"
//        private const val KEY_TAG_ID = "tag_id"

        // Table Create Statements
        // Todo table create statement
        private val CREATE_TABLE_EVENT = "CREATE TABLE $TABLE_EVENT" +
                "($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$KEY_NAME VARCHAR(50) NOT NULL," +
                "$KEY_TGL_EVENT TEXT NOT NULL," +
                "$KEY_TGL_AKHIR TEXT NOT NULL," +
                "$KEY_VENUE TEXT NOT NULL," +
                "$KEY_FEE INTEGER," +
                "$KEY_CP TEXT NOT NULL)"

//BUAT TABEL BARU
//        // Tag table create statement
//        private val CREATE_TABLE_TAG = ("CREATE TABLE " + DatabaseHelper.Companion.TABLE_TAG
//                + "(" + DatabaseHelper.Companion.KEY_ID + " INTEGER PRIMARY KEY," + DatabaseHelper.Companion.KEY_TAG_NAME + " TEXT,"
//                + DatabaseHelper.Companion.KEY_CREATED_AT + " DATETIME" + ")")
//
//        // todo_tag table create statement
//        private val CREATE_TABLE_TODO_TAG = ("CREATE TABLE "
//                + DatabaseHelper.Companion.TABLE_TODO_TAG + "(" + DatabaseHelper.Companion.KEY_ID + " INTEGER PRIMARY KEY,"
//                + DatabaseHelper.Companion.KEY_TODO_ID + " INTEGER," + DatabaseHelper.Companion.KEY_TAG_ID + " INTEGER,"
//                + DatabaseHelper.Companion.KEY_CREATED_AT + " DATETIME" + ")")
    }
}