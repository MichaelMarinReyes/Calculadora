package com.vistaresultado

import android.os.Bundle
import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.calculadora.R
import java.security.NoSuchAlgorithmException

class Resultado : AppCompatActivity() {

    private var resultado: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.resultado)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Recibir datos de pestaña anterior
        try {
            recibirDatos()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        val textViewResultado = findViewById<TextView>(R.id.textResultado)
        textViewResultado.text = "El resultado de la operación es ${resultado ?: " "}"

        val layout1 = findViewById<View>(R.id.resultado) as ConstraintLayout
        val fondo = VistaResultado(this, this.resultado)
        layout1.addView(fondo)
    }

    //Recibir datos de pantalla anterior
    @Throws(NoSuchAlgorithmException::class)
    private fun recibirDatos() {
        val datos = intent.extras
        if (datos != null) {
            val claveResultado = "Resultado"
            this.resultado = (datos.getSerializable(claveResultado) as? Double)
        }
    }
}