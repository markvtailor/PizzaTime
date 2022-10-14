package com.markvtls.pizzatime.domain.use_cases

import com.markvtls.pizzatime.domain.model.Dish
import com.markvtls.pizzatime.domain.model.toDomain
import com.markvtls.pizzatime.domain.repository.DishesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use this to get saved dishes from DishesDatabase.
 */
class GetSavedDishesByTypeUseCase @Inject constructor(
    private val repository: DishesRepository
) {
    operator fun invoke(type: String): Flow<List<Dish>> = flow {
       val dishes = repository.getSavedDishes(type)
        emit(dishes.map { it.toDomain() })
    }
}