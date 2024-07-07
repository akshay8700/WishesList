package com.akshay8700.wisheslist.data

import kotlinx.coroutines.flow.Flow

// Its like a father of Dao or WishDao this work like a higher layer that helps developer to not
// just do actions in coroutine but manage different conditions for example if data is not able to
// get from cloud we can get that from local storage, Dao cannot do that because dao is a actual function
// THIS IS NOW A DAO/ACTION FUNCTION
class WishRepository(private val wishDao: WishDao) {
    suspend fun addAWish(wish: Wish){
        wishDao.addAWish(wish)
    }

    fun getWishes(): Flow<List<Wish>> = wishDao.getAllWishes()

    fun getAWishById(id:Long) :Flow<Wish> {
        return wishDao.getAWishById(id)
    }

    suspend fun updateAWish(wish:Wish){
        wishDao.updateAWish(wish)
    }

    suspend fun deleteAWish(wish: Wish){
        wishDao.deleteAWish(wish)
    }
}