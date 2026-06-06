package com.dgdocha.gamelauncher.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dgdocha.gamelauncher.R
import com.dgdocha.gamelauncher.data.models.Game
import com.dgdocha.gamelauncher.databinding.ItemGameCardBinding
import coil.load

class GamesGridAdapter(
    private val onGameClick: (Game) -> Unit
) : ListAdapter<Game, GamesGridAdapter.GameViewHolder>(GameDiffCallback()) {

    inner class GameViewHolder(private val binding: ItemGameCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(game: Game) {
            binding.apply {
                gameTitle.text = game.name
                gameCategory.text = game.category ?: "Other"

                gameImage.load(game.packageName) {
                    placeholder(R.drawable.ic_launcher_background)
                    error(R.drawable.ic_launcher_background)
                }

                root.setOnClickListener {
                    onGameClick(game)
                }

                root.isFocusable = true
                root.setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        root.elevation = 8f
                    } else {
                        root.elevation = 4f
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemGameCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GameDiffCallback : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.packageName == newItem.packageName
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem == newItem
        }
    }
}
