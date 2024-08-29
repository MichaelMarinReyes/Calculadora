package com.calculadora

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.StringReader
import java.security.NoSuchAlgorithmException
import com.vistaresultado.Resultado
import java.util.logging.Level
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    private var entrada: EditText? = null
    private var botonEjecutar: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Recibir datos
        entrada = findViewById(R.id.entradaTexto)
        botonEjecutar = findViewById(R.id.calcularBoton)

        //Evento botón
        botonEjecutar!!.setOnClickListener(View.OnClickListener {
            try {
                val textoIngresado = entrada!!.getText().toString()
                val reader = StringReader(textoIngresado)

                val lexer: Lexer = Lexer(reader)
                val parser: Parser = Parser(lexer)
                parser.parse()
                println("Fin de compilación")
                enviarResultado(parser.getResultado())
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: Exception) {
                println("Error irrecuperable")
                e.printStackTrace()
            //Logger.getLogger(MainActivity::class.java.name).log(Level.SEVERE, null, e)
            }
        })
    }

    fun enviarResultado(resultado: Double) {
        val nuevaVentana = Intent(this, Resultado::class.java)

        nuevaVentana.putExtra("Resultado", resultado)
        startActivity(nuevaVentana)
    }
}