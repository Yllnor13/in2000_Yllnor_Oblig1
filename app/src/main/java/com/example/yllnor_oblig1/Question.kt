package com.example.yllnor_oblig1

class Question(sp: String, sa: Boolean) {
    val spoersmaal: String = sp
    val sant: Boolean = sa

    fun returnBoolean(): Boolean{
        return sant
    }

    override fun toString(): String{
        return spoersmaal
    }
}