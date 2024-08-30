package com.calculadora

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.errores.Errores
import com.vistaresultado.Resultado
import java.io.StringReader
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {

    private var entrada: EditText? = null
    private var botonEjecutar: Button? = null
    private var botonError: Button? = null
    private var tablaLexico: TableLayout? = null
    private var tablaSintactico: TableLayout? = null

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
        botonError = findViewById(R.id.erroresBoton)

        //Evento botón ejecutar
        botonEjecutar!!.setOnClickListener(View.OnClickListener {
            try {
                if (!entrada!!.getText().toString().isEmpty()) {
                    val textoIngresado = entrada!!.getText().toString()
                    val reader = StringReader(textoIngresado)

                    val lexer: Lexer = Lexer(reader)
                    val parser: Parser = Parser(lexer)
                    parser.parse()
                    println("Fin de compilación")
                    enviarResultado(parser.getResultado())
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Escriba una operación aritmética",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: Exception) {
                println("Error irrecuperable")
                AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Ha ocurrido un error\nPresione el botón \"Errores\" para más\ninformación.")
                    .setPositiveButton("OK", null)
                    .show()
                e.printStackTrace()
                //Logger.getLogger(MainActivity::class.java.name).log(Level.SEVERE, null, e)
            }
        })

        //Evento botón errores
        botonError!!.setOnClickListener(View.OnClickListener {
            try {
                if (Lexer.errores.isNotEmpty() || Parser.errorSintactico.isNotEmpty()) {

                    val intent = Intent(this, Errores::class.java)
                    intent.putParcelableArrayListExtra("erroresLexicos", Lexer.errores)
                    intent.putParcelableArrayListExtra("erroresSintacticos", Parser.errorSintactico)
                    startActivity(intent)

                    println("Entrando a errores")
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "No hay errores para mostrar",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
    }

    fun enviarResultado(resultado: Double) {
        val nuevaVentana = Intent(this, Resultado::class.java)
        nuevaVentana.putExtra("Resultado", resultado)
        startActivity(nuevaVentana)
    }
}