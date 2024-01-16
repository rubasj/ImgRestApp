package cz.utb.fai.imgrestapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.material.snackbar.Snackbar
import cz.utb.fai.imgrestapp.R
import cz.utb.fai.imgrestapp.databinding.ActivityApodformBinding


class ApodFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityApodformBinding

    private lateinit var calendarView: CalendarView
    private lateinit var dateText: EditText

    private lateinit var submitButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_apodform)

        binding = ActivityApodformBinding.inflate(layoutInflater)
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



            if (dateText.text.toString() != null) {
                val intent = Intent(this, ApodViewActivity::class.java)

                intent.putExtra("date", dateText.text.toString())

                // Start the new activity
                startActivity(intent)
            }
            else {
                val rootView: View = findViewById(android.R.id.content)
                val snackbar = Snackbar.make(rootView, "Data from the service doesn't received.", Snackbar.LENGTH_LONG)
                snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorError))
                snackbar.show()
            }

        }


    }
}


