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
    private var scoreX = 0
    private var scoreO = 0


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

        if (checkedForWinner(O)) {
            scoreO++
            result("O Wins!")
        }
        else if (checkedForWinner(X)) {
            scoreX++
            result("X Wins!")
        }


        if(fullBoard()) {
            result("Draw")
        }
    }

    private fun checkedForWinner(s: String): Boolean {
        if(match(binding.a1,s) && match(binding.a2,s) && match(binding.a3,s))
            return true
        if(match(binding.b1,s) && match(binding.b2,s) && match(binding.b3,s))
            return true
        if(match(binding.c1,s) && match(binding.c2,s) && match(binding.c3,s))
            return true

        if(match(binding.a1,s) && match(binding.b1,s) && match(binding.c1,s))
            return true
        if(match(binding.a2,s) && match(binding.b2,s) && match(binding.c2,s))
            return true
        if(match(binding.a3,s) && match(binding.b3,s) && match(binding.c3,s))
            return true

        if(match(binding.a1,s) && match(binding.b2,s) && match(binding.c3,s))
            return true
        if(match(binding.a3,s) && match(binding.b2,s) && match(binding.c1,s))
            return true

        return false
    }


    private fun match(button: Button, symbol: String) = button.text == symbol

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
        val message = "\nTotal Wins for O: $scoreO \n\nTotal Wins for X: $scoreX"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
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