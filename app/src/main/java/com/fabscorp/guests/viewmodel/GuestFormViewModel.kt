package com.fabscorp.guests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.fabscorp.guests.model.GuestModel
import com.fabscorp.guests.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application){
    private val repository = GuestRepository.getInstance(application)

    fun insert (guestModel: GuestModel){
        repository.insert(guestModel)
    }
}