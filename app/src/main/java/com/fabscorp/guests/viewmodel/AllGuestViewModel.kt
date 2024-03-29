package com.fabscorp.guests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabscorp.guests.model.GuestModel
import com.fabscorp.guests.repository.GuestRepository

class AllGuestViewModel(application: Application) : AndroidViewModel(application) {

    private var repo = GuestRepository.getInstance(application.applicationContext)
    private val listAllGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuests

    fun getAll() {
        listAllGuests.value = repo.getAll()
    }
}