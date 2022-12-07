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

//data class Todo(val id: Long, val name: String, val lastname: String, val birthday: String, val tel: String, val isDone: Boolean)




class MainActivity2 : AppCompatActivity() {

    private val dbHelper = DBHelper(this)
    private val list = mutableListOf<Todo>()
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)





        val Name = findViewById<EditText>(R.id.TextName)
        val LastName = findViewById<EditText>(R.id.TextLastname)
        val Number = findViewById<EditText>(R.id.TextNumber)
        val Date = findViewById<EditText>(R.id.editTextDate)





        val buttonAdd = findViewById<Button>(R.id.but_plus)
        buttonAdd.setOnClickListener {

            val id = dbHelper.add(Name.text.toString(), Number.text.toString(), Date.text.toString(), LastName.text.toString() )
            Name.text.clear();
            LastName.text.clear();
            Number.text.clear();
            Date.text.clear();

        }
        val buttonRet = findViewById<Button>(R.id.but_ret)
        buttonRet.setOnClickListener {


            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

}

//        val button = findViewById<Button>(R.id.but_pl)
//        val textView = findViewById<RecyclerView>(R.id.recyclerView)
//
//
//        button.setOnClickListener {
//
//        }



}