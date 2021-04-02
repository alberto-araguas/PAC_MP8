package com.araguas.pac_mp8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

          btnIrActivity2.setOnClickListener {
           val intent = Intent(this, Activity_2::class.java)
           startActivity(intent)
          }

    }


}