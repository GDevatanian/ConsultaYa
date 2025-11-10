package com.ar.consultaya

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<android.view.View>(R.id.btnBuscarMedico).setOnClickListener {
            Toast.makeText(this, "Buscar médico", Toast.LENGTH_SHORT).show()
        }

        findViewById<android.view.View>(R.id.btnMisTurnos).setOnClickListener {
            Toast.makeText(this, "Mis turnos", Toast.LENGTH_SHORT).show()
        }

        findViewById<android.view.View>(R.id.btnMisChats).setOnClickListener {
            Toast.makeText(this, "Mis chats", Toast.LENGTH_SHORT).show()
        }

        findViewById<android.view.View>(R.id.btnHistorial).setOnClickListener {
            Toast.makeText(this, "Historial", Toast.LENGTH_SHORT).show()
        }

        findViewById<android.view.View>(R.id.btnPerfil).setOnClickListener {
            Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show()
        }

        findViewById<android.view.View>(R.id.btnConsultaGeneral).setOnClickListener {
            Toast.makeText(this, "Consulta General", Toast.LENGTH_SHORT).show()
        }

        findViewById<android.view.View>(R.id.btnEspecialidad).setOnClickListener {
            Toast.makeText(this, "Especialidad", Toast.LENGTH_SHORT).show()
        }

        findViewById<android.view.View>(R.id.navHome).setOnClickListener {
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
        }

        findViewById<android.view.View>(R.id.navBuscar).setOnClickListener {
            Toast.makeText(this, "Buscar", Toast.LENGTH_SHORT).show()
        }

        findViewById<android.view.View>(R.id.navTurnos).setOnClickListener {
            Toast.makeText(this, "Turnos", Toast.LENGTH_SHORT).show()
        }

        findViewById<android.view.View>(R.id.navPerfil).setOnClickListener {
            Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show()
        }
    }
}