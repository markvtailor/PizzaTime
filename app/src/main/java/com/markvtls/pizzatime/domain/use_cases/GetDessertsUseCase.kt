package com.markvtls.pizzatime.domain.use_cases

import com.markvtls.pizzatime.data.dto.DishResponse
import com.markvtls.pizzatime.domain.repository.DishesRepository
import com.markvtls.pizzatime.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use this to get desserts list from API.
 */
class GetDessertsUseCase @Inject constructor(
    private val repository: DishesRepository
) {
    operator fun invoke(): Flow<List<DishResponse>> = flow {
        val response = repository.getDesserts(Constants.API_KEY, Constants.API_HOST)
        emit(response)
    }
}