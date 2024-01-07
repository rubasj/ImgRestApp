package cz.utb.fai.imgrestapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import cz.utb.fai.imgrestapp.MyApplication
import cz.utb.fai.imgrestapp.R
import cz.utb.fai.imgrestapp.api.ApodRequestDto
import cz.utb.fai.imgrestapp.databinding.ActivityApodinfoBinding
import cz.utb.fai.imgrestapp.model.ApodInfoViewModel
import cz.utb.fai.imgrestapp.model.ApodInfoViewModelFactory

class ApodFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityApodinfoBinding


    private lateinit var calendarView: CalendarView
    private lateinit var dateText: EditText

    private lateinit var submitButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apodinfo)

        binding = ActivityApodinfoBinding.inflate(layoutInflater)
        val view  = binding.root

        setContentView(view)


        calendarView = findViewById(R.id.calendarView)
        dateText = findViewById(R.id.dateText)
        submitButton = findViewById(R.id.buttonSubmit)

        // Set a listener to the CalendarView to capture date changes
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // Update the TextView with the selected date

            val formattedDayOfMonth = String.format("%02d", dayOfMonth)
            val formattedMonth = String.format("%02d", month +1)
            val selectedDate = "$year-${formattedMonth}-$formattedDayOfMonth"
            dateText.setText(selectedDate)
        }

        submitButton.setOnClickListener {
            // Get the text from the EditText
            val submittedDate = dateText.text.toString()

            // Create a new Intent to start the next activity
            val intent = Intent(this, ApodViewActivity::class.java)

            // Pass the submitted date to the next activity using Intent
            intent.putExtra("DATE_", submittedDate)

            // Start the new activity
            startActivity(intent)
        }

    }
}


