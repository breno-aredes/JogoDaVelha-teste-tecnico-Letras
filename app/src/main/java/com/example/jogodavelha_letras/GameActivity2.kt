package com.example.jogodavelha_letras

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.example.jogodavelha_letras.databinding.ActivityGame2Binding
import com.example.jogodavelha_letras.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.util.Calendar
import java.text.SimpleDateFormat

class GameActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityGame2Binding
    private lateinit var player1: String
    private lateinit var player2: String
    private lateinit var tableSize: String
    private lateinit var botOrNot: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game2)

        binding = ActivityGame2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        player1 = SecurityPreferences(this).getString("player1")
        player2 = SecurityPreferences(this).getString("player2")
        botOrNot = SecurityPreferences(this).getString("botOrNot")
        binding.userName.text = player1

        tableSize = SecurityPreferences(this).getString("tableSize")




        val size = tableSize.toInt()

        val tableLayout = binding.game

        for (i in 0 until size) {
            val row = layoutInflater.inflate(R.layout.row_layout, null) as TableRow
            for (j in 0 until size) {
                val cellLayout = layoutInflater.inflate(R.layout.cell_layout, null) as TextView
                val cellID = "$i$j"
                cellLayout.id = resources.getIdentifier(cellID, "id", packageName)

                if (size > 7) {
                    cellLayout.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30f)
                }
                row.addView(cellLayout)
            }
            tableLayout.addView(row)
        }

    }

    private var playerTime = 1
    private val player1Moves = mutableListOf<View>()
    private val player2Moves = mutableListOf<View>()
    private var gameEnded = false

    fun onCellClick(view: View) {
        if (view is TextView) {
            if (playerTime == 1) {
                view.text = "X"
                view.setTextColor(Color.RED)
                binding.userName.text = player2
                player1Moves.add(view)
                playerTime = 2
                checkWin()
                if (botOrNot == "1" && !gameEnded) {
                    botPlays()
                }
            } else if (playerTime == 2) {
                view.text = "O"
                view.setTextColor(Color.BLUE)
                player2Moves.add(view)
                binding.userName.text = player1
                playerTime = 1
                checkWin()
            }

            view.isClickable = false
        }
    }

    private fun botPlays() {

        val allCells = mutableListOf<View>()
        val tableLayout = binding.game
        for (i in 0 until tableLayout.childCount) {
            val row = tableLayout.getChildAt(i) as TableRow
            for (j in 0 until row.childCount) {
                val cell = row.getChildAt(j)
                allCells.add(cell)
            }
        }

        allCells.removeAll(player1Moves)
        allCells.removeAll(player2Moves)

        if (allCells.isNotEmpty()) {
            val randomCell = allCells.random()
            if (randomCell is TextView) {
                randomCell.text = "O"
                randomCell.setTextColor(Color.BLUE)
                player2Moves.add(randomCell)
                randomCell.isClickable = false
                binding.userName.text = player1
                playerTime = 1
                checkWin()
            }

        }
    }

    fun checkIfSameRow(playerMoves: List<View>): Boolean {
        if (playerMoves.size < 3) {
            return false
        }
        val playerMoveIds = playerMoves.map { it.id }

        val size = tableSize.toInt()
        for (i in 0 until size) {
            val testRow = mutableListOf<Int>()
            for (j in 0 until size) {
                val cellID = "$i$j"
                testRow.add(cellID.toInt())
            }
            if (playerMoveIds.containsAll(testRow)) {
                return true
            }
            testRow.clear()
        }
        return false
    }

    fun checkIfSameColumn(playerMoves: List<View>): Boolean {
        if (playerMoves.size < 3) {
            return false
        }
        val playerMoveIds = playerMoves.map { it.id }

        val size = tableSize.toInt()
        for (j in 0 until size) {
            val testColumn = mutableListOf<Int>()
            for (i in 0 until size) {
                val cellID = "$i$j"
                testColumn.add(cellID.toInt())
            }
            if (playerMoveIds.containsAll(testColumn)) {
                return true
            }
            testColumn.clear()
        }
        return false
    }

    fun checkIfSameDiagonal(playerMoves: List<View>): Boolean {
        if (playerMoves.size < 3) {
            return false
        }
        val playerMoveIds = playerMoves.map { it.id }

        val size = tableSize.toInt()

        val testMainDiagonal = mutableListOf<Int>()
        val testSecondaryDiagonal = mutableListOf<Int>()

        for (i in 0 until size) {
            val mainDiagonalCellID = "$i$i"
            val secondaryDiagonalCellID = "$i${size - 1 - i}"

            testMainDiagonal.add(mainDiagonalCellID.toInt())
            testSecondaryDiagonal.add(secondaryDiagonalCellID.toInt())
        }

        return playerMoveIds.containsAll(testMainDiagonal) || playerMoveIds.containsAll(
            testSecondaryDiagonal
        )
    }


    @SuppressLint("SetTextI18n")
    private fun checkWin() {
        val player1WinRow = checkIfSameRow(player1Moves)
        val player1WinCollumn = checkIfSameColumn(player1Moves)
        val player2WinRow = checkIfSameRow(player2Moves)
        val player2WinCollumn = checkIfSameColumn(player2Moves)
        val player1WinDiagonal = checkIfSameDiagonal(player1Moves)
        val player2WinDIagonal = checkIfSameDiagonal(player2Moves)

        if (player1WinRow || player1WinCollumn || player1WinDiagonal) {
            disableAllCells()
            binding.userName.text = "$player1"
            binding.headerText.text = "Vencedor"

            gameEnded = true
            winerButtons()
        }
        if (player2WinRow || player2WinCollumn || player2WinDIagonal) {
            disableAllCells()
            binding.userName.text = "$player2"

            binding.headerText.text = "Vencedor"
            gameEnded = true
            winerButtons()
        }


    }

    private fun winerButtons() {
        binding.winnerButtons.addView(layoutInflater.inflate(R.layout.button1, null) as Button)
        binding.winnerButtons.addView(layoutInflater.inflate(R.layout.button2, null) as Button)
    }

    private fun disableAllCells() {


        val tableLayout = binding.game
        for (i in 0 until tableLayout.childCount) {
            val rowLayout = tableLayout.getChildAt(i) as TableRow
            for (j in 0 until rowLayout.childCount) {
                val cell = rowLayout.getChildAt(j)
                cell.isClickable = false
            }
        }
    }

    fun newGame(v: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun restartGame(v: View) {

        val tableLayout = binding.game
        for (i in 0 until tableLayout.childCount) {
            val rowLayout = tableLayout.getChildAt(i) as TableRow
            for (j in 0 until rowLayout.childCount) {
                val cell = rowLayout.getChildAt(j) as TextView
                cell.text = ""
                cell.isClickable = true
            }
        }
        player1Moves.clear()
        player2Moves.clear()

        playerTime = 1
        binding.userName.text = player1

        gameEnded = false
        binding.headerText.text = "Vez do Jogador"
        binding.winnerButtons.removeAllViews()

    }

    data class GameResult(
        val date: String,
        val time: String,
        val winnerName: String,
        val loserName: String
    )



}