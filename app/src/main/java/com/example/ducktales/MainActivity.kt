package com.example.ducktales

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.ResponseBody


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var btnEnter : Button = findViewById(R.id.btnEnter)
        btnEnter.setOnClickListener ()
        {
            val postId: EditText = findViewById(R.id.txtEmail)
            val email = postId.text.toString()

            if (email.isNotEmpty()) {
                sendEmail(email)
            } else {
                Toast.makeText(this, "Please enter an email", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendEmail(email: String)
    {
        val newemail = email.replace("@", "%40")
        val url = "https://duckiecool.azurewebsites.net/sendDuckie?useremail=${newemail}"

        try {
            val call = RetrofitClient.instance.sendEmail(url)
            Log.v("words", call!!.request().toString());

            call.enqueue(object: retrofit2.Callback<ResponseBody?>{
                override fun onResponse(
                    call: retrofit2.Call<ResponseBody?>,
                    response: retrofit2.Response<ResponseBody?>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@MainActivity, "Email sent successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MainActivity, HiDucks::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@MainActivity, "Failed to send email", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: retrofit2.Call<ResponseBody?>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            }
            )
        }
        catch (e:Exception){
            Toast.makeText(this@MainActivity, "invalid email error", Toast.LENGTH_SHORT).show()
        }
    }
}