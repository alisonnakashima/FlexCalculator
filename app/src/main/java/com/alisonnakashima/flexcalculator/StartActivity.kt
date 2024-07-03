package com.alisonnakashima.flexcalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class StartActivity : AppCompatActivity() {

    private lateinit var btStart : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        btStart = findViewById( R.id.btStart )

        btStart.setOnClickListener{
            btStartOnClick();
        }
    }

    private fun btStartOnClick () {
        val main = Intent(this, MainActivity::class.java);
        startActivity(main);
    }

//    fun btStartOnClick(view: View?) {
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//    }

}