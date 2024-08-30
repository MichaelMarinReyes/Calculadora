package com.calculadora

import android.os.Parcel
import android.os.Parcelable

class ErrorSintactico(
    val tipo: String?,
    val linea: Int,
    val columna: Int,
    val lexema: String?,
    val descripcion: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(tipo)
        parcel.writeInt(linea)
        parcel.writeInt(columna)
        parcel.writeString(lexema)
        parcel.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ErrorSintactico> {
        override fun createFromParcel(parcel: Parcel): ErrorSintactico {
            return ErrorSintactico(parcel)
        }

        override fun newArray(size: Int): Array<ErrorSintactico?> {
            return arrayOfNulls(size)
        }
    }
}
