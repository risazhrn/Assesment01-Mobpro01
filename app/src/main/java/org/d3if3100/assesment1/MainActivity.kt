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

        val items = arrayOf("Rupiah to USD", "Rupiah to Yen","Rupiah to Euro", "USD to Rupiah", "USD to Yen", "USD to Euro", "Yen to Rupiah", "Yen to USD", "Yen to Euro")
        val dropdown: AutoCompleteTextView = binding.materialSpinner
        val adapter = ArrayAdapter(this, R.layout.list_items, items)
        dropdown.setAdapter(adapter)

        dropdown.setOnItemClickListener { _, _, position, _ -> value = items[position] }

        binding.btnConvert.setOnClickListener {
            convertMoney()
        }
        binding.btnReset.setOnClickListener {
            reset()
        }
    }

    private fun reset() {
        binding.nominal.setText(R.string.empty)
        binding.resultConvert.setText(R.string.empty)
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
                formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
            }

            "Rupiah to Yen" ->{
                if (nominal.toDouble() < 100) {
                    Toast.makeText(this, R.string.min_rupiah, Toast.LENGTH_LONG).show()
                    return
                }
                hasil = nominal.toDouble() / 11_269.92
                formatCurrency = NumberFormat.getCurrencyInstance(Locale.JAPAN)
            }
            "Rupiah to Euro" -> {
                if (nominal.toDouble() < 100) {
                    Toast.makeText(this, R.string.min_rupiah, Toast.LENGTH_LONG).show()
                    return
                }
                hasil = nominal.toDouble() / 16_329.20
                formatCurrency = NumberFormat.getCurrencyInstance(Locale("eu", "EU"))
            }
            "USD to Rupiah" -> {
                if (nominal.toDouble() < 1) {
                    Toast.makeText(this, R.string.min_dollar, Toast.LENGTH_LONG).show()
                    return
                }
                hasil = nominal.toDouble() * 15_060
                formatCurrency = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
            }
            "USD to Euro" -> {
                if (nominal.toDouble() < 1) {
                    Toast.makeText(this, R.string.min_dollar, Toast.LENGTH_LONG).show()
                    return
                }
                hasil = nominal.toDouble() * 0.92
                formatCurrency = NumberFormat.getCurrencyInstance(Locale("eu", "EU"))
            }
            "USD to Yen" -> {
                if (nominal.toDouble() < 1) {
                    Toast.makeText(this, R.string.min_dollar, Toast.LENGTH_LONG).show()
                    return
                }
                hasil = nominal.toDouble() * 132.74
                formatCurrency = NumberFormat.getCurrencyInstance(Locale.JAPAN)
            }
            "Yen to Rupiah" -> {
                if (nominal.toDouble() < 100) {
                    Toast.makeText(this, R.string.min_yen, Toast.LENGTH_LONG).show()
                    return
                }
                hasil = nominal.toDouble() * 112.66
                formatCurrency = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
            }
            "Yen to USD" -> {
                if (nominal.toDouble() < 100) {
                    Toast.makeText(this, R.string.min_yen, Toast.LENGTH_LONG).show()
                    return
                }
                hasil = nominal.toDouble() / 132.74
                formatCurrency = NumberFormat.getCurrencyInstance(Locale("en", "US"))
            }
            "Yen to Euro" -> {
                if (nominal.toDouble() < 100) {
                    Toast.makeText(this, R.string.min_yen, Toast.LENGTH_LONG).show()
                    return
                }
                hasil = nominal.toDouble() / 144.86
                formatCurrency = NumberFormat.getCurrencyInstance(Locale("eu", "EU"))
            }

            else -> hasil = 0.0
        }

        if (formatCurrency != null) {
            binding.resultConvert.text =
                getString(R.string.hasil_convert, formatCurrency.format(hasil))
        }
    }
}