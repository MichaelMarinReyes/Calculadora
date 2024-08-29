package com.calculadora

class ErrorLexico(
    val tipo: String?,
    val linea: Int,
    val columna: Int,
    val lexema: String,
    val descripcion: String?
) {


}