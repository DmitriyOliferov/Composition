package com.oliferov.composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import com.oliferov.composition.R
import com.oliferov.composition.databinding.FragmentGameFinishedBinding
import com.oliferov.composition.domain.entity.GameResult
import com.oliferov.composition.domain.entity.GameSettings
import com.oliferov.composition.domain.entity.Level
import com.oliferov.composition.presentation.ChooseLevelFragment.Companion.NAME
import java.lang.RuntimeException
import kotlin.properties.Delegates


class GameFinishedFragment : Fragment() {

    private lateinit var  gameResult: GameResult

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
    get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                retryGame()
            }
        })
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun setResult(){
//        binding.tvRequiredAnswers.text = R.string.re
//    }

    private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }

    companion object{
        private const val KEY_GAME_RESULT = "game_result"



        fun newInstance(gameResult: GameResult): GameFinishedFragment{
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }

    }

    private fun retryGame(){
        requireActivity().supportFragmentManager.popBackStack(GameFragment.NAME,FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}