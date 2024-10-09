package com.example.pertemuan6

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker.OnDateChangedListener
import android.widget.EditText
import android.widget.Spinner
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pertemuan6.databinding.ActivityMainBinding
import java.util.Calendar
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            val monthList = resources.getStringArray(R.array.month)
            val btnSelectDate = findViewById<EditText>(R.id.datepicker)
            btnSelectDate.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    this,
                    { _, selectedYear, selectedMonth, selectedDay ->
                        val dateText = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                        btnSelectDate.setText(dateText)
                    },
                    year, month, day
                )
                datePickerDialog.show()
            }


            fun showTimePicker(view: View) {
                com.example.myapplication.dialog.TimePicker().show(supportFragmentManager, "datePicker")
            }
            override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                Toast.makeText(this, String.format(
                    Locale.getDefault(), "%02d:%02d", p1, p2),
                    Toast.LENGTH_SHORT).show()
            }


//            Kehadiran Dropdown=======================================
            val spinner = findViewById<Spinner>(R.id.kehadiran_spinner)
            val repeatOptions = arrayOf("Hadir Tepat Waktu", "Terlambat", "Izin")
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, kehadiranSpinner)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.setSelection(0)

//            Selected Kehadiran
            kehadiranSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }



        }
    }
}