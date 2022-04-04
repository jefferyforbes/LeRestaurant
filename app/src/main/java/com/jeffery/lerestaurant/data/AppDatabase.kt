package com.jeffery.lerestaurant.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jeffery.lerestaurant.data.dao.MenuDao
import com.jeffery.lerestaurant.data.dao.OrderDao
import com.jeffery.lerestaurant.data.dao.OrderItemDao
import com.jeffery.lerestaurant.data.dao.UserDao
import com.jeffery.lerestaurant.data.entities.MenuItem
import com.jeffery.lerestaurant.data.entities.Order
import com.jeffery.lerestaurant.data.entities.OrderItem
import com.jeffery.lerestaurant.data.entities.User
import com.jeffery.lerestaurant.domain.CourseType
import com.jeffery.lerestaurant.util.Converters

@Database(
    entities = [User::class, MenuItem::class, Order::class, OrderItem::class],
    version = 7,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun menuDao(): MenuDao
    abstract fun orderDao(): OrderDao
    abstract fun orderItemDao() : OrderItemDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "le_restaurant_database"
                )
                    .allowMainThreadQueries()
                    .createFromAsset("menuItem2.db")
                    .fallbackToDestructiveMigration()
                    .build()
                instance
            }
        }

        fun deleteDatabase() {
            INSTANCE = null
        }
    }
}