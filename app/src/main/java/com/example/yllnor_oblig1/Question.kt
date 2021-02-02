package com.example.yllnor_oblig1

class Question(sp: String, sa: Boolean) { //har bare spoersmaalet og om det var sant eller usant
    val spoersmaal: String = sp
    val sant: Boolean = sa

    fun returnBoolean(): Boolean{ //returnerer ting som skal gjoere koden mulig aa kjoere i aktiviteten
        return sant
    }

    override fun toString(): String{
        return spoersmaal
    }
}