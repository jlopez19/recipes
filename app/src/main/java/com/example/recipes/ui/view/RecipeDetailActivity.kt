package com.example.recipes.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.recipes.data.model.RecipeModel
import com.example.recipes.databinding.ActivityRecipeDetailBinding
import com.example.recipes.ui.viewModel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding

    private val recipeViewModel: RecipeViewModel by viewModels()

    private lateinit var recipeModel: RecipeModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipeId = intent.getIntExtra(RECIPE_ID_EXTRA_NAME, 0)
        recipeViewModel.getRecipeById(recipeId)
        recipeViewModel.recipeModel.observe(this) { recipeObserved ->
            recipeModel = recipeObserved
            setRecipeInformation()
        }

        binding.buttonRecipeMap.setOnClickListener { buttonRecipeMapListener() }
    }

    private fun setRecipeInformation() {
        binding.textViewRecipeName.text = recipeModel.name
        binding.textViewRecipeDescription.text = recipeModel.description

        Glide.with(this)
            .load(recipeModel.image)
            .centerCrop()
            .into(binding.imageViewRecipeImage)
    }

    private fun buttonRecipeMapListener() {
        val intent = Intent(this, RecipeMapActivity::class.java).apply {
            putExtra(RecipeMapActivity.RECIPE_LOCATION_EXTRA_NAME, recipeModel.location)
        }
        startActivity(intent)
    }

    companion object {
        const val RECIPE_ID_EXTRA_NAME = "RECIPE_ID_EXTRA_NAME"
    }
}