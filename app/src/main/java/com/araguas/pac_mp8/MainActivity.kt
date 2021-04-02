package com.araguas.pac_mp8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    // configuramos las acciones de los botones
          btnIrActivity2.setOnClickListener {
           val intent = Intent(this, Activity_2::class.java)
           startActivity(intent)
          }
          btnIrActivity3.setOnClickListener {
            val intent = Intent(this, Activity_3::class.java)
            startActivity(intent)
          }
          btnIrActivity4.setOnClickListener {
            val intent = Intent(this, Activity_4::class.java)
            startActivity(intent)
          }

    }


}