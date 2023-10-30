package com.example.jogodavelha_letras

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.jogodavelha_letras.databinding.ActivityGame2Binding
import com.example.jogodavelha_letras.databinding.ActivityHistoricBinding
import android.content.Context




class Historic : AppCompatActivity() {

    private lateinit var binding: ActivityHistoricBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game2)

        binding = ActivityHistoricBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}