package com.araguas.pac_mp8


import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_3.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnIrActivity4

class Activity_3 : AppCompatActivity() {
    //trabajar sobre la camara
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)
        //boton volver Activity 1
        BtnAct3Volver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        //boton tomar foto. lo primero es averiguar si tenemos los permisos necesarios para acceder a la camara
        // si no es así se deben solicitar.
        btnCamara.setOnClickListener {
            checkPermisos()
        }

    }

    private var foto: Uri? = null
    private val REQUEST_IMAGE_CAPTURE = 1

    private fun checkPermisos() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ){
            // solicicitar los permisos para camara y memoria no volatil
            pedirPermisos()
        }else{
            //si tenemos los permisos, abrir la camara
            abreCamara()
        }
    }

    private fun abreCamara() {
    // usamos ContentValues para el almacenaje de la foto devuelta por la camara
        val value= ContentValues()
        value.put(MediaStore.Images.Media.TITLE,"nueva imagen")
        foto=contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,value)
    //lanzamos por intent la app de la camara indicando donde va la imagen capturada
        val camaraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            camaraIntent.putExtra(MediaStore.EXTRA_OUTPUT, foto)
             startActivityForResult(camaraIntent, REQUEST_IMAGE_CAPTURE)
    }

    // metodo sobreescrito para que si la captura de la imagen es OK nos la muestre en la Activity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            imageView.setImageURI(foto)

        }
    }

    //--------- PEDIR LOS PERMISOS DE ACCESO A CAMARA Y ESCRITURA--------------------
    private fun pedirPermisos() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.CAMERA ) ||
                ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE )){
          // El usuario ha rechazado los permisos, por lo que debe otorgarlos si se quiere usar la camara.
          // enviamos un mensaje para indicar que hay que dar permisos explicitamente.
            Toast.makeText(this, "Para usar la camara hay que dar permisos en AppInfo", Toast.LENGTH_LONG).show()
        } else {
            //pedir permisos
           ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA,android.Manifest.permission.WRITE_EXTERNAL_STORAGE),777)
        }
    }
    // Metodo sobreescrito que se lanza automáticamente al conceder los permisos.
    // se comprueban y si está correcto lanzamos el intent de la camara.
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 777) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults.isNotEmpty() && grantResults[1] == PackageManager.PERMISSION_GRANTED )
                abreCamara()
        }
    }


}