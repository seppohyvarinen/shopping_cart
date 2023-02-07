package fi.tuni.shoppingcart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import fi.tuni.myapplication.R

lateinit var header : TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        header = findViewById(R.id.header)
    }
}