package com.markvtls.pizzatime.data.repository

import com.markvtls.pizzatime.data.dto.DishResponse
import com.markvtls.pizzatime.data.source.local.DishEntity
import com.markvtls.pizzatime.data.source.local.DishesDatabase
import com.markvtls.pizzatime.data.source.remote.PizzaApiService
import com.markvtls.pizzatime.domain.repository.DishesRepository
import javax.inject.Inject


/**
 * Implementation of DishesRepository.
 */
class DishesRepositoryImpl @Inject constructor(
    private val pizzaApiService: PizzaApiService,
    private val database: DishesDatabase
): DishesRepository {

    override suspend fun getPizzas(apiKey: String, apiHost: String): List<DishResponse> {
        return pizzaApiService.getPizzas(apiKey, apiHost)
    }

    override suspend fun getDesserts(apiKey: String, apiHost: String): List<DishResponse> {
        return pizzaApiService.getDesserts(apiKey, apiHost)
    }

    override fun getSavedDishes(type: String): List<DishEntity> {
        return database.dishDao().getDishes(type)
    }

    override fun insertDishes(dishes: List<DishEntity>) {
        database.dishDao().insertDishes(dishes)
    }


}