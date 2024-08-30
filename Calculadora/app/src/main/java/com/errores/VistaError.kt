package com.errores

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import com.calculadora.ErrorLexico
import com.calculadora.ErrorSintactico

class VistaError(
    context: Context?,
    private val erroresLexicos: ArrayList<ErrorLexico>?,
    private val erroresSintacticos: ArrayList<ErrorSintactico>?
) : View(context) {

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val paint = Paint().apply {
            color = Color.BLACK
            textSize = 40f
        }

        var yPosition = 100f

        // Dibujar errores léxicos
        erroresLexicos?.forEach { error ->
            val texto = "Léxico - Tipo: ${error.tipo}, Línea: ${error.linea}, Columna: ${error.columna}, Lexema: ${error.lexema}, Descripción: ${error.descripcion}"
            canvas.drawText(texto, 50f, yPosition, paint)
            yPosition += 60f
        }

        // Dibujar errores sintácticos
        erroresSintacticos?.forEach { error ->
            val texto = "Sintáctico - Tipo: ${error.tipo}, Línea: ${error.linea}, Columna: ${error.columna}, Lexema: ${error.lexema}, Descripción: ${error.descripcion}"
            canvas.drawText(texto, 50f, yPosition, paint)
            yPosition += 60f
        }
    }
}
