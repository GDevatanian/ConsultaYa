package com.ar.consultaya

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Flecha atrás
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        // Navegación
        findViewById<LinearLayout>(R.id.navHome).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        findViewById<LinearLayout>(R.id.navBuscar).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("PANTALLA_INICIAL", "BUSCAR")
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        findViewById<LinearLayout>(R.id.navTurnos).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("PANTALLA_INICIAL", "TURNOS")
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        // Logout
        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Cerrar sesión")
                .setMessage("¿Estás seguro de que quieres salir?")
                .setPositiveButton("Salir") { _, _ ->
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }

        // Otros botones (visuales)
        findViewById<Button>(R.id.btnEditProfile).setOnClickListener {
            Toast.makeText(this, "Editar Perfil", Toast.LENGTH_SHORT).show()
        }

        findViewById<LinearLayout>(R.id.btnMedicalHistory).setOnClickListener {
            Toast.makeText(this, "Historial Médico", Toast.LENGTH_SHORT).show()
        }

        findViewById<LinearLayout>(R.id.btnPremium).setOnClickListener {
            Toast.makeText(this, "Plan Premium", Toast.LENGTH_SHORT).show()
        }
    }
}

