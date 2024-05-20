package com.example.ducktales

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class HiDucks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hi_ducks)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var btnQuack : Button = findViewById(R.id.btnQuack)
        var duck1 : ImageView = findViewById(R.id.duck1)
        var duck2 : ImageView = findViewById(R.id.duck2)
        var duck3 : ImageView = findViewById(R.id.duck3)
        var duck4 : ImageView = findViewById(R.id.duck4)
        var clickCounter = 0


        btnQuack.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                clickCounter = clickCounter + 1
                if (clickCounter == 1) {
                    duck1.visibility = View.VISIBLE

                } else if (clickCounter == 2 ) {
                    duck2.visibility = View.VISIBLE
                }
                else if (clickCounter == 3 ) {
                    duck3.visibility = View.VISIBLE
                }
                else if (clickCounter == 4 ) {
                    duck4.visibility = View.VISIBLE
                }
                else {
                    Toast.makeText(this@HiDucks, "Oh no! There's no more ducks", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}