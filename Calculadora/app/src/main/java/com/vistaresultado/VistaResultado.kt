package com.vistaresultado

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class VistaResultado(context: Context?, private val resultado: Double?) : View(context) {

    private val paint: Paint = Paint()

    init {
        // Configurar el Paint
        paint.color = Color.BLACK
        paint.textSize = 50f
        paint.isAntiAlias = true
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Color de fondo
        canvas.drawRGB(255, 255, 255)

        // Configurar el texto
        val resultadoTexto = "El resultado de su operación es: ${resultado ?: "Sin resultado"}"

        //Centrar en la pantalla
        val textAncho = paint.measureText(resultadoTexto)
        val posX = (canvas.width - textAncho) / 2
        val posY = canvas.height / 2f

        // Dibujar el texto en el Canvas
        canvas.drawText(resultadoTexto, posX, posY, paint)
    }
}