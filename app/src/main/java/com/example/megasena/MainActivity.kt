package com.example.megasena

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var preferencias: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val text_input: EditText = findViewById(R.id.txt_input)
        val text_game: TextView = findViewById(R.id.txt_game)
        val button_game : Button = findViewById(R.id.btt_game)

        //banco de dados
        preferencias = getSharedPreferences("banco de dados", MODE_PRIVATE)
        val resultado = preferencias.getString("chave1", null)
        if(resultado != null){
            text_game.text = "Ultimo jogo: $resultado"
        }

        button_game.setOnClickListener{
            game_start(text_input, text_game)
        }
    }

    private fun game_start(textInput: EditText, textGame: TextView) {
        val txtInput = textInput.text.toString()

        if (txtInput.isEmpty()){
            Toast.makeText(this, "Digite um número entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }

        if (txtInput.toInt() < 6 || txtInput.toInt() > 15){
            Toast.makeText(this, "Digite um número entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }

        val list_game = mutableListOf<Int>()
        while (true){
            val random = Random

            if (list_game.size < txtInput.toInt()){
                val random_number = random.nextInt(59)
                list_game.add(random_number + 1)
            }  else {
                break
            }
        }
        textGame.text = list_game.joinToString(" - ")

        val editor = preferencias.edit()
        editor.putString("chave1", textGame.text.toString())
        editor.apply()

    }


}