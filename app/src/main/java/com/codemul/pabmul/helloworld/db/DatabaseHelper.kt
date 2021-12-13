package com.codemul.pabmul.helloworld.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.codemul.pabmul.helloworld.data.DataEvent

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VERSION) {
//    private lateinit var database: SQLiteDatabase
//    lateinit var db : SQLiteDatabase
    override fun onCreate(db: SQLiteDatabase) {

        // creating required tables
        db.execSQL(CREATE_TABLE_EVENT)
//        db.execSQL(DatabaseHelper.Companion.CREATE_TABLE_TAG)
//        db.execSQL(DatabaseHelper.Companion.CREATE_TABLE_TODO_TAG)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS ${EventColumns.TABLE_EVENT}")
//        db.execSQL("DROP TABLE IF EXISTS " + DatabaseHelper.Companion.TABLE_TAG)
//        db.execSQL("DROP TABLE IF EXISTS " + DatabaseHelper.Companion.TABLE_TODO_TAG)

        // create new tables
        onCreate(db)
    }

    fun open() {
         this.writableDatabase
    }
    fun insertEvent(values: ContentValues?): Long{
        val db = this.writableDatabase
        return db.insert(EventColumns.TABLE_EVENT, null, values)
    }

//    fun close() {
//        val db = this.writableDatabase
//        db.close()
//
//        if (db.isOpen)
//            db.close()
//    }

//    var listEvents = ArrayList<DataEvent>()
//        set(listEvents) {
//            if (listEvents.size >0){
//                this.listEvents.clear()
//            }
//            this.listEvents.addAll(listEvents)
//        }
//
//    fun add(event: DataEvent){
//        this.listEvents.add(event)
//    }
//
//    fun addEvent(name: String, tgl_event: String, tgl_akhir: String, fee: Int, venue: String, cp: String ){
//        val db: SQLiteDatabase = this.writableDatabase
//        val contentValues = ContentValues()
//        contentValues.put(KEY_NAME, name)
//        contentValues.put(KEY_TGL_EVENT, tgl_event)
//        contentValues.put(KEY_TGL_AKHIR, tgl_akhir)
//        contentValues.put(KEY_FEE, fee)
//        contentValues.put(KEY_VENUE, venue)
//        contentValues.put(KEY_CP, cp)
//
//        //insert row
//        db.insert(TABLE_EVENT, null, contentValues)
//
//        db.close()
//    }

//    fun queryById(id: String): Cursor {
//        return database.query(
//            DATABASE_NAME,
//            null,
//            "$id = ?",
//            arrayOf(id),
//            null,
//            null,
//            "$id ASC",
//            null)
//    }

    internal class EventColumns : BaseColumns{
        companion object {
            const val TABLE_EVENT = "events"
            const val KEY_ID = "id"
            const val KEY_IMAGE = "images"
            const val KEY_NAME = "name_event"
            const val KEY_TGL_EVENT = "tgl_event"
            const val KEY_TGL_AKHIR = "tgl_akhir"
            const val KEY_FEE = "fee"
            const val KEY_VENUE = "venue"
            const val KEY_CP = "contact_person"
        }
    }
    companion object {
        // Logcat tag
//        private const val LOG = "DatabaseHelper"

        // Database Version
        private const val DATABASE_VERSION = 1

        // Database Name
        private const val DATABASE_NAME = "dbTurneyApp"

        // Table Names
//        private const val TABLE_EVENT = "events"
        private const val TABLE_GAMES = "games"
//        private const val TABLE_TODO_TAG = "todo_tags"

        // Common column names
//        private const val KEY_ID = "id"
//        private const val KEY_CREATED_AT = "created_at"

        // EVENTS Table - column nmaes
//        private const val KEY_NAME = "name_event"
//        private const val KEY_TGL_EVENT = "tgl_event"
//        private const val KEY_TGL_AKHIR = "tgl_akhir"
//        private const val KEY_FEE = 0
//        private const val KEY_VENUE = "venue"
//        private const val KEY_CP = "contact_person"

        //UBAH SENDIRI
//        // TAGS Table - column names
//        private const val KEY_TAG_NAME = "tag_name"
//
//        // NOTE_TAGS Table - column names
//        private const val KEY_TODO_ID = "todo_id"
//        private const val KEY_TAG_ID = "tag_id"

        // Table Create Statements
        // Todo table create statement
        private val CREATE_TABLE_EVENT = "CREATE TABLE ${EventColumns.TABLE_EVENT}" +
                "(${EventColumns.KEY_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${EventColumns.KEY_NAME} TEXT NOT NULL,"+
                "${EventColumns.KEY_IMAGE} TEXT NULL"+
//                "${EventColumns.KEY_TGL_EVENT} TEXT NOT NULL," +
                "${EventColumns.KEY_TGL_AKHIR} TEXT NOT NULL," +
                "${EventColumns.KEY_VENUE} TEXT NOT NULL," +
                "${EventColumns.KEY_FEE} INTEGER NULL," +
                "${EventColumns.KEY_CP} TEXT NOT NULL)"

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