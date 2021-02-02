package com.example.yllnor_oblig1

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import java.math.RoundingMode

class ConverterActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        val spinner = findViewById<Spinner>(R.id.spinner)  //legger til knapper og textviews
        val brukerInput = findViewById<EditText>(R.id.brukerInputTall)
        val konvert = findViewById<Button>(R.id.konvert)
        val text2 = findViewById<TextView>(R.id.textView2)
        val units = resources.getStringArray(R.array.units)
        val nesteActivity = findViewById<Button>(R.id.button2)
        var unit = ""  //dette skal vaere en global variabel som skal gjoere det slik at konverter knappen vet hva brukeren valgte paa spinenren
        brukerInput.hint = "skriv her" //her er hintet

        ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item). also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                unit = units[position] //gjoer unit om til uniten som er valgt
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { //hvis de ikke har valgt noe
                text2.text = "velg noe"
            }
        }


        konvert.setOnClickListener{
            if(brukerInput.text.toString().isNotEmpty() && brukerInput.text.toString().toDoubleOrNull() != null){ //sjekker om brukeren skrev noe og om det de skrev er double
                val tall = brukerInput.text.toString().toDouble() //gjoer det som er skrevet om til double

                /*if(unit == "gallon"){ //kalkulerer resultatet basert paa hva brukeren valgte
                    val toText = tall * 3.78541
                    val rounded = toText.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
                    text2.text = "$rounded liters"
                } else if(unit == "fluid ounce"){
                    val toText = tall * 0.02957
                    val rounded = toText.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
                    text2.text = "$rounded liters"
                } else if(unit == "cup"){
                    val toText = tall * 0.23659
                    val rounded = toText.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
                    text2.text = "$rounded liters"
                } else if(unit == "hogshead"){
                    val toText = tall * 238.481
                    val rounded = toText.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
                    text2.text = "$rounded liters"
                }*/
                when(unit){ //bytta til when her, men den gjoer akkurat det samme som iftestene over
                    "gallon" ->{
                        val toText = tall * 3.78541
                        val rounded = toText.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
                        text2.text = "$rounded liters"
                    }
                    "fluid ounce" ->{
                        val toText = tall * 0.02957
                        val rounded = toText.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
                        text2.text = "$rounded liters"
                    }
                    "cup" ->{
                        val toText = tall * 0.23659
                        val rounded = toText.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
                        text2.text = "$rounded liters"
                    }
                    "hogshead" ->{
                        val toText = tall * 238.481
                        val rounded = toText.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
                        text2.text = "$rounded liters"
                    }
                }
            }else{ //ellers aa faa error av aa ikke ha noe skrevet eller aa skrive bokstaver istedenfor tall saa faar de en toast
                Toast.makeText(applicationContext,"Du skrev inn noe feil ellers saa var inputet tomt",Toast.LENGTH_SHORT).show()
            }

            brukerInput.text = null //slik at man kan vise hintet paa nytt igjen etter at brukeren har skrevet inn noe
            brukerInput.hint = "skriv her"
            brukerInput.onEditorAction(EditorInfo.IME_ACTION_DONE) //slaar av tastaturet
        }

        nesteActivity.setOnClickListener{ //neste activity
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}