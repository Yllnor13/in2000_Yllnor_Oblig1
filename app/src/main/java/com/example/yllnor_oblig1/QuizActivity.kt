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
        var poeng = 0 //hvor mange poeng de faar
        var spmpos = 0 //vite hvor langt i quizzen de er i

        var spoersmaal = mutableListOf<Question>(question1, question2, question3, question4, question5, question6) //var i tilfelle vi vil legge til funksjonalitet som legger til flere spoersmal
        //Kunne ha latt det bli val, men jeg har en snikende antagelse om at dette er mutablelist istedenfor immutable list av en grunn som vi skal komme tilbake og bruke den.
        //dette har da paavirket litt av designet senere i koden, men ikke noe som faar den til aa ikke gjoere det oppgaven ber om
        var naaspm = spoersmaal[0] //global variabel for hvilket spoersmal som quizzen er i

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
            if(spmpos < (spoersmaal.size-1)){ //gaar gjennom alle spoersmalene
                if (!naaspm.returnBoolean()) { //om det de svarte var riktig, saa oeker poeng og den drar til neste spm
                    poeng++
                    spmpos++
                    naaspm = spoersmaal[spmpos]
                    resultat.text = "RIKTIG \n du har naa $poeng poeng"
                    spersmaal.text = naaspm.toString()
                } else { //dersom de svarer feil saa vil den til neste spoersmaal
                    resultat.text = "FEIL \n du har fortsatt $poeng poeng"
                    spmpos++
                    naaspm = spoersmaal[spmpos]
                    spersmaal.text = naaspm.toString()
                }
            } else {//naar man er paa siste spoersmal, slik at den ikke faar nullpointer error
                if (!naaspm.returnBoolean() && spmpos < spoersmaal.size) {//om det de svarte var riktig
                    poeng++
                }
                spmpos = spoersmaal.size //gjoer det slik at selv om de klikker paa knappen igjen mens den er usynlig/gjennomsiktig saa far de ikke poeng
                fakta.background.alpha = 70//knapper blir gjennomsiktige
                fleip.background.alpha = 70
                resultat.text = "du er naa ferdig med quizzen \n du fikk $poeng poeng til sammen ut av " + spoersmaal.size + "  poeng"
            //har det slikt i tilfelle vi skal legge til flere spoersmal senere. da behoever man ikke aa matte endre paa det hver gang om man hardkoded tallet for antall mulige poeng. mindre teknisk gjeld
            }
        }

        fakta.setOnClickListener{ //gjoer det samme som knappen over, bare om brukeren svarte det motsatte
            if(spmpos < (spoersmaal.size-1)){
                if (naaspm.returnBoolean()) {
                    poeng++
                    spmpos++
                    naaspm = spoersmaal[spmpos]
                    resultat.text = "RIKTIG \n du har naa $poeng poeng"
                    spersmaal.text = naaspm.toString()
                } else {
                    resultat.text = "FEIL \n du har fortsatt $poeng poeng"
                    spmpos++
                    naaspm = spoersmaal[spmpos]
                    spersmaal.text = naaspm.toString()
                }
            } else {
                if (naaspm.returnBoolean() && spmpos < spoersmaal.size) {
                    poeng++
                }
                spmpos = spoersmaal.size
                fakta.background.alpha = 70
                fleip.background.alpha = 70
                resultat.text = "du er naa ferdig med quizzen \n du fikk $poeng poeng til sammen ut av " + spoersmaal.size + "  poeng"
            }
        }

        restart.setOnClickListener{ //resetter ting til som det var paa starten av aktiviteten
            fakta.background.alpha = 255
            fleip.background.alpha = 255
            resultat.text = "du valgte aa restarte \n du har naa 0 poeng"
            poeng = 0
            spmpos = 0
            naaspm = spoersmaal[0]
            spersmaal.text = naaspm.toString()
        }
    }
}