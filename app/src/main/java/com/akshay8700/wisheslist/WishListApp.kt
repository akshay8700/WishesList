package com.akshay8700.wisheslist

import android.app.Application

class WishListApp: Application() {
    override fun onCreate() {
        super.onCreate()
        // This provide function is creating our
        // database through database builder
        Graph.provide(this)
    }
}