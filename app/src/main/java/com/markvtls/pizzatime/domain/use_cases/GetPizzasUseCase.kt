package com.markvtls.pizzatime.domain.use_cases

import com.markvtls.pizzatime.data.dto.DishResponse
import com.markvtls.pizzatime.domain.repository.DishesRepository
import com.markvtls.pizzatime.utils.Constants.API_HOST
import com.markvtls.pizzatime.utils.Constants.API_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use this to get pizzas list from API.
 */
class GetPizzasUseCase @Inject constructor(
    private val repository: DishesRepository
) {
    operator fun invoke(): Flow<List<DishResponse>> = flow {
        val response = repository.getPizzas(API_KEY, API_HOST)
        emit(response)
    }
}