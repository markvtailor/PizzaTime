package com.markvtls.pizzatime.domain.repository

import com.markvtls.pizzatime.data.dto.DishResponse
import com.markvtls.pizzatime.data.source.local.DishEntity

/**
 * DishesRepository interface.
 */
interface DishesRepository {

    /**
     * Get pizzas list from API.
     */
    suspend fun getPizzas(apiKey: String, apiHost: String): List<DishResponse>

    /**
     * Get desserts list from API.
     */
    suspend fun getDesserts(apiKey: String, apiHost: String): List<DishResponse>

    /**
     * Get dishes list from RoomDB by type.
     */
    fun getSavedDishes(type: String): List<DishEntity>

    /**
     * Inserting API response into RoomDB.
     */
    fun insertDishes(dishes: List<DishEntity>)

}