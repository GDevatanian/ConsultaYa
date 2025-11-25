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

data class Turno(val nombre: String, val especialidad: String, val fechaHora: String, val motivo: String = "")

class MainActivity : AppCompatActivity() {
    private var horarioSeleccionado: String? = null
    private val turnos = mutableListOf(
        Turno("Dr. Pepe Pepito", "Cardiologia", "27/11/25, 18:00hs", "Dolor en el pecho y fatiga"),
        Turno("Dra. Pepa Pepita", "Dermatologia", "1/12/25, 09:00hs", "Control de lunares"),
        Turno("Dr. Juan Perez", "Clinico", "20/02/26, 10:15hs", "Chequeo general anual")
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

        findViewById<android.view.View>(R.id.btnEmergencia).setOnClickListener {
            mostrarEmergenciaConfirmar()
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

        val listaEspecialidad = findViewById<LinearLayout>(R.id.listaMedicosEspecialidad)
        val listaNombreAsc = findViewById<LinearLayout>(R.id.listaMedicosNombreAsc)
        val menuFiltros = findViewById<LinearLayout>(R.id.menuFiltros)
        val overlayFiltros = findViewById<View>(R.id.overlayFiltros)
        val labelFiltroActivo = findViewById<TextView>(R.id.labelFiltroActivo)
        val btnMenu = findViewById<TextView>(R.id.btnMenu)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        fun ocultarMenu() {
            menuFiltros.visibility = View.GONE
            overlayFiltros.visibility = View.GONE
        }

        fun mostrarMenu() {
            menuFiltros.visibility = View.VISIBLE
            overlayFiltros.visibility = View.VISIBLE
        }

        btnBack.setOnClickListener {
            mostrarHome()
        }

        btnMenu.setOnClickListener {
            if (menuFiltros.visibility == View.VISIBLE) {
                ocultarMenu()
            } else {
                mostrarMenu()
            }
        }

        overlayFiltros.setOnClickListener {
            ocultarMenu()
        }

        findViewById<LinearLayout>(R.id.btnFiltroEspecialidad).setOnClickListener {
            listaEspecialidad.visibility = View.VISIBLE
            listaNombreAsc.visibility = View.GONE
            labelFiltroActivo.text = "Ordenado por: Especialidad"
            ocultarMenu()
        }

        findViewById<LinearLayout>(R.id.btnFiltroNombreAsc).setOnClickListener {
            listaEspecialidad.visibility = View.GONE
            listaNombreAsc.visibility = View.VISIBLE
            labelFiltroActivo.text = "Ordenado por: Nombre (A-Z)"
            ocultarMenu()
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

        findViewById<Button>(R.id.btnAgendar1_nombre).setOnClickListener {
            mostrarAgendarTurno("Dr. Pepe Pepito", "Cardiologia", "◇ 4.8 (128)")
        }
        findViewById<Button>(R.id.btnAgendar2_nombre).setOnClickListener {
            mostrarAgendarTurno("Dra. Pepa Pepita", "Dermatologia", "◇ 4.6 (95)")
        }
        findViewById<Button>(R.id.btnAgendar3_nombre).setOnClickListener {
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
            card.findViewById<ImageView>(R.id.imagenMedico).setImageResource(obtenerFotoDoctor(turno.nombre))

            card.setOnClickListener {
                mostrarSalaEspera(turno, index)
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
        findViewById<ImageView>(R.id.imagenMedicoElegido).setImageResource(obtenerFotoDoctor(nombre))

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
                turnos.add(Turno(nombre, especialidad, fechaHora, motivo))
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

        findViewById<ImageView>(R.id.btnVerDetalle1).setOnClickListener {
            mostrarDetalleConsulta("Dr. Juan Pérez", "Cardiología", "15/11/2024, 10:30hs.", "El paciente presenta síntomas de fatiga y dolor en el pecho. Se recomienda realizar estudios complementarios y seguimiento en 30 días. Presión arterial: 130/85. Frecuencia cardíaca: 72 bpm.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle2).setOnClickListener {
            mostrarDetalleConsulta("Dra. María García", "Dermatología", "10/10/2024, 14:00hs.", "Control dermatológico de rutina. Se observa mejora en las lesiones previas. Se recomienda continuar con el tratamiento tópico indicado.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle3).setOnClickListener {
            mostrarDetalleConsulta("Dr. Carlos López", "Cardiología", "05/09/2024, 09:15hs.", "Consulta cardiológica de seguimiento. Electrocardiograma dentro de parámetros normales. Se mantiene tratamiento actual.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle4).setOnClickListener {
            mostrarDetalleConsulta("Dra. Ana Martínez", "Pediatría", "28/08/2024, 16:45hs.", "Control pediátrico de rutina. Niño en buen estado general. Peso y talla dentro de percentiles normales.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle5).setOnClickListener {
            mostrarDetalleConsulta("Dr. Roberto Sánchez", "Oftalmología", "15/08/2024, 11:00hs.", "Consulta oftalmológica. Agudeza visual estable. Se recomienda continuar con el uso de lentes correctivos.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle6).setOnClickListener {
            mostrarDetalleConsulta("Dra. Laura Fernández", "Ginecología", "01/08/2024, 13:30hs.", "Control ginecológico anual. Examen físico normal. Se programaron estudios complementarios.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle1_fechaDesc).setOnClickListener {
            mostrarDetalleConsulta("Dr. Juan Pérez", "Cardiología", "15/11/2024, 10:30hs.", "El paciente presenta síntomas de fatiga y dolor en el pecho. Se recomienda realizar estudios complementarios y seguimiento en 30 días. Presión arterial: 130/85. Frecuencia cardíaca: 72 bpm.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle2_fechaDesc).setOnClickListener {
            mostrarDetalleConsulta("Dra. María García", "Dermatología", "10/10/2024, 14:00hs.", "Control dermatológico de rutina. Se observa mejora en las lesiones previas. Se recomienda continuar con el tratamiento tópico indicado.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle3_fechaDesc).setOnClickListener {
            mostrarDetalleConsulta("Dr. Carlos López", "Cardiología", "05/09/2024, 09:15hs.", "Consulta cardiológica de seguimiento. Electrocardiograma dentro de parámetros normales. Se mantiene tratamiento actual.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle4_fechaDesc).setOnClickListener {
            mostrarDetalleConsulta("Dra. Ana Martínez", "Pediatría", "28/08/2024, 16:45hs.", "Control pediátrico de rutina. Niño en buen estado general. Peso y talla dentro de percentiles normales.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle5_fechaDesc).setOnClickListener {
            mostrarDetalleConsulta("Dr. Roberto Sánchez", "Oftalmología", "15/08/2024, 11:00hs.", "Consulta oftalmológica. Agudeza visual estable. Se recomienda continuar con el uso de lentes correctivos.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle6_fechaDesc).setOnClickListener {
            mostrarDetalleConsulta("Dra. Laura Fernández", "Ginecología", "01/08/2024, 13:30hs.", "Control ginecológico anual. Examen físico normal. Se programaron estudios complementarios.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle1_nombreAsc).setOnClickListener {
            mostrarDetalleConsulta("Dr. Juan Pérez", "Cardiología", "15/11/2024, 10:30hs.", "El paciente presenta síntomas de fatiga y dolor en el pecho. Se recomienda realizar estudios complementarios y seguimiento en 30 días. Presión arterial: 130/85. Frecuencia cardíaca: 72 bpm.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle2_nombreAsc).setOnClickListener {
            mostrarDetalleConsulta("Dra. María García", "Dermatología", "10/10/2024, 14:00hs.", "Control dermatológico de rutina. Se observa mejora en las lesiones previas. Se recomienda continuar con el tratamiento tópico indicado.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle3_nombreAsc).setOnClickListener {
            mostrarDetalleConsulta("Dr. Carlos López", "Cardiología", "05/09/2024, 09:15hs.", "Consulta cardiológica de seguimiento. Electrocardiograma dentro de parámetros normales. Se mantiene tratamiento actual.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle4_nombreAsc).setOnClickListener {
            mostrarDetalleConsulta("Dra. Ana Martínez", "Pediatría", "28/08/2024, 16:45hs.", "Control pediátrico de rutina. Niño en buen estado general. Peso y talla dentro de percentiles normales.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle5_nombreAsc).setOnClickListener {
            mostrarDetalleConsulta("Dr. Roberto Sánchez", "Oftalmología", "15/08/2024, 11:00hs.", "Consulta oftalmológica. Agudeza visual estable. Se recomienda continuar con el uso de lentes correctivos.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle6_nombreAsc).setOnClickListener {
            mostrarDetalleConsulta("Dra. Laura Fernández", "Ginecología", "01/08/2024, 13:30hs.", "Control ginecológico anual. Examen físico normal. Se programaron estudios complementarios.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle1_nombreDesc).setOnClickListener {
            mostrarDetalleConsulta("Dr. Juan Pérez", "Cardiología", "15/11/2024, 10:30hs.", "El paciente presenta síntomas de fatiga y dolor en el pecho. Se recomienda realizar estudios complementarios y seguimiento en 30 días. Presión arterial: 130/85. Frecuencia cardíaca: 72 bpm.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle2_nombreDesc).setOnClickListener {
            mostrarDetalleConsulta("Dra. María García", "Dermatología", "10/10/2024, 14:00hs.", "Control dermatológico de rutina. Se observa mejora en las lesiones previas. Se recomienda continuar con el tratamiento tópico indicado.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle3_nombreDesc).setOnClickListener {
            mostrarDetalleConsulta("Dr. Carlos López", "Cardiología", "05/09/2024, 09:15hs.", "Consulta cardiológica de seguimiento. Electrocardiograma dentro de parámetros normales. Se mantiene tratamiento actual.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle4_nombreDesc).setOnClickListener {
            mostrarDetalleConsulta("Dra. Ana Martínez", "Pediatría", "28/08/2024, 16:45hs.", "Control pediátrico de rutina. Niño en buen estado general. Peso y talla dentro de percentiles normales.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle5_nombreDesc).setOnClickListener {
            mostrarDetalleConsulta("Dr. Roberto Sánchez", "Oftalmología", "15/08/2024, 11:00hs.", "Consulta oftalmológica. Agudeza visual estable. Se recomienda continuar con el uso de lentes correctivos.")
        }

        findViewById<ImageView>(R.id.btnVerDetalle6_nombreDesc).setOnClickListener {
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
        findViewById<ImageView>(R.id.imagenDoctorDetalle).setImageResource(obtenerFotoDoctor(nombre))

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
        findViewById<ImageView>(R.id.imagenDoctorChat).setImageResource(obtenerFotoDoctor(nombreDoctor))

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

    private fun mostrarDialogoCancelarEnSalaEspera(index: Int, turno: Turno) {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Cancelar turno")
        builder.setMessage("¿Estás seguro que deseas cancelar el turno con ${turno.nombre} de ${turno.especialidad} el ${turno.fechaHora}?")
        
        builder.setPositiveButton("Cancelar turno") { dialog, _ ->
            turnos.removeAt(index)
            dialog.dismiss()
            Toast.makeText(this, "Turno cancelado", Toast.LENGTH_SHORT).show()
            mostrarTurnos()
        }
        
        builder.setNegativeButton("Volver") { dialog, _ ->
            dialog.dismiss()
        }
        
        builder.show()
    }

    private fun mostrarSalaEspera(turno: Turno, index: Int) {
        setContentView(R.layout.activity_sala_espera)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            mostrarTurnos()
        }

        findViewById<TextView>(R.id.nombreProfesional).text = turno.nombre
        findViewById<TextView>(R.id.especialidadProfesional).text = turno.especialidad
        findViewById<ImageView>(R.id.imagenProfesional).setImageResource(obtenerFotoDoctor(turno.nombre))

        val fechaFormateada = "Martes ${turno.fechaHora}"
        findViewById<TextView>(R.id.fechaHoraTurno).text = fechaFormateada
        
        findViewById<TextView>(R.id.descripcionTurno).text = turno.motivo

        findViewById<Button>(R.id.btnIngresarLlamada).setOnClickListener {
            mostrarVideollamadaNormal(turno)
        }

        findViewById<Button>(R.id.btnCancelarConsulta).setOnClickListener {
            mostrarDialogoCancelarEnSalaEspera(index, turno)
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

    private fun obtenerFotoDoctor(nombre: String): Int {
        return when (nombre) {
            "Dr. Pepe Pepito" -> R.drawable.pepe_pepito
            "Dra. Pepa Pepita" -> R.drawable.pepa_pepita
            "Dr. Juan Pérez", "Dr. Juan Perez" -> R.drawable.juan_perez
            "Dra. María García", "Dra. Maria Garcia" -> R.drawable.maria_garcia
            "Dr. Carlos López", "Dr. Carlos Lopez" -> R.drawable.carlos_lopez
            "Dra. Ana Martínez", "Dra. Ana Martinez" -> R.drawable.ana_martinez
            "Dr. Roberto Sánchez", "Dr. Roberto Sanchez" -> R.drawable.roberto_sanchez
            "Dra. Laura Fernández", "Dra. Laura Fernandez" -> R.drawable.laura_fernandez
            else -> R.drawable.doc
        }
    }

    private fun mostrarDialogo(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun mostrarEmergenciaConfirmar() {
        setContentView(R.layout.activity_emergencia_confirmar)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            mostrarHome()
        }

        findViewById<TextView>(R.id.btnConectar).setOnClickListener {
            mostrarEmergenciaConectando()
        }

        findViewById<TextView>(R.id.btnCancelar).setOnClickListener {
            mostrarHome()
        }
    }

    private fun mostrarEmergenciaConectando() {
        setContentView(R.layout.activity_emergencia_conectando)

        findViewById<TextView>(R.id.btnCancelarConexion).setOnClickListener {
            mostrarHome()
        }

        // Simular conexión después de 5 segundos
        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
            mostrarVideollamadaEmergencia()
        }, 5000)
    }

    private fun mostrarVideollamadaEmergencia() {
        mostrarVideollamadaNormal(Turno("Dr. Guardia", "Emergencias", "Ahora", "Consulta de emergencia"))
    }

    private fun mostrarVideollamadaNormal(turno: Turno) {
        setContentView(R.layout.activity_videollamada)

        // Variables de estado
        var videoEncendido = true
        var micEncendido = true
        var volumenEncendido = true

        // Configurar nombre del doctor
        findViewById<TextView>(R.id.nombreDoctorVideollamada).text = turno.nombre

        // Botón 1: Prender/Apagar cámara
        val btnToggleVideo = findViewById<android.widget.ImageButton>(R.id.btnToggleVideo)
        btnToggleVideo.setOnClickListener {
            videoEncendido = !videoEncendido
            if (videoEncendido) {
                btnToggleVideo.setImageResource(R.drawable.ic_video)
                Toast.makeText(this, "Cámara encendida", Toast.LENGTH_SHORT).show()
            } else {
                btnToggleVideo.setImageResource(R.drawable.ic_video_off)
                Toast.makeText(this, "Cámara apagada", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón 2: Silenciar llamada
        val btnToggleVolume = findViewById<android.widget.ImageButton>(R.id.btnToggleVolume)
        btnToggleVolume.setOnClickListener {
            volumenEncendido = !volumenEncendido
            if (volumenEncendido) {
                btnToggleVolume.setImageResource(R.drawable.ic_volume_up)
                Toast.makeText(this, "Llamada con sonido", Toast.LENGTH_SHORT).show()
            } else {
                btnToggleVolume.setImageResource(R.drawable.ic_volume_off)
                Toast.makeText(this, "Llamada silenciada", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón 3: Mutear micrófono
        val btnToggleMic = findViewById<android.widget.ImageButton>(R.id.btnToggleMic)
        btnToggleMic.setOnClickListener {
            micEncendido = !micEncendido
            if (micEncendido) {
                btnToggleMic.setImageResource(R.drawable.ic_mic)
                Toast.makeText(this, "Micrófono activado", Toast.LENGTH_SHORT).show()
            } else {
                btnToggleMic.setImageResource(R.drawable.ic_mic_off)
                Toast.makeText(this, "Micrófono silenciado", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón 4: Finalizar llamada
        findViewById<android.widget.ImageButton>(R.id.btnEndCall).setOnClickListener {
            Toast.makeText(this, "Llamada finalizada", Toast.LENGTH_SHORT).show()
            mostrarHome()
        }

        // Botón 5: Dar vuelta cámara (dentro del recuadro del paciente)
        findViewById<android.widget.ImageButton>(R.id.btnFlipCamera).setOnClickListener {
            Toast.makeText(this, "Rotar cámara", Toast.LENGTH_SHORT).show()
        }
    }
}