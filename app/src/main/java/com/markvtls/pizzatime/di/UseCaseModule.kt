package com.markvtls.pizzatime.di

import com.markvtls.pizzatime.domain.repository.DishesRepository
import com.markvtls.pizzatime.domain.use_cases.GetDessertsUseCase
import com.markvtls.pizzatime.domain.use_cases.GetPizzasUseCase
import com.markvtls.pizzatime.domain.use_cases.GetSavedDishesByTypeUseCase
import com.markvtls.pizzatime.domain.use_cases.InsertDishesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing use cases.
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetPizzasUseCase(repository: DishesRepository): GetPizzasUseCase {
        return GetPizzasUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetDessertsUseCase(repository: DishesRepository): GetDessertsUseCase {
        return GetDessertsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetSavedDishesByTypeUseCase(repository: DishesRepository): GetSavedDishesByTypeUseCase {
        return GetSavedDishesByTypeUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideInsertDishesUseCase(repository: DishesRepository): InsertDishesUseCase {
        return InsertDishesUseCase(repository)
    }
}