package com.lamarck.guessgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    val words = listOf("Android","Activity","Fragment")
    val secretWord = words.random().uppercase()
    val secretWorsdDisplay = MutableLiveData<String>()
    var correctGuesses =""
    val incorrectGuesses = MutableLiveData<String>()
    val livesLefty = MutableLiveData<Int>(8)

    init {
        secretWorsdDisplay.value = deriveSecretWordDisplay()
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
                secretWorsdDisplay.value = deriveSecretWordDisplay()
            }else{
                incorrectGuesses.value +="$guess"
                livesLefty.value = livesLefty.value?.minus(1)
            }
        }
    }

    fun isWon() = secretWord.equals(secretWorsdDisplay.value,true)

    fun isLost() = livesLefty.value?:0  <=0

    fun wonLostMessage():String{
        var message =""
        if(isWon()) message ="You won!"
        else if (isLost()) message ="You lost!"
        message +="The word was $secretWord."
        return message
    }

}