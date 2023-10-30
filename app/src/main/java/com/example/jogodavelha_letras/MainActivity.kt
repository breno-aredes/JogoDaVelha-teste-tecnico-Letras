package com.example.jogodavelha_letras


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.core.content.ContextCompat
import com.example.jogodavelha_letras.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var nPlayers = 2
    private var tableSize = "3"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun selectVsPlayer(view: View) {
        val btnotsel = findViewById<Button>(R.id.vsBot)
        val btsel = findViewById<Button>(R.id.vsPlayer)
        btsel.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        btnotsel.setBackgroundColor(ContextCompat.getColor(this, R.color.lgray))

        val player2 = findViewById<EditText>(R.id.player2)
        player2.visibility = View.VISIBLE
        nPlayers = 2

    }

    fun selectVsBot(view: View) {
        val btnotsel = findViewById<Button>(R.id.vsBot)
        val btsel = findViewById<Button>(R.id.vsPlayer)
        btnotsel.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        btsel.setBackgroundColor(ContextCompat.getColor(this, R.color.lgray))

        val player2 = findViewById<EditText>(R.id.player2)
        player2.visibility = View.GONE
        nPlayers = 1


    }

    fun selectedSize(view: View) {
        val allButtons = listOf<Button>(
            findViewById(R.id.x3),
            findViewById(R.id.x4),
            findViewById(R.id.x5),
            findViewById(R.id.x6),
            findViewById(R.id.x7),
            findViewById(R.id.x8),
            findViewById(R.id.x9),
            findViewById(R.id.x10)
        )

        for (button in allButtons) {
            if (button.id == view.id) {
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                tableSize = button.tag.toString()

            } else {
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.lgray))
            }
        }
    }

    fun startTheGame(v: View) {
        val name = binding.player1.text.toString()
        var name2 = "Robô"
        var emptyName = "Informe o nome"
        if (nPlayers != 1) {
            name2 = binding.player2.text.toString()
            emptyName = "preencha ambos os nomes"
        }

        if (name != "" || name2 != "") {
            SecurityPreferences(this).storeString("player1",name)
            SecurityPreferences(this).storeString("player2",name2)
            SecurityPreferences(this).storeString("tableSize",tableSize)
            SecurityPreferences(this).storeString("botOrNot",nPlayers.toString())


            startActivity(Intent(this, GameActivity2::class.java) )
            finish()

        } else {
            Toast.makeText(this, "$emptyName", Toast.LENGTH_SHORT).show()
        }

    }

    fun hitoric (v: View) {
        startActivity(Intent(this, Historic::class.java) )
    }

}