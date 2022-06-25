package com.jeffery.lerestaurant.data.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.jeffery.lerestaurant.data.AppDatabase
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import java.io.IOException

class MenuDaoTest {

    private lateinit var menuDao: MenuDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()

        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        menuDao = db.menuDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}