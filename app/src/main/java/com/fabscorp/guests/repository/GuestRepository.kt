package com.fabscorp.guests.repository

import android.content.Context

class GuestRepository private constructor(context: Context){

    private val guestDataBase = GuestDataBase(context)

    //Singleton
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized){
                repository = GuestRepository(context)
            }
            return repository
        }
    }
}