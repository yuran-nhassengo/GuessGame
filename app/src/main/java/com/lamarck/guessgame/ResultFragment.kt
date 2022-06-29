package com.lamarck.guessgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.lamarck.guessgame.databinding.FragmentResultBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {

    private var _binding:FragmentResultBinding? =null
    private val binding get() =_binding!!

    lateinit var viewModel: ResultViewModel
    lateinit var viewModelFactor: ResultViewModelFactorry



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val view = binding.root

        val resul = ResultFragmentArgs.fromBundle(requireArguments()).result
        viewModelFactor = ResultViewModelFactorry(resul)
        viewModel =ViewModelProvider(this,viewModelFactor)
            .get(ResultViewModel::class.java)

        binding.wonLost.text = viewModel.result

        binding.newGameButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_resultFragment_to_gameFragment)

        }

        return view


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}