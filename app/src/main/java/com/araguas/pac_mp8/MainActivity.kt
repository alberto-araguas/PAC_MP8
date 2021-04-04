package com.araguas.pac_mp8

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        mPlayer=MediaPlayer.create(this,R.raw.golden)

    }

   //Para la realización del reproductor se ha utlizado el mediaPlayer en vez del soundPool, ya
   // que ANDROID STUDIO indica que soundPool está en desuso y creo que es mejor utilizar funcionalidades
   // que no estén descontinuadas.

    var mPlayer: MediaPlayer? = null

    private fun playSound() {
        //en un bloque try-cacth por seguridad, realizamos la reproducción  con el método .start
        try {
            mPlayer?.start()
            Toast.makeText(this, "Canción sin derechos de autor", Toast.LENGTH_LONG).show()
        }catch(e: Exception ){
            Log.e("musica", "error de reproduccion")
        }
    }
    private fun stopSound() {
        // pausamos la cancion y la ponermos al principio
        mPlayer?.seekTo(0)
        mPlayer?.pause()
   }




}