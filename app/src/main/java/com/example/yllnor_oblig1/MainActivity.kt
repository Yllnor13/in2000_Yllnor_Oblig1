package com.example.yllnor_oblig1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val knappEn = findViewById<Button>(R.id.knappEn) //finner fram elementene jeg trenger
        val resultat = findViewById<TextView>(R.id.resultat)
        val brukerInput = findViewById<EditText>(R.id.brukerInput)

        brukerInput.setHint("skriv her") //her er hintet

        knappEn.setOnClickListener{ //hva knappen skal gjoerre
            var brukerText = brukerInput.text.toString() //gjoer det brukeren skriver om til string
            val ikkeCase = brukerText.toLowerCase() //gjoer det om til lowercase slik at man kan sammenligne ting uten problem
            var palindrom = ikkeCase.reversed() //reverserer teksten for aa sammenligne
            resultat.text = if(palindrom == ikkeCase){ //dersom det er det samme forlengs og baklengs
                brukerText + " er et palindrom" //si til brukeren at det var palindrom (og ha med det de originalt skrev dersom de hadde uppercase)
            } else{
                brukerText + " er ikke et palindrom" //dersom de ikke hadde uppercase
            }
            brukerInput.setText(null) //slik at man kan vise hintet paa nytt igjen etter at brukeren har skrevet inn noe
            brukerInput.setHint("skriv her")
            brukerInput.onEditorAction(EditorInfo.IME_ACTION_DONE) //slaar av tastaturet
        }

        val nesteActivity = findViewById<Button>(R.id.knappTo) //knapp for neste activity blir registrert

        nesteActivity.setOnClickListener{
            val intent = Intent(this, ConverterActivity::class.java)
            startActivity(intent)  //starter neste activity
        }

    }
}