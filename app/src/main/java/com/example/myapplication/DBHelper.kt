package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "tododb"
        const val TABLE_NAME = "todos"

        const val KEY_ID = "id"
        const val KEY_NAME= "name"
        const val KEY_LAST_NAME = "lastname"
        const val KEY_TEL = "tel"
        const val KEY_BIRTHDAY = "birthday"
        const val KEY_IS_DONE = "is_done"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE $TABLE_NAME (
                $KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $KEY_NAME TEXT NOT NULL,
                $KEY_LAST_NAME TEXT NOT NULL,
                $KEY_TEL TEXT NOT NULL,
                $KEY_BIRTHDAY TEXT NOT NULL,
                $KEY_IS_DONE INTEGER NOT NULL
            )""")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun getAll(): List<Todo> {
        val result = mutableListOf<Todo>()
        val database = this.writableDatabase
        val cursor: Cursor = database.query(
            TABLE_NAME, null, null, null,
            null, null, null
        )
        if (cursor.moveToFirst()) {
            val idIndex: Int = cursor.getColumnIndex(KEY_ID)
            val nameIndex: Int = cursor.getColumnIndex(KEY_NAME)
            val lastnameIndex: Int = cursor.getColumnIndex(KEY_LAST_NAME)
            val telIndex: Int = cursor.getColumnIndex(KEY_TEL)
            val birthdayIndex: Int = cursor.getColumnIndex(KEY_BIRTHDAY)
            val isDoneIndex: Int = cursor.getColumnIndex(KEY_IS_DONE)
            do {
                val todo = Todo(
                    cursor.getLong(idIndex),
                    cursor.getString(nameIndex),
                    cursor.getString(lastnameIndex),
                    cursor.getString(telIndex),
                    cursor.getString(birthdayIndex),
                    cursor.getInt(isDoneIndex) == 1
                )
                result.add(todo)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return result
    }

    fun add(name: String, tel:String, birthday:String, lastname:String,  isDone: Boolean = false):Long {
        val database = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, name)
        contentValues.put(KEY_LAST_NAME, lastname)
        contentValues.put(KEY_TEL, tel)
        contentValues.put(KEY_BIRTHDAY, birthday)
        contentValues.put(KEY_IS_DONE, if (isDone) 1 else 0)
        val id = database.insert(TABLE_NAME, null, contentValues)
        close()
        return id;
    }

    fun update(id: Long, name: String, tel:String, birthday:String, lastname:String,  isDone: Boolean = false) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, name)
        contentValues.put(KEY_LAST_NAME, lastname)
        contentValues.put(KEY_TEL, tel)
        contentValues.put(KEY_BIRTHDAY, birthday)
        contentValues.put(KEY_IS_DONE, if (isDone) 1 else 0)
        database.update(TABLE_NAME, contentValues, "$KEY_ID = ?", arrayOf(id.toString()))
        close()
    }

    fun remove(id: Int) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME, "$KEY_ID = ?", arrayOf(id.toString()))
        close()
    }



    fun removeAll() {
        val database = this.writableDatabase
        database.delete(TABLE_NAME, null, null)
        close()
    }
}