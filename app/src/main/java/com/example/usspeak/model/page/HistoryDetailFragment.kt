package com.example.usspeak.model.page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.usspeak.R
import com.example.usspeak.databinding.FragmentHistoryDetailBinding

class HistoryDetailFragment : Fragment() {
    private var _binding : FragmentHistoryDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val suggestion = "\"" + SUGGESTION + "\""

        binding.tvDate.text = DATE
        binding.tvDuration.text = DURATION
        binding.tvEmotion.text = EMOTION
        binding.tvSuggestion.text = suggestion

        val emoji = when (EMOTION) {
            "Sad" -> R.drawable.ic_emotion_sad
            "Happy" -> R.drawable.ic_emotion_happy
            "Fearful" -> R.drawable.ic_emotion_fearful
            "Neutral" -> R.drawable.ic_emotion_neutral
            "Disgusted" -> R.drawable.ic_emotion_disgusted
            "Surprised" -> R.drawable.ic_emotion_surprised
            "Angry" -> R.drawable.ic_emotion_angry
            else -> R.drawable.placeholder_circle
        }

        Glide.with(view)
            .load(emoji)
            .placeholder(R.drawable.placeholder_circle)
            .error(R.drawable.placeholder_circle)
            .into(binding.ivEmotion)
    }

    companion object {
        var DATE = "date"
        var DURATION = "duration"
        var EMOTION = "emotion"
        var SUGGESTION = "suggestion"
    }
}