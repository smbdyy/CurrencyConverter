package com.hseproject.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    val toRub = mapOf<String, Double>(
        "RUR" to 1.0,
        "AUD" to 47.4678,
        "AZN" to 40.4801,
        "GBP" to 86.9007,
        "AMD" to 0.1427,
        "BYN" to 28.8682,
        "BGN" to 39.5249,
        "BRL" to 14.239,
        "HUF" to 0.2243,
        "HKD" to 8.8611,
        "DKK" to 10.3686,
        "USD" to 68.6745,
        "EUR" to 77.3481,
        "INR" to 0.9082,
        "KZT" to 0.1717,
        "CAD" to 50.9909,
        "KGS" to 0.9252,
        "CNY" to 9.688,
        "MDL" to 3.9869,
        "NOK" to 7.3105,
        "PLN" to 17.4058,
        "RON" to 15.9779,
        "XDR" to 94.7481,
        "SGD" to 49.3068,
        "TJS" to 6.6755,
        "TRY" to 10.1109,
        "TMT" to 19.6494,
        "UZS" to 0.0068,
        "UAH" to 2.5776,
        "CZK" to 2.8961,
        "SEK" to 7.3994,
        "CHF" to 71.8954,
        "ZAR" to 4.0769,
        "KRW" to 0.0571,
        "JPY" to 0.6362
    )

    val curs = arrayOf<String> (
        "RUR", "AUD", "AZN", "GBP", "AMD",
        "BYN", "BGN", "BRL", "HUF", "HKD",
        "DKK", "USD", "EUR", "INR", "KZT",
        "CAD", "KGS", "CNY", "MDL", "NOK",
        "PLN", "RON", "XDR", "SGD", "TJS",
        "TRY", "TMT", "UZS", "UAH", "CZK",
        "SEK", "CHF", "ZAR", "KRW", "JPY"
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cur_from = "RUR"
        var cur_to = "RUR"

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, curs)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        currency_from.adapter = adapter
        currency_from.prompt = "Из"
        currency_from.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                cur_from = parent!!.getItemAtPosition(pos) as String
                to_amount.setText("")
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

        currency_to.adapter = adapter
        currency_to.prompt = "В"
        currency_to.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                cur_to = parent!!.getItemAtPosition(pos) as String
                to_amount.setText("")
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

        convert.setOnClickListener {
            var am_from_str = from_amount.text.toString()
            var am_from = 0.0
            if (am_from_str != "") {
                am_from = am_from_str.toDouble()
            }
            var in_rub = am_from*toRub[cur_from]!!
            var in_to = in_rub/toRub[cur_to]!!
            in_to = round(in_to*100)/100
            to_amount.setText(in_to.toString())
        }


    }
}