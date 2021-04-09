package com.araguas.pac_mp8



import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_2.*

class Activity_2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        btnTabla.setOnClickListener{
            crearTabla()

        }

        btnInsertar.setOnClickListener{
            val admin = AdminSQLiteOpenHelper(this,"administracion", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("dni", etDni.getText().toString())
            registro.put("nombre", etNombre.getText().toString())
            registro.put("correo", etCorreo.getText().toString())
            bd.insert("personas", null, registro)
            bd.close()
            etDni.setText("")
            etNombre.setText("")
            etCorreo.setText("")
            Toast.makeText(this, "Se cargaron los datos de la persona", Toast.LENGTH_SHORT).show()
        }

        btnConsultar.setOnClickListener{
            try {
                val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
                val bd = admin.writableDatabase
                val fila = bd.rawQuery("select nombre, correo from personas where dni=${etDni.text}", null)
                if (fila.moveToFirst()) {
                    etNombre.setText(fila.getString(0))
                    etCorreo.setText(fila.getString(1))
                } else
                    Toast.makeText(this, "No existe un artículo con dicho código", Toast.LENGTH_SHORT).show()
                bd.close()
            }catch ( exception: Exception){
                Toast.makeText(this, "Error en la lectura de datos de la BBDD", Toast.LENGTH_SHORT).show()
            }
        }

        btnVoverAct1.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "Entrando en Activity 2", Toast.LENGTH_LONG).show()
    }

    fun crearTabla(){
        //lanzamos el comando SQL que crea la tabla
        val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
        val bd = admin.writableDatabase
        val tabla = bd.rawQuery("CREATE TABLE personas(dni String primary key, nombre String, correo String)", null)
    }






}


