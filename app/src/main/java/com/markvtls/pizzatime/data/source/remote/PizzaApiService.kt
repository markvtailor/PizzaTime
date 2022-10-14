package com.markvtls.pizzatime.data.source.remote

import com.markvtls.pizzatime.data.dto.DishResponse
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Retrofit service for making requests to PizzaAPI.
 */
interface PizzaApiService {

    /**
     * Get pizzas list from PizzaAPI.
     */
    @GET("pizzas")
    suspend fun getPizzas(@Header("X-RapidAPI-Key") apiKey: String, @Header("X-RapidAPI-Host") host: String): List<DishResponse>

    /**
     * Get desserts list from PizzaApi.
     */
    @GET("desserts")
    suspend fun getDesserts(@Header("X-RapidAPI-Key") apiKey: String, @Header("X-RapidAPI-Host") host: String): List<DishResponse>

}