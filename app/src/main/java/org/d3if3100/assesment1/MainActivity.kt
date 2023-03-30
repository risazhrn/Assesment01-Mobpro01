package org.d3if3100.assesment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import org.d3if3100.assesment1.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var value = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = arrayOf("Rupiah to USD", "USD to Rupiah")
        val dropdown: AutoCompleteTextView = binding.materialSpinner
        val adapter = ArrayAdapter(this, R.layout.list_items, items)
        dropdown.setAdapter(adapter)

        dropdown.setOnItemClickListener { _, _, position, _ ->
            val selectedValue = items[position]

            value = if (selectedValue == "Rupiah to USD") {
                "Rupiah to USD"
            } else {
                "USD to Rupiah"
            }
        }

        binding.btnConvert.setOnClickListener {
            convertMoney()
        }
    }

    private fun convertMoney() {
        val dropdown = binding.materialSpinner.text.toString()
        if (TextUtils.isEmpty(dropdown)) {
            Toast.makeText(this, R.string.convert_type_null, Toast.LENGTH_LONG).show()
            return
        }
        val nominal = binding.nominal.text.toString()
        if (TextUtils.isEmpty(nominal)) {
            Toast.makeText(this, R.string.convert_null, Toast.LENGTH_LONG).show()
            return
        }

        var formatCurrency: NumberFormat? = null
        val hasil: Double
        when (value) {
            "Rupiah to USD" -> {
                if (nominal.toDouble() < 100) {
                    Toast.makeText(this, R.string.min_rupiah, Toast.LENGTH_LONG).show()
                    return
                }
                hasil = nominal.toDouble() / 15_060
                formatCurrency = NumberFormat.getCurrencyInstance(Locale("en", "US"))
            }
            "USD to Rupiah" -> {
                if (nominal.toDouble() < 1) {
                    Toast.makeText(this, R.string.min_dollar, Toast.LENGTH_LONG).show()
                    return
                }
                hasil = nominal.toDouble() * 15_060
                formatCurrency = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
            }
            else -> hasil = 0.0
        }

        if (formatCurrency != null) {
            binding.resultConvert.text =
                getString(R.string.hasil_convert, formatCurrency.format(hasil))
        }
    }
}