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
        val textoResultado = "El resultado es"
        val valorResultado = resultado?.toString() ?: "Sin resultado"

        // Medir el ancho y altura de cada línea de texto
        val anchoTextoResultado = paint.measureText(textoResultado)
        val anchoValorResultado = paint.measureText(valorResultado)
        val textHeight = paint.fontMetrics.bottom - paint.fontMetrics.top

        // Calcular posiciones para centrar el texto en el canvas
        val posXTexto = (canvas.width - anchoTextoResultado) / 2
        val posXValor = (canvas.width - anchoValorResultado) / 2

        val posYTexto = (canvas.height - 2 * textHeight) / 2
        val posYValor = posYTexto + textHeight

        // Dibujar el texto en el Canvas
        canvas.drawText(textoResultado, posXTexto, posYTexto, paint)
        canvas.drawText(valorResultado, posXValor, posYValor, paint)
    }
}