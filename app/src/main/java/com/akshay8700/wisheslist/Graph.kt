package com.akshay8700.wisheslist

import android.content.Context
import androidx.room.Room
import com.akshay8700.wisheslist.data.WishDatabase
import com.akshay8700.wisheslist.data.WishRepository

// This graph is taking our database and
object Graph {
    private lateinit var database: WishDatabase

    // Question: Why he is taking wishDao from database
    val wishRepository by lazy {
        WishRepository(wishDao = database.wishDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, WishDatabase::class.java, "wishlist.db").build()
    }
}