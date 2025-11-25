package com.ar.consultaya

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class TurnoConfirmadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turno_confirmado)

        // Obtener datos del intent
        val medico = intent.getStringExtra("MEDICO") ?: "Médico"
        val especialidad = intent.getStringExtra("ESPECIALIDAD") ?: "Especialidad"
        val fechaHora = intent.getStringExtra("FECHA_HORA") ?: "Fecha"

        // Mostrar datos
        findViewById<TextView>(R.id.txtMedicoConfirmado).text = medico
        findViewById<TextView>(R.id.txtEspecialidadConfirmada).text = especialidad
        findViewById<TextView>(R.id.txtFechaConfirmada).text = fechaHora

        // Botón cerrar (X)
        findViewById<ImageView>(R.id.btnCerrar).setOnClickListener {
            irAHome()
        }

        // Botón ir al inicio
        findViewById<Button>(R.id.btnIrHome).setOnClickListener {
            irAHome()
        }

        // Iniciar animaciones
        animarEntrada()
    }

    private fun irAHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        overridePendingTransition(0, 0)
        finish()
    }

    override fun onBackPressed() {
        irAHome()
    }

    private fun animarEntrada() {
        val circle = findViewById<View>(R.id.circleBackground)
        val check = findViewById<ImageView>(R.id.iconCheck)
        
        // Escalar el círculo desde 0
        circle.scaleX = 0f
        circle.scaleY = 0f
        circle.animate()
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(500)
            .setInterpolator(OvershootInterpolator(1.5f))
            .start()

        // Escalar el check con delay
        check.scaleX = 0f
        check.scaleY = 0f
        check.animate()
            .scaleX(1f)
            .scaleY(1f)
            .setStartDelay(300)
            .setDuration(400)
            .setInterpolator(OvershootInterpolator(2f))
            .start()

        // Animar confetti
        val confettis = listOf(
            R.id.confetti1, R.id.confetti2, R.id.confetti3, R.id.confetti4,
            R.id.confetti5, R.id.confetti6, R.id.confetti7, R.id.confetti8
        )

        confettis.forEachIndexed { index, id ->
            val confetti = findViewById<View>(id)
            confetti.alpha = 0f
            
            // Posición inicial en el centro
            confetti.translationX = 0f
            confetti.translationY = 0f
            
            // Calcular dirección de explosión
            val angle = (index * 45.0) * (Math.PI / 180.0)
            val distance = 150f + Random.nextFloat() * 100f
            val targetX = (Math.cos(angle) * distance).toFloat()
            val targetY = (Math.sin(angle) * distance).toFloat()
            
            confetti.animate()
                .alpha(1f)
                .translationX(targetX)
                .translationY(targetY)
                .scaleX(1.5f)
                .scaleY(1.5f)
                .rotation(Random.nextFloat() * 360f)
                .setStartDelay(400 + (index * 50L))
                .setDuration(600)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .withEndAction {
                    // Fade out después de la explosión
                    confetti.animate()
                        .alpha(0f)
                        .translationY(targetY + 100f)
                        .setDuration(400)
                        .start()
                }
                .start()
        }
    }
}

