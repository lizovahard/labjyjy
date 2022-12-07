package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class Todo(
    val id: Long,
    val name: String,
    val lastname: String,
    val birthday: String,
    val tel: String,
    val isDone: Boolean
)


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
            dbHelper.remove(list[it].id.toInt())
            list.removeAt(it)
            adapter.notifyItemRemoved(it)
        }


        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        editText.doOnTextChanged { text, start, before, count ->
            lifecycleScope.launch(Dispatchers.IO)
            {
                val glist = list.filter {
                    (it.name.indexOf(text.toString()) != -1) || (it.lastname?.indexOf(text.toString()) != -1)
                }
                list?.clear()
                if (glist.isEmpty()) {
                } else {
                    list.addAll(glist)
                    withContext(Dispatchers.Main) {
                        adapter.notifyDataSetChanged()
                    }
                }

            }
        }
        val buttonAdd = findViewById<Button>(R.id.but_add)
        buttonAdd.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)

            startActivity(intent)
            //           val id = dbHelper.add(editText.text.toString(), "89132490223", "14.09.2003", "petrov" )
            //          list.removeAll(dbHelper.getAll())
//            list.addAll(dbHelper.getAll())
//           adapter.notifyItemInserted(list.lastIndex)

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


