package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Todo(val id: Long, val name: String, val lastname: String, val birthday: String, val tel: String, val isDone: Boolean)




class MainActivity : AppCompatActivity() {

    private val dbHelper = DBHelper(this)
    private val list = mutableListOf<Todo>()
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        list.addAll(dbHelper.getAll())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listSTR = list.map { todo: Todo -> todo.id }
        adapter = RecyclerAdapter(list) {
            // адаптеру передали обработчик удаления элемента
            dbHelper.remove(it)
            list.removeAt(it)
            adapter.notifyItemRemoved(it)
        }



        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val buttonAdd = findViewById<Button>(R.id.but_pl)
        buttonAdd.setOnClickListener {
            val id = dbHelper.add(editText.text.toString(), "89132490223", "14.09.2003", "petrov" )
            list.add(Todo(id, editText.text.toString(), "petrov", "14.09.2003", "89132490223", true))
            adapter.notifyItemInserted(list.lastIndex)

        }


//        val button = findViewById<Button>(R.id.but_pl)
//        val textView = findViewById<RecyclerView>(R.id.recyclerView)
//
//
//        button.setOnClickListener {
//
//        }

        }

    }


