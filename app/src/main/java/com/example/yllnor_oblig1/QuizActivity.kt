package com.example.yllnor_oblig1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        //spoersmaal blir laget og liste for dem blir laget ogsaa + andre variabler som trengs
        val question1 = Question("iphonen er laget av selskapet Apple", true)
        val question2 = Question("Google sitt mobiloperativsystem heter Ios", false)
        val question3 = Question("bestaar denne innleveringen obliggen?", true)
        val question4 = Question("Android studio er bygt paa Microsofts Visual Studio code program", false)
        val question5 = Question("er 2 + 2 = 4?", true)
        val question6 = Question("UiO aapnet i 1981", false)
        var poeng = 0
        var spmpos = 0

        var spoersmaal = mutableListOf<Question>(question1, question2, question3, question4, question5, question6)
        var naaspm = spoersmaal[0]

        //deklarering av views
        val fakta = findViewById<Button>(R.id.buttonFakta)
        val fleip = findViewById<Button>(R.id.buttonFleip)
        val restart = findViewById<Button>(R.id.buttonRestart)

        val spersmaal = findViewById<TextView>(R.id.textSpoersmaal)
        val resultat = findViewById<TextView>(R.id.textPoeng)

        spersmaal.text = naaspm.toString()
        resultat.text = "du har 0 poeng saa langt"

        //funskjoner av knapper
        fleip.setOnClickListener {
            if(spmpos < 6){
                if (naaspm.returnBoolean() == false) {
                    poeng++
                    resultat.text = "RIKTIG \n du har naa $poeng poeng"
                    naaspm = spoersmaal[spmpos++]
                    spersmaal.text = naaspm.toString()
                } else {
                    resultat.text = "feil svar \n" + " du har fortsatt $poeng poeng"
                    naaspm = spoersmaal[spmpos++]
                    spersmaal.text = naaspm.toString()
                }
            } else {
                resultat.text = "du er naa ferdig med quizzen \n du fikk $poeng poeng til sammen"
            }
        }

        fakta.setOnClickListener{
            if(spmpos < 6){
                if (naaspm.returnBoolean() == true) {
                    poeng++
                    resultat.text = "RIKTIG \n du har naa $poeng poeng"
                    naaspm = spoersmaal[spmpos++]
                    spersmaal.text = naaspm.toString()
                } else {
                    resultat.text = "feil svar \n du har fortsatt $poeng poeng"
                    naaspm = spoersmaal[spmpos++]
                    spersmaal.text = naaspm.toString()
                }
            } else {
                resultat.text = "du er naa ferdig med quizzen \n du fikk $poeng poeng til sammen"
            }
        }

        restart.setOnClickListener{
            resultat.text = "du valgte aa restarte \n du har naa 0 poeng"
            poeng = 0
            spmpos = 0
            naaspm = spoersmaal[0]
            spersmaal.text = naaspm.toString()
        }
    }
}