package com.example.autointernetconnection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    lateinit var status: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        status = findViewById(R.id.status)

        findViewById<Button>(R.id.button).setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        // if you want observe network status then use this way
        NetWorkManger.networkStatus.observe(this, Observer {
            when (it) {
                CONNECTED -> {
                    status.text = "Internet is connected"
                    Log.d(
                        "MainActivity",
                        "Internet is connected " + !NetWorkManger.isDisconnected()
                    )
                }
                DISCONNECTED -> {
                    status.text = "Internet disconnected"
                    Log.d("MainActivity", "Internet disconnected" + !NetWorkManger.isDisconnected())
                }
            }
        })
    }
}