package com.akshay8700.wisheslist.data

import androidx.room.Database
import androidx.room.RoomDatabase

// Actuall database
@Database(
    // Getting column and rows from wish we have declared
    // them in Wish class
    entities = [Wish::class],
    version = 1,
    exportSchema = false
)
// Returning WishDao means actions insert, delete etc.
abstract class WishDatabase : RoomDatabase() {
    abstract fun wishDao(): WishDao
}