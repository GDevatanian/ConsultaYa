package com.ar.consultaya

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
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
        val pantallaInicial = intent.getStringExtra("PANTALLA_INICIAL")
        when (pantallaInicial) {
            "BUSCAR" -> mostrarBuscarMedico()
            "TURNOS" -> mostrarTurnos()
            else -> mostrarHome()
        }
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
            mostrarListaChats()
        }

        findViewById<android.view.View>(R.id.btnHistorial).setOnClickListener {
            mostrarHistorial()
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
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }

    private fun mostrarBuscarMedico() {
        setContentView(R.layout.activity_buscar_medico)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            mostrarHome()
        }

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
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }

    private fun mostrarTurnos() {
        setContentView(R.layout.activity_turnos)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            mostrarHome()
        }

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
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
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

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            mostrarBuscarMedico()
        }

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
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }

    private fun mostrarHistorial() {
        setContentView(R.layout.activity_historial)

        val listaOriginal = findViewById<LinearLayout>(R.id.listaConsultas)
        val listaFechaDesc = findViewById<LinearLayout>(R.id.listaConsultasFechaDesc)
        val listaNombreAsc = findViewById<LinearLayout>(R.id.listaConsultasNombreAsc)
        val listaNombreDesc = findViewById<LinearLayout>(R.id.listaConsultasNombreDesc)
        val menuFiltros = findViewById<LinearLayout>(R.id.menuFiltros)
        val overlayFiltros = findViewById<View>(R.id.overlayFiltros)
        val labelFiltroActivo = findViewById<TextView>(R.id.labelFiltroActivo)

        fun ocultarMenu() {
            menuFiltros.visibility = View.GONE
            overlayFiltros.visibility = View.GONE
        }

        fun mostrarMenu() {
            menuFiltros.visibility = View.VISIBLE
            overlayFiltros.visibility = View.VISIBLE
        }

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            mostrarHome()
        }

        findViewById<TextView>(R.id.btnFiltro).setOnClickListener {
            if (menuFiltros.visibility == View.VISIBLE) {
                ocultarMenu()
            } else {
                mostrarMenu()
            }
        }

        overlayFiltros.setOnClickListener {
            ocultarMenu()
        }

        findViewById<LinearLayout>(R.id.btnFiltroFechaAsc).setOnClickListener {
            listaOriginal.visibility = View.VISIBLE
            listaFechaDesc.visibility = View.GONE
            listaNombreAsc.visibility = View.GONE
            listaNombreDesc.visibility = View.GONE
            labelFiltroActivo.text = "Ordenado por: Fecha (Más reciente primero)"
            ocultarMenu()
        }

        findViewById<LinearLayout>(R.id.btnFiltroFechaDesc).setOnClickListener {
            listaOriginal.visibility = View.GONE
            listaFechaDesc.visibility = View.VISIBLE
            listaNombreAsc.visibility = View.GONE
            listaNombreDesc.visibility = View.GONE
            labelFiltroActivo.text = "Ordenado por: Fecha (Más antiguo primero)"
            ocultarMenu()
        }

        findViewById<LinearLayout>(R.id.btnFiltroNombreAsc).setOnClickListener {
            listaOriginal.visibility = View.GONE
            listaFechaDesc.visibility = View.GONE
            listaNombreAsc.visibility = View.VISIBLE
            listaNombreDesc.visibility = View.GONE
            labelFiltroActivo.text = "Ordenado por: Nombre (A-Z)"
            ocultarMenu()
        }

        findViewById<LinearLayout>(R.id.btnFiltroNombreDesc).setOnClickListener {
            listaOriginal.visibility = View.GONE
            listaFechaDesc.visibility = View.GONE
            listaNombreAsc.visibility = View.GONE
            listaNombreDesc.visibility = View.VISIBLE
            labelFiltroActivo.text = "Ordenado por: Nombre (Z-A)"
            ocultarMenu()
        }

        // Listeners para lista original
        findViewById<Button>(R.id.btnVerDetalle1).setOnClickListener {
            mostrarDetalleConsulta("Dr. Juan Pérez", "Cardiología", "15/11/2024, 10:30hs.", "El paciente presenta síntomas de fatiga y dolor en el pecho. Se recomienda realizar estudios complementarios y seguimiento en 30 días. Presión arterial: 130/85. Frecuencia cardíaca: 72 bpm.")
        }

        findViewById<Button>(R.id.btnVerDetalle2).setOnClickListener {
            mostrarDetalleConsulta("Dra. María García", "Dermatología", "10/10/2024, 14:00hs.", "Control dermatológico de rutina. Se observa mejora en las lesiones previas. Se recomienda continuar con el tratamiento tópico indicado.")
        }

        findViewById<Button>(R.id.btnVerDetalle3).setOnClickListener {
            mostrarDetalleConsulta("Dr. Carlos López", "Cardiología", "05/09/2024, 09:15hs.", "Consulta cardiológica de seguimiento. Electrocardiograma dentro de parámetros normales. Se mantiene tratamiento actual.")
        }

        findViewById<Button>(R.id.btnVerDetalle4).setOnClickListener {
            mostrarDetalleConsulta("Dra. Ana Martínez", "Pediatría", "28/08/2024, 16:45hs.", "Control pediátrico de rutina. Niño en buen estado general. Peso y talla dentro de percentiles normales.")
        }

        findViewById<Button>(R.id.btnVerDetalle5).setOnClickListener {
            mostrarDetalleConsulta("Dr. Roberto Sánchez", "Oftalmología", "15/08/2024, 11:00hs.", "Consulta oftalmológica. Agudeza visual estable. Se recomienda continuar con el uso de lentes correctivos.")
        }

        findViewById<Button>(R.id.btnVerDetalle6).setOnClickListener {
            mostrarDetalleConsulta("Dra. Laura Fernández", "Ginecología", "01/08/2024, 13:30hs.", "Control ginecológico anual. Examen físico normal. Se programaron estudios complementarios.")
        }

        // Listeners para lista fecha descendente
        findViewById<Button>(R.id.btnVerDetalle1_fechaDesc).setOnClickListener {
            mostrarDetalleConsulta("Dr. Juan Pérez", "Cardiología", "15/11/2024, 10:30hs.", "El paciente presenta síntomas de fatiga y dolor en el pecho. Se recomienda realizar estudios complementarios y seguimiento en 30 días. Presión arterial: 130/85. Frecuencia cardíaca: 72 bpm.")
        }

        findViewById<Button>(R.id.btnVerDetalle2_fechaDesc).setOnClickListener {
            mostrarDetalleConsulta("Dra. María García", "Dermatología", "10/10/2024, 14:00hs.", "Control dermatológico de rutina. Se observa mejora en las lesiones previas. Se recomienda continuar con el tratamiento tópico indicado.")
        }

        findViewById<Button>(R.id.btnVerDetalle3_fechaDesc).setOnClickListener {
            mostrarDetalleConsulta("Dr. Carlos López", "Cardiología", "05/09/2024, 09:15hs.", "Consulta cardiológica de seguimiento. Electrocardiograma dentro de parámetros normales. Se mantiene tratamiento actual.")
        }

        findViewById<Button>(R.id.btnVerDetalle4_fechaDesc).setOnClickListener {
            mostrarDetalleConsulta("Dra. Ana Martínez", "Pediatría", "28/08/2024, 16:45hs.", "Control pediátrico de rutina. Niño en buen estado general. Peso y talla dentro de percentiles normales.")
        }

        findViewById<Button>(R.id.btnVerDetalle5_fechaDesc).setOnClickListener {
            mostrarDetalleConsulta("Dr. Roberto Sánchez", "Oftalmología", "15/08/2024, 11:00hs.", "Consulta oftalmológica. Agudeza visual estable. Se recomienda continuar con el uso de lentes correctivos.")
        }

        findViewById<Button>(R.id.btnVerDetalle6_fechaDesc).setOnClickListener {
            mostrarDetalleConsulta("Dra. Laura Fernández", "Ginecología", "01/08/2024, 13:30hs.", "Control ginecológico anual. Examen físico normal. Se programaron estudios complementarios.")
        }

        // Listeners para lista nombre ascendente
        findViewById<Button>(R.id.btnVerDetalle1_nombreAsc).setOnClickListener {
            mostrarDetalleConsulta("Dr. Juan Pérez", "Cardiología", "15/11/2024, 10:30hs.", "El paciente presenta síntomas de fatiga y dolor en el pecho. Se recomienda realizar estudios complementarios y seguimiento en 30 días. Presión arterial: 130/85. Frecuencia cardíaca: 72 bpm.")
        }

        findViewById<Button>(R.id.btnVerDetalle2_nombreAsc).setOnClickListener {
            mostrarDetalleConsulta("Dra. María García", "Dermatología", "10/10/2024, 14:00hs.", "Control dermatológico de rutina. Se observa mejora en las lesiones previas. Se recomienda continuar con el tratamiento tópico indicado.")
        }

        findViewById<Button>(R.id.btnVerDetalle3_nombreAsc).setOnClickListener {
            mostrarDetalleConsulta("Dr. Carlos López", "Cardiología", "05/09/2024, 09:15hs.", "Consulta cardiológica de seguimiento. Electrocardiograma dentro de parámetros normales. Se mantiene tratamiento actual.")
        }

        findViewById<Button>(R.id.btnVerDetalle4_nombreAsc).setOnClickListener {
            mostrarDetalleConsulta("Dra. Ana Martínez", "Pediatría", "28/08/2024, 16:45hs.", "Control pediátrico de rutina. Niño en buen estado general. Peso y talla dentro de percentiles normales.")
        }

        findViewById<Button>(R.id.btnVerDetalle5_nombreAsc).setOnClickListener {
            mostrarDetalleConsulta("Dr. Roberto Sánchez", "Oftalmología", "15/08/2024, 11:00hs.", "Consulta oftalmológica. Agudeza visual estable. Se recomienda continuar con el uso de lentes correctivos.")
        }

        findViewById<Button>(R.id.btnVerDetalle6_nombreAsc).setOnClickListener {
            mostrarDetalleConsulta("Dra. Laura Fernández", "Ginecología", "01/08/2024, 13:30hs.", "Control ginecológico anual. Examen físico normal. Se programaron estudios complementarios.")
        }

        // Listeners para lista nombre descendente
        findViewById<Button>(R.id.btnVerDetalle1_nombreDesc).setOnClickListener {
            mostrarDetalleConsulta("Dr. Juan Pérez", "Cardiología", "15/11/2024, 10:30hs.", "El paciente presenta síntomas de fatiga y dolor en el pecho. Se recomienda realizar estudios complementarios y seguimiento en 30 días. Presión arterial: 130/85. Frecuencia cardíaca: 72 bpm.")
        }

        findViewById<Button>(R.id.btnVerDetalle2_nombreDesc).setOnClickListener {
            mostrarDetalleConsulta("Dra. María García", "Dermatología", "10/10/2024, 14:00hs.", "Control dermatológico de rutina. Se observa mejora en las lesiones previas. Se recomienda continuar con el tratamiento tópico indicado.")
        }

        findViewById<Button>(R.id.btnVerDetalle3_nombreDesc).setOnClickListener {
            mostrarDetalleConsulta("Dr. Carlos López", "Cardiología", "05/09/2024, 09:15hs.", "Consulta cardiológica de seguimiento. Electrocardiograma dentro de parámetros normales. Se mantiene tratamiento actual.")
        }

        findViewById<Button>(R.id.btnVerDetalle4_nombreDesc).setOnClickListener {
            mostrarDetalleConsulta("Dra. Ana Martínez", "Pediatría", "28/08/2024, 16:45hs.", "Control pediátrico de rutina. Niño en buen estado general. Peso y talla dentro de percentiles normales.")
        }

        findViewById<Button>(R.id.btnVerDetalle5_nombreDesc).setOnClickListener {
            mostrarDetalleConsulta("Dr. Roberto Sánchez", "Oftalmología", "15/08/2024, 11:00hs.", "Consulta oftalmológica. Agudeza visual estable. Se recomienda continuar con el uso de lentes correctivos.")
        }

        findViewById<Button>(R.id.btnVerDetalle6_nombreDesc).setOnClickListener {
            mostrarDetalleConsulta("Dra. Laura Fernández", "Ginecología", "01/08/2024, 13:30hs.", "Control ginecológico anual. Examen físico normal. Se programaron estudios complementarios.")
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
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }

    private fun mostrarDetalleConsulta(nombre: String, especialidad: String, fechaHora: String, anotaciones: String) {
        setContentView(R.layout.activity_detalle_consulta)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            mostrarHistorial()
        }

        findViewById<TextView>(R.id.nombreDoctor).text = nombre
        findViewById<TextView>(R.id.especialidadDoctor).text = especialidad
        findViewById<TextView>(R.id.fechaHoraConsulta).text = fechaHora
        findViewById<TextView>(R.id.textAnotaciones).text = anotaciones

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

    private fun mostrarListaChats() {
        setContentView(R.layout.activity_lista_chats)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            mostrarHome()
        }

        findViewById<android.view.View>(R.id.chat1).setOnClickListener {
            mostrarChat("Dr. Juan Pérez")
        }

        findViewById<android.view.View>(R.id.chat2).setOnClickListener {
            mostrarChat("Dra. María García")
        }

        findViewById<android.view.View>(R.id.chat3).setOnClickListener {
            mostrarChat("Dr. Carlos López")
        }

        findViewById<android.view.View>(R.id.chat4).setOnClickListener {
            mostrarChat("Dra. Ana Martínez")
        }

        findViewById<android.view.View>(R.id.chat5).setOnClickListener {
            mostrarChat("Dr. Roberto Sánchez")
        }

        findViewById<android.view.View>(R.id.chat6).setOnClickListener {
            mostrarChat("Dra. Laura Fernández")
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

    private fun mostrarChat(nombreDoctor: String) {
        setContentView(R.layout.activity_chat)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            mostrarListaChats()
        }

        findViewById<TextView>(R.id.nombreDoctorChat).text = nombreDoctor

        findViewById<Button>(R.id.btnEnviar).setOnClickListener {
            val mensaje = findViewById<EditText>(R.id.editMensaje).text.toString()
            if (mensaje.isNotEmpty()) {
                findViewById<EditText>(R.id.editMensaje).text.clear()
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
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }
}