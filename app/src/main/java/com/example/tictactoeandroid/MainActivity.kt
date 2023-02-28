package com.example.tictactoeandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.tictactoeandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    enum class Turn {
        O,
        X
    }

    private var firstTurn = Turn.X
    private var currentTurn = Turn.X
    private var boardList = mutableListOf<Button>()


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
    }

    private fun initBoard() {
        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)
        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)
        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)
    }

    fun boardTapped(view: View) {
        if (view !is Button)
            return
        addToBoard(view)

        if(fullBoard()) {
            result("Draw")
        }
    }

    private fun resetBoard() {
        for(button in boardList) {
            button.text = ""
        }
        if (firstTurn == Turn.O)
            firstTurn = Turn.X
        else if (firstTurn == Turn.X)
            firstTurn == Turn.O

        currentTurn = firstTurn
        setTurnLabel()
    }

    private fun result(title: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setPositiveButton("New Game") {
                _,_ ->
                resetBoard()
            }
            .setCancelable(false)
            .show()
    }

    private fun fullBoard(): Boolean {
        for(button in boardList) {
            if(button.text == "")
                return false
        }
        return true
    }

    private fun addToBoard(button: Button) {
        if(button.text != "")
            return

        if(currentTurn == Turn.O) {
            button.text= "O"
            currentTurn = Turn.X
        }

        else if(currentTurn == Turn.X) {
            button.text = "X"
            currentTurn = Turn.O
        }

        setTurnLabel()
    }

    private fun setTurnLabel() {
        var turnText = ""

        if(currentTurn == Turn.X)
            turnText = "Turn $X"

        else if (currentTurn == Turn.O)
            turnText = "Turn $O"

        binding.turnTV.text = turnText
    }

    companion object {
        const val O = "O"
        const val X = "X"
    }
}