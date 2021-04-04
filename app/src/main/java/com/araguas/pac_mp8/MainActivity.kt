package com.araguas.pac_mp8

import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //inicializamos soundPool cangando el archivo de música
        soundPool =  SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool!!.load(baseContext, R.raw.golden, 1)

       // configuramos las acciones de los botones de acceso a otra Activity
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
        // configuramos las acciones de los botones del reproductor de música
          btnPlay.setOnClickListener {
            playSound()
          }
          btnStop.setOnClickListener {
            stopSound()
          }
    }



    //variables que usa sounPool
    private var soundPool: SoundPool? = null
    private val soundId = 1

    private fun playSound() {
        soundPool?.play(soundId, 1F, 1F, 1, 1, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
    }
    private fun stopSound() {
        soundPool?.stop(soundId)
    }

}