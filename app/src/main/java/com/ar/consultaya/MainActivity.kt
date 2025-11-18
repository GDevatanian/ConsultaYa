package com.ar.consultaya

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

data class Turno(val nombre: String, val especialidad: String, val fechaHora: String)

class MainActivity : AppCompatActivity() {
    private var horarioSeleccionado: String? = null
    private val turnos = mutableListOf(
        Turno("Dr. Pepe Pepito", "Cardiologia", "10/11/25, 15:00hs"),
        Turno("Dra. Pepa Pepita", "Dermatologia", "1/12/25, 08:00hs"),
        Turno("Dr. Juan Perez", "Clinico", "20/02/26, 10:15hs")
    )
    private var turnoSeleccionadoParaCancelar: Int = -1

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
            mostrarTurnos()
        }

        findViewById<android.view.View>(R.id.btnMisChats).setOnClickListener {
            Toast.makeText(this, "Mis chats", Toast.LENGTH_SHORT).show()
        }

        findViewById<android.view.View>(R.id.btnHistorial).setOnClickListener {
            Toast.makeText(this, "Historial", Toast.LENGTH_SHORT).show()
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
            mostrarTurnos()
        }

        findViewById<android.view.View>(R.id.navPerfil).setOnClickListener {
            Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show()
        }
    }

    private fun mostrarBuscarMedico() {
        setContentView(R.layout.activity_buscar_medico)

        findViewById<Button>(R.id.btnAgendar1).setOnClickListener {
            mostrarAgendarTurno("Dr. Pepe Pepito", "Cardiologia", "◇ 4.8 (128)")
        }

        findViewById<Button>(R.id.btnAgendar2).setOnClickListener {
            mostrarAgendarTurno("Dra. Pepa Pepita", "Dermatologia", "◇ 4.6 (95)")
        }

        findViewById<Button>(R.id.btnAgendar3).setOnClickListener {
            mostrarAgendarTurno("Dr. Juan Perez", "Clinico", "◇ 4.9 (203)")
        }

        findViewById<android.view.View>(R.id.navHome).setOnClickListener {
            mostrarHome()
        }

        findViewById<android.view.View>(R.id.navBuscar).setOnClickListener {
            mostrarBuscarMedico()
        }

        findViewById<android.view.View>(R.id.navTurnos).setOnClickListener {
            mostrarTurnos()
        }

        findViewById<android.view.View>(R.id.navPerfil).setOnClickListener {
            Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show()
        }
    }

    private fun mostrarTurnos() {
        setContentView(R.layout.activity_turnos)

        val listaTurnos = findViewById<LinearLayout>(R.id.listaTurnos)
        listaTurnos.removeAllViews()

        turnos.forEachIndexed { index, turno ->
            val card = layoutInflater.inflate(R.layout.item_turno, listaTurnos, false)
            card.findViewById<TextView>(R.id.nombreMedico).text = turno.nombre
            card.findViewById<TextView>(R.id.especialidadMedico).text = turno.especialidad
            card.findViewById<TextView>(R.id.fechaHora).text = turno.fechaHora
            card.findViewById<Button>(R.id.btnCancelar).setOnClickListener {
                mostrarDialogoCancelar(index, turno)
            }
            listaTurnos.addView(card)
        }

        findViewById<View>(R.id.navHome).setOnClickListener {
            mostrarHome()
        }

        findViewById<View>(R.id.navBuscar).setOnClickListener {
            mostrarBuscarMedico()
        }

        findViewById<View>(R.id.navTurnos).setOnClickListener {
            mostrarTurnos()
        }

        findViewById<View>(R.id.navPerfil).setOnClickListener {
            Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show()
        }
    }

    private fun mostrarDialogoCancelar(index: Int, turno: Turno) {
        turnoSeleccionadoParaCancelar = index
        val dialog = findViewById<LinearLayout>(R.id.dialogCancelar)
        dialog.visibility = View.VISIBLE
        findViewById<TextView>(R.id.detalleTurno).text = "Turno con: ${turno.nombre} de ${turno.especialidad} el ${turno.fechaHora}."

        findViewById<Button>(R.id.btnVolver).setOnClickListener {
            dialog.visibility = View.GONE
        }

        findViewById<Button>(R.id.btnConfirmarCancelar).setOnClickListener {
            turnos.removeAt(index)
            dialog.visibility = View.GONE
            mostrarTurnos()
            Toast.makeText(this, "Turno cancelado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun mostrarAgendarTurno(nombre: String, especialidad: String, rating: String) {
        setContentView(R.layout.activity_agendar_turno)
        horarioSeleccionado = null

        findViewById<TextView>(R.id.nombreMedicoElegido).text = nombre
        findViewById<TextView>(R.id.especialidadMedicoElegido).text = especialidad
        findViewById<TextView>(R.id.ratingMedicoElegido).text = rating

        val horarios = listOf(
            R.id.btnHora1, R.id.btnHora2, R.id.btnHora3, R.id.btnHora4, R.id.btnHora5,
            R.id.btnHora6, R.id.btnHora7, R.id.btnHora8, R.id.btnHora9, R.id.btnHora10
        )

        horarios.forEach { id ->
            findViewById<Button>(id).setOnClickListener { view ->
                horarios.forEach { otroId ->
                    findViewById<Button>(otroId).background = ContextCompat.getDrawable(this, R.drawable.rounded_button)
                    findViewById<Button>(otroId).setTextColor(ContextCompat.getColor(this, R.color.text_dark))
                }
                (view as Button).background = ContextCompat.getDrawable(this, R.drawable.button_horario_selected)
                view.setTextColor(ContextCompat.getColor(this, R.color.white))
                horarioSeleccionado = view.text.toString()
            }
        }

        findViewById<Button>(R.id.btnConfirmarTurno).setOnClickListener {
            val motivo = findViewById<EditText>(R.id.editMotivo).text.toString()
            if (horarioSeleccionado == null) {
                Toast.makeText(this, "Seleccione un horario", Toast.LENGTH_SHORT).show()
            } else {
                var fecha = findViewById<TextView>(R.id.txtFecha).text.toString()
                fecha = fecha.replace("/2025", "/25").replace("/2026", "/26")
                val fechaHora = "$fecha, ${horarioSeleccionado}hs"
                turnos.add(Turno(nombre, especialidad, fechaHora))
                Toast.makeText(this, "Turno confirmado", Toast.LENGTH_SHORT).show()
                mostrarTurnos()
            }
        }

        findViewById<android.view.View>(R.id.navHome).setOnClickListener {
            mostrarHome()
        }

        findViewById<android.view.View>(R.id.navBuscar).setOnClickListener {
            mostrarBuscarMedico()
        }

        findViewById<android.view.View>(R.id.navTurnos).setOnClickListener {
            mostrarTurnos()
        }

        findViewById<android.view.View>(R.id.navPerfil).setOnClickListener {
            Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show()
        }
    }
}