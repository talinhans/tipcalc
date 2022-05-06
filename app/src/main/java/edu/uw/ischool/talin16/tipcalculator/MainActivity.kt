package edu.uw.ischool.talin16.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var tipPercentage = 0.10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.etAmount)
        val tipButton = findViewById<Button>(R.id.tipButton)
        val spinner = findViewById<Spinner>(R.id.tipSpinner)

        var adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.tips,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this

        var editTextAmount = 0.0
        var calculatedAmount = 0.0

        editText.setOnEditorActionListener { _, actionId, _ -> // this listeners gets triggered when user presses done button on keyboard
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                tipButton.isEnabled = true
                true
            }
            false
        }
        tipButton.setOnClickListener {
            editTextAmount = editText.text.toString().trim().toDouble()
            val tipAmount = tipPercentage * editTextAmount
            val roundedAmount = (tipAmount * 100.0).roundToInt() / 100.0
            calculatedAmount = roundedAmount
            Toast.makeText(baseContext, "$$calculatedAmount", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        var selectedTip = p0?.getItemAtPosition(p2).toString().trim().toInt()
        tipPercentage = 1.0 * selectedTip / 100
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}