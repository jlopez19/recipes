package com.example.recipes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.data.model.RecipeModel

class RecipeAdapter(
    private var recipeModelList: List<RecipeModel>,
    private val onClickListener: (RecipeModel) -> Unit
): RecyclerView.Adapter<RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecipeViewHolder(layoutInflater.inflate(R.layout.item_recipe, parent, false))
    }

    override fun getItemCount() = recipeModelList.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.render(recipeModelList[position], onClickListener)
    }

    fun setRecipeList(recipeModelList: List<RecipeModel>) {
        this.recipeModelList = recipeModelList
        notifyDataSetChanged()
    }
}