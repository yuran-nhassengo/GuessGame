package com.lamarck.guessgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.lamarck.guessgame.databinding.FragmentGameBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding?=null
    private val binding get() =_binding!!

    lateinit var viewModel: GameViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=  FragmentGameBinding.inflate(inflater, container, false)

        val view = binding.root

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)


        updateScreen()

        binding.guessButton.setOnClickListener() {
            viewModel.makeGuess(binding.guess.text.toString().uppercase())
            binding.guess.text = null
            updateScreen()

            if(viewModel.isWon()|| viewModel.isLost()){
                val action = GameFragmentDirections
                    .actionGameFragmentToResultFragment(viewModel.wonLostMessage())
                view.findNavController().navigate(action)
            }
        }


        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun updateScreen(){
        binding.word.text = viewModel.secretWorsdDisplay
        binding.lives.text ="You have ${viewModel.livesLefty} lives left"
        binding.incorrectGuess.text ="Incorrect guesses: ${viewModel.incorrectGuesses}"

    }








}