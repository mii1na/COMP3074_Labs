package ca.gbc.comp3074.fahim_mina.labex1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val label = findViewById<TextView>(R.id.textViewMessage)
        val btn = findViewById<Button>(R.id.button)
        val values = resources.getStringArray(R.array.values)

        label.text = values[i]

        btn.setOnClickListener {
            i = (i + 1) % values.size
            label.text = values[i]
            btn.setBackgroundColor(getColor(R.color.green))
        }
    }
}