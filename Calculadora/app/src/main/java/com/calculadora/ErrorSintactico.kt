package com.calculadora

class ErrorSintactico(
    val tipo: String?,
    val linea: Int,
    val columna: Int,
    val lexema: String?,
    val descripcion: String?
) {

}