package com.ar.consultaya

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mostrarHome()
    }

    private fun mostrarHome() {
        setContentView(R.layout.activity_main)

        findViewById<android.view.View>(R.id.btnBuscarMedico).setOnClickListener {
            mostrarBuscarMedico()
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
            mostrarHome()
        }

        findViewById<android.view.View>(R.id.navBuscar).setOnClickListener {
            mostrarBuscarMedico()
        }

        findViewById<android.view.View>(R.id.navTurnos).setOnClickListener {
            Toast.makeText(this, "Turnos", Toast.LENGTH_SHORT).show()
        }

        findViewById<android.view.View>(R.id.navPerfil).setOnClickListener {
            Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show()
        }
    }

    private fun mostrarBuscarMedico() {
        setContentView(R.layout.activity_buscar_medico)

        findViewById<Button>(R.id.btnAgendar1).setOnClickListener {
            Toast.makeText(this, "Agendar con Dr. Pepe Pepito", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnAgendar2).setOnClickListener {
            Toast.makeText(this, "Agendar con Dra. Pepa Pepita", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnAgendar3).setOnClickListener {
            Toast.makeText(this, "Agendar con Dr. Juan Perez", Toast.LENGTH_SHORT).show()
        }

        findViewById<android.view.View>(R.id.navHome).setOnClickListener {
            mostrarHome()
        }

        findViewById<android.view.View>(R.id.navBuscar).setOnClickListener {
            mostrarBuscarMedico()
        }

        findViewById<android.view.View>(R.id.navTurnos).setOnClickListener {
            Toast.makeText(this, "Turnos", Toast.LENGTH_SHORT).show()
        }

        findViewById<android.view.View>(R.id.navPerfil).setOnClickListener {
            Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show()
        }
    }
}