package com.example.recipes.core.di

import com.example.recipes.core.RetrofitUnsafeClientHelper
import com.example.recipes.data.network.RecipeApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://demo1496816.mockable.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(RetrofitUnsafeClientHelper.getUnsafeOkHttpClient())
        .build()

    @Singleton
    @Provides
    fun provideRecipeApiClient(retrofit: Retrofit): RecipeApiClient = retrofit.create(RecipeApiClient::class.java)
}