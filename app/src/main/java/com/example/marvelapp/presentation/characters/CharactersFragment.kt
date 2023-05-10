package com.example.marvelapp.presentation.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.core.domain.model.Character
import com.example.marvelapp.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding get() = _binding!!

    private val charactersAdapter = CharactersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCharactersBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCharactersAdapter()
        charactersAdapter.submitList(
            listOf(
                Character("3d-man", "https://cmro.travis-starnes.com/images/issues/fantastic_four/annual/006.jpg"),
                Character("3d-man", "https://cmro.travis-starnes.com/images/issues/fantastic_four/annual/006.jpg"),
                Character("3d-man", "https://cmro.travis-starnes.com/images/issues/fantastic_four/annual/006.jpg"),
                Character("3d-man", "https://cmro.travis-starnes.com/images/issues/fantastic_four/annual/006.jpg"),
            )
        )
    }

    private fun initCharactersAdapter(){
        with(binding.recyclerCharacters) {
            setHasFixedSize(true)
            adapter = charactersAdapter
        }
    }

}