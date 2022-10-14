package com.markvtls.pizzatime.di

import android.content.Context
import com.markvtls.pizzatime.data.repository.DishesRepositoryImpl
import com.markvtls.pizzatime.data.source.local.DishDao
import com.markvtls.pizzatime.data.source.local.DishesDatabase
import com.markvtls.pizzatime.data.source.remote.PizzaApiService
import com.markvtls.pizzatime.domain.repository.DishesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing local data dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDishDao(database: DishesDatabase): DishDao {
        return database.dishDao()
    }

    @Provides
    @Singleton
    fun provideDishesDataBase(@ApplicationContext context: Context): DishesDatabase {
        return DishesDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideDishesRepository(pizzaApiService: PizzaApiService, database: DishesDatabase): DishesRepository {
        return DishesRepositoryImpl(pizzaApiService, database)
    }
}