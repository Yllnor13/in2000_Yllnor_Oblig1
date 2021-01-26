package com.example.yllnor_oblig1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import java.math.RoundingMode

class ConverterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val brukerInput = findViewById<EditText>(R.id.brukerInputTall)
        val konvert = findViewById<Button>(R.id.konvert)
        val text2 = findViewById<TextView>(R.id.textView2)
        val units = resources.getStringArray(R.array.units)
        val nesteActivity = findViewById<Button>(R.id.button2)
        var unit = ""
        brukerInput.setHint("skriv her") //her er hintet

        ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item). also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                unit = units.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                text2.text = "velg noe"
            }
        }


        konvert.setOnClickListener{
            if(brukerInput.text.toString().isNotEmpty() && brukerInput.text.toString().toDoubleOrNull() != null){
                var tall = brukerInput.text.toString().toDouble()

                if(unit == "gallon"){
                    var toText = tall * 3.78541
                    val rounded = toText.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
                    text2.text = rounded.toString() + " liters"
                } else if(unit == "fluid ounce"){
                    var toText = tall * 0.02957
                    val rounded = toText.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
                    text2.text = rounded.toString() + " liters"
                } else if(unit == "cup"){
                    var toText = tall * 0.23659
                    val rounded = toText.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
                    text2.text = rounded.toString() + " liters"
                } else if(unit == "hogshead"){
                    var toText = tall * 238.481
                    val rounded = toText.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
                    text2.text = rounded.toString() + " liters"
                }
            }else{
                Toast.makeText(applicationContext,"Du skrev inn noe feil ellers saa var inputet tomt",Toast.LENGTH_SHORT).show()
            }

            brukerInput.setText(null) //slik at man kan vise hintet paa nytt igjen etter at brukeren har skrevet inn noe
            brukerInput.setHint("skriv her")
            brukerInput.onEditorAction(EditorInfo.IME_ACTION_DONE) //slaar av tastaturet
        }

        nesteActivity.setOnClickListener{
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}