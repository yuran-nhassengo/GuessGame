package com.lamarck.guessgame

import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    val words = listOf("Android","Activity","Fragment")
    val secretWord = words.random().uppercase()
    var secretWorsdDisplay = ""
    var correctGuesses =""
    var incorrectGuesses =""
    var livesLefty =8

    init {
        secretWorsdDisplay = deriveSecretWordDisplay()
    }

    fun deriveSecretWordDisplay(): String{
        var display =""
        secretWord.forEach {
            display += checkLetter(it.toString())
        }

        return display
    }


    fun checkLetter(str:String) = when (correctGuesses.contains(str)){
        true -> str
        false->"_"
    }

    fun makeGuess(guess: String){
        if (guess.length ==1 ){
            if (secretWord.contains(guess)){
                correctGuesses += guess
                secretWorsdDisplay = deriveSecretWordDisplay()
            }else{
                incorrectGuesses +="$guess"
                livesLefty--
            }
        }
    }

    fun isWon() = secretWord.equals(secretWorsdDisplay,true)

    fun isLost() = livesLefty  <=0

    fun wonLostMessage():String{
        var message =""
        if(isWon()) message ="You won!"
        else if (isLost()) message ="You lost!"
        message +="The word was $secretWord."
        return message
    }

}