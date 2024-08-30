package com.errores

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import com.calculadora.ErrorLexico
import com.calculadora.ErrorSintactico

class VistaError(context: Context?, private val erroresLexicos: ArrayList<ErrorLexico>?, private val erroresSintacticos: ArrayList<ErrorSintactico>?) : View(context) {

    private val margenSuperior = 100f
    private val margenIzquierdo = 50f
    private val margenEntreFilas = 60f
    private val anchoCelda = 600f // Ajusta según el tamaño deseado
    private val altoCelda = 60f // Ajusta según el tamaño deseado
    private val colorLinea = Color.GRAY
    private val paint = Paint().apply {
        color = Color.BLACK
        textSize = 40f
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Configuración de pintura para líneas
        val linePaint = Paint().apply {
            color = colorLinea
            strokeWidth = 2f // Grosor de las líneas de la cuadrícula
        }

        var yPosition = margenSuperior

        // Dibujar encabezado de errores léxicos
        canvas.drawText("Léxico", margenIzquierdo, yPosition + paint.textSize, paint)
        yPosition += altoCelda

        // Dibujar errores léxicos
        erroresLexicos?.forEach { error ->
            val texto = "Tipo: ${error.tipo}, Línea: ${error.linea}, Columna: ${error.columna}, Lexema: ${error.lexema}, Descripción: ${error.descripcion}"
            canvas.drawText(texto, margenIzquierdo, yPosition + paint.textSize, paint)
            yPosition += altoCelda

            // Dibujar línea horizontal
            canvas.drawLine(margenIzquierdo, yPosition, margenIzquierdo + anchoCelda, yPosition, linePaint)
        }

        // Añadir un espacio adicional antes de empezar a dibujar errores sintácticos
        yPosition += margenEntreFilas

        // Dibujar encabezado de errores sintácticos
        canvas.drawText("Sintáctico", margenIzquierdo, yPosition + paint.textSize, paint)
        yPosition += altoCelda

        // Dibujar errores sintácticos
        erroresSintacticos?.forEach { error ->
            val texto = "Tipo: ${error.tipo}, Línea: ${error.linea}, Columna: ${error.columna}, Lexema: ${error.lexema}, Descripción: ${error.descripcion}"
            canvas.drawText(texto, margenIzquierdo, yPosition + paint.textSize, paint)
            yPosition += altoCelda

            // Dibujar línea horizontal
            canvas.drawLine(margenIzquierdo, yPosition, margenIzquierdo + anchoCelda, yPosition, linePaint)
        }

        // Dibujar líneas verticales
        canvas.drawLine(margenIzquierdo, margenSuperior, margenIzquierdo, yPosition, linePaint)
        canvas.drawLine(margenIzquierdo + anchoCelda, margenSuperior, margenIzquierdo + anchoCelda, yPosition, linePaint)
    }
}
