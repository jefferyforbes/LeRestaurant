package com.jeffery.lerestaurant.di

import android.content.Context
import androidx.room.Room
import com.jeffery.lerestaurant.data.AppDatabase
import com.jeffery.lerestaurant.data.LeRestaurantService
import com.jeffery.lerestaurant.data.LeRestaurantServiceImpl
import com.jeffery.lerestaurant.data.repositories.MenuRepository
import com.jeffery.lerestaurant.data.repositories.MenuRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "le_restaurant_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun providesDatabaseService(
        leRestaurantServiceImpl: LeRestaurantServiceImpl
    ): LeRestaurantService {
        return leRestaurantServiceImpl
    }

    @Provides
    fun providesMenuRepository(menuRepositoryImpl: MenuRepositoryImpl): MenuRepository {
        return menuRepositoryImpl
    }
}