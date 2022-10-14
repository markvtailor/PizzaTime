package com.markvtls.pizzatime.domain.use_cases


import com.markvtls.pizzatime.data.dto.DishResponse
import com.markvtls.pizzatime.data.source.local.DishEntity
import com.markvtls.pizzatime.domain.repository.DishesRepository
import javax.inject.Inject

/**
 * Use this to insert API response into DishesDatabase.
 */
class InsertDishesUseCase @Inject constructor(
    private val repository: DishesRepository
) {
    operator fun invoke(type: String, dishes: List<DishResponse>) {
        val dishesToSave = dishes.map { DishEntity(it.name, type, it.price, it.description) }
        repository.insertDishes(dishesToSave)
    }
}