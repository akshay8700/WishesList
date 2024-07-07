package com.akshay8700.wisheslist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "wish-title")
    val title: String = "",
    @ColumnInfo(name = "wish-desc")
    val description: String = ""
)

// Dummy Data
object DummyWish {
    val wishList = listOf(
        Wish(title = "Urus Yellow", description = "Dream car"),
        Wish(title = "Rolls Royce", description = "Luxury car"),
        Wish(title = "Luxury 10cr house in delhi", description = "beautiful house"),
        Wish(title = "1 thousand cr in bank", description = "Money")
    )
}
