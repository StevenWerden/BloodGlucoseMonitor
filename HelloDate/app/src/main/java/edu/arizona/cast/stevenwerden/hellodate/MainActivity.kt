package edu.arizona.cast.stevenwerden.hellodate

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class MainActivity : AppCompatActivity() {

    private lateinit var dateSpot: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dateSpot = findViewById(R.id.date_spot)
        displayDate()

        dateSpot.setOnClickListener {
            displayDate()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun displayDate() {
        val dateTime = LocalDateTime.now()
        dateSpot.text = dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withZone(
            ZoneId.systemDefault()))
    }
}