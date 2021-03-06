package com.example.usspeak.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.usspeak.R
import com.example.usspeak.databinding.RowHistoryBinding
import com.example.usspeak.model.page.HistoryDetailFragment
import com.example.usspeak.response.HistoryResponse

class HistoryAdapter(
    private val listHistory: ArrayList<HistoryResponse.DataItem>,
    val context: Context?
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    class ViewHolder(binding: RowHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val emotion = binding.tvEmotion
        val duration = binding.tvDuration
        val date = binding.tvDate
        val icon = binding.ivEmotion
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.emotion.text = listHistory[position].emotion
        holder.duration.text = listHistory[position].duration
        holder.date.text = listHistory[position].date.toString()

        val emoji = when (listHistory[position].emotion) {
            "Sad" -> R.drawable.ic_emotion_sad
            "Happy" -> R.drawable.ic_emotion_happy
            "Fearful" -> R.drawable.ic_emotion_fearful
            "Neutral" -> R.drawable.ic_emotion_neutral
            "Disgusted" -> R.drawable.ic_emotion_disgusted
            "Surprised" -> R.drawable.ic_emotion_surprised
            "Angry" -> R.drawable.ic_emotion_angry
            else -> R.drawable.placeholder_circle
        }

        Glide.with(holder.itemView)
            .load(emoji)
            .placeholder(R.drawable.placeholder_circle)
            .error(R.drawable.placeholder_circle)
            .into(holder.icon)

        holder.itemView.setOnClickListener {
            HistoryDetailFragment.DATE = listHistory[position].date.toString()
            HistoryDetailFragment.DURATION = listHistory[position].duration.toString()
            HistoryDetailFragment.SUGGESTION = listHistory[position].suggestion.toString()
            HistoryDetailFragment.EMOTION = listHistory[position].emotion.toString()
            Navigation.findNavController(it).navigate(R.id.fragment_history_detail)
        }

    }

    override fun getItemCount(): Int = listHistory.size
}