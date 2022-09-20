package com.example.clickbiggernumber2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initGame()
    }

    private fun initGame() {
        resetBackgroundColor()
        setRandomNumbersToButtons()
        enableButtons()
    }

    private fun resetBackgroundColor() {
        backgroundView.setBackgroundColor(Color.WHITE)
    }

    private fun setRandomNumbersToButtons() {
        var rnd = (0..100).random()
        findViewById<TextView>(R.id.leftButtonView).text = "$rnd"
        rnd = (0..100).random()
        findViewById<TextView>(R.id.rightButtonView).text = "$rnd"
    }

    fun replayButtonClick(view: View) {
        initGame()
    }

    fun leftButtonClick(view: View) {
        val answerWasCorrect = checkAnswer(true)
        displayResult(answerWasCorrect)
        disableButtons()
    }

    private fun disableButtons() {
        leftButtonView.isEnabled = false
        rightButtonView.isEnabled = false
    }

    private fun enableButtons() {
        leftButtonView.isEnabled = true
        rightButtonView.isEnabled = true
    }

    fun rightButtonClick(view: View) {
        val answerWasCorrect = checkAnswer(false)
        displayResult(answerWasCorrect)
        disableButtons()
    }

    private fun checkAnswer(wasLeftButtonClick: Boolean): Boolean {
        val leftNumber = leftButtonView.text.toString().toInt()
        val rightNumber = rightButtonView.text.toString().toInt()

        val isLeftNumberBigger = leftNumber > rightNumber

        return (wasLeftButtonClick && isLeftNumberBigger) || (!wasLeftButtonClick && !isLeftNumberBigger)
    }

    private fun displayResult(answerWasCorrect: Boolean) {
        setBackgroundColor(answerWasCorrect)
        toastResult(answerWasCorrect)
        updatePoints(answerWasCorrect)
    }

    private fun setBackgroundColor(answerWasCorrect: Boolean) {
        var color = if (answerWasCorrect) Color.GREEN else Color.RED
        backgroundView.setBackgroundColor(color)
    }

    private fun toastResult(answerWasCorrect: Boolean) {
        val status = if (answerWasCorrect) "won" else "lost"
        val points = pointsView.text.toString().toInt()
        val amount = if (!answerWasCorrect && (points == 0)) "" else "1 point"
        val message = "You $status $amount"
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun updatePoints(answerWasCorrect: Boolean) {
        var points = pointsView.text.toString().toInt()

        if (answerWasCorrect) {
            points++
        } else if (points > 0) {
            points--
        }
        pointsView.text = points.toString()
    }
}
