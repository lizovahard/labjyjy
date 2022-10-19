package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val intent = getIntent()
        val s = intent.getStringExtra(MainActivity.EXTRA_KEY)

        val textView = findViewById<TextView>(R.id.textView4)
        textView.text = s
    }
}