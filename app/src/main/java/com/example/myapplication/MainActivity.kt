package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {




    val EDIT_TEXT_KEY = "EDIT_TEXT_KEY"

    override fun onSaveInstanceState(outState: Bundle) {
        val editText = findViewById<TextView>(R.id.tv)
        outState.putString(EDIT_TEXT_KEY, editText.text.toString())
        super.onSaveInstanceState(outState)
    }


    companion object {
        const val EXTRA_KEY = "EXTRA"
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button5)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)

            intent.putExtra(EXTRA_KEY, "1")
            startActivity(intent)
        }
        val button2 = findViewById<Button>(R.id.button6)
        button2.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)

            intent.putExtra(EXTRA_KEY, "2")
            startActivity(intent)
        }
        val button3 = findViewById<Button>(R.id.button7)
        button3.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)

            intent.putExtra(EXTRA_KEY, "3")
            startActivity(intent)
        }
        if (savedInstanceState != null) {
            val editText = findViewById<TextView>(R.id.tv)
            editText.text = (savedInstanceState.getString(EDIT_TEXT_KEY))
        }
        val minn = findViewById<Button>(R.id.but_min)
        val tev = findViewById<TextView>(R.id.tv)
        val pl = findViewById<Button>(R.id.but_pl)

        pl.setOnClickListener {
            tev.text =   (tev.text.toString().toInt() + 1).toString()
        }
        minn.setOnClickListener {
            tev.text =   (tev.text.toString().toInt() - 1).toString()
        }

        }

    }


