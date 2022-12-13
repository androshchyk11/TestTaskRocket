package com.example.testtaskrocket.presentation.gamesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtaskrocket.databinding.ItemGameBinding
import com.example.testtaskrocket.domain.entity.GameEntity
import javax.inject.Inject

class GamesAdapter @Inject constructor() : RecyclerView.Adapter<GamesAdapter.GamesViewHolder>() {

    val items = arrayListOf<GameEntity>()
    var onClickListener: ((Int) -> Unit)? = null

    inner class GamesViewHolder(private val binding: ItemGameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding) {
                gameTitle.text = items[adapterPosition].name
                Glide
                    .with(root.context)
                    .load(items[adapterPosition].image.iconUrl)
                    .into(gameImage)

                gameClickOverlay.setOnClickListener {
                    onClickListener?.invoke(adapterPosition)
                }
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context))
        return GamesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = items.size
}