package com.errores

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.calculadora.ErrorLexico
import com.calculadora.ErrorSintactico
import com.calculadora.Lexer
import com.calculadora.Parser
import com.calculadora.R

class Errores : AppCompatActivity() {

    private var tablaLexico: TableLayout? = null
    private var tablaSintactico: TableLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_errores)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tablaLexico = findViewById(R.id.erroresLexicosTabla)
        tablaSintactico = findViewById(R.id.erroresSintacticosTabla)

        val erroresLexicos: ArrayList<ErrorLexico>? = intent.getParcelableArrayListExtra("erroresLexicos")
        val erroresSintacticos: ArrayList<ErrorSintactico>? = intent.getParcelableArrayListExtra("erroresSintacticos")

        erroresLexicos?.let { llenarTablaLexicos(it) }
        erroresSintacticos?.let { llenarTablaSintacticos(it) }
    }

    private fun llenarTablaLexicos(erroresLexicos: ArrayList<ErrorLexico>) {
        tablaLexico?.removeAllViews()

        // Agregar cabeceras a la tabla
        val cabecera = TableRow(this)
        cabecera.addView(createTextView("Tipo"))
        cabecera.addView(createTextView("Línea"))
        cabecera.addView(createTextView("Columna"))
        cabecera.addView(createTextView("Lexema"))
        cabecera.addView(createTextView("Descripción"))
        tablaLexico?.addView(cabecera)

        // Añadir los datos de errores léxicos a la tabla
        for (error in erroresLexicos) {
            val fila = TableRow(this)

            fila.addView(createTextView(error.tipo))
            fila.addView(createTextView(error.linea.toString()))
            fila.addView(createTextView(error.columna.toString()))
            fila.addView(createTextView(error.lexema))
            fila.addView(createTextView(error.descripcion))

            tablaLexico?.addView(fila)
        }
        Lexer.errores.clear()
    }



    private fun llenarTablaSintacticos(erroresSintacticos: ArrayList<ErrorSintactico>) {
        // Limpiar la tabla antes de llenarla
        tablaSintactico?.removeAllViews()

        // Agregar cabeceras a la tabla
        val cabecera = TableRow(this)
        cabecera.addView(createTextView("Tipo"))
        cabecera.addView(createTextView("Línea"))
        cabecera.addView(createTextView("Columna"))
        cabecera.addView(createTextView("Lexema"))
        cabecera.addView(createTextView("Descripción"))
        tablaSintactico?.addView(cabecera)

        // Añadir los datos de errores sintácticos a la tabla
        for (error in erroresSintacticos) {
            val fila = TableRow(this)

            fila.addView(createTextView(error.tipo))
            fila.addView(createTextView(error.linea.toString()))
            fila.addView(createTextView(error.columna.toString()))
            fila.addView(createTextView(error.lexema))
            fila.addView(createTextView(error.descripcion))

            tablaSintactico?.addView(fila)
        }

        Parser.errorSintactico.clear()
    }

    private fun createTextView(text: String?): TextView {
        val textView = TextView(this)
        textView.text = text ?: ""
        textView.setPadding(16, 16, 16, 16)
        textView.layoutParams = TableRow.LayoutParams(
            0,
            TableRow.LayoutParams.WRAP_CONTENT,
            1f
        )
        return textView
    }


}