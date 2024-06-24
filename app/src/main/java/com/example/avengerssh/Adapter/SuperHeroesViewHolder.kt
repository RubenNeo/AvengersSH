package com.example.avengerssh.Adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.avengerssh.data.SuperheroResponse
import com.example.avengerssh.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroViewHolder(private val binding: ItemSuperheroBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(superhero: SuperheroResponse) {
        binding.nameTextView.text = superhero.name
        Picasso.get().load(superhero.image.url).into(binding.avatarImageView);

    }
}