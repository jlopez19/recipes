package com.example.recipes.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipes.data.model.RecipeModel
import com.example.recipes.databinding.ItemRecipeBinding

class RecipeViewHolder(
    private val view: View
): RecyclerView.ViewHolder(view) {

    private val binding = ItemRecipeBinding.bind(view)

    fun render(recipeModel: RecipeModel, onClickListener: (RecipeModel) -> Unit) {
        binding.textViewRecipeName.text = recipeModel.name

        Glide.with(view)
            .load(recipeModel.image)
            .centerCrop()
            .into(binding.imageViewRecipeImage)

        itemView.setOnClickListener { onClickListener(recipeModel) }
    }
}