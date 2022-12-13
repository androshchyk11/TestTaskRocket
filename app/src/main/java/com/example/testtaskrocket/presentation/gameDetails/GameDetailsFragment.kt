package com.example.testtaskrocket.presentation.gameDetails

import android.os.Bundle
import android.text.Html
import android.view.View
import com.bumptech.glide.Glide
import com.example.testtaskrocket.R
import com.example.testtaskrocket.databinding.FragmentGameDetailsBinding
import com.example.testtaskrocket.presentation.baseFragment.BaseFragment
import com.example.testtaskrocket.presentation.mainActivity.MainActivity


class GameDetailsFragment : BaseFragment<FragmentGameDetailsBinding>(R.layout.fragment_game_details) {

    companion object {
        const val GAME_IMAGE_URL = "GAME_IMAGE_URL"
        const val GAME_TITLE = "GAME_TITLE"
        const val GAME_DESCRIPTION = "GAME_DESCRIPTION"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getInfo()
    }

    override fun setUpClicks() {
        binding.backButton.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
    }

    private fun getInfo() {
        with(binding) {
            gameTitle.text = arguments?.getString(GAME_TITLE).toString()
            gameDescription.text = Html.fromHtml(arguments?.getString(GAME_DESCRIPTION).toString(), Html.FROM_HTML_MODE_COMPACT).toString()
            Glide
                .with(requireContext())
                .load(arguments?.getString(GAME_IMAGE_URL))
                .into(gameImage)
        }
    }
}