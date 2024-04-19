package com.fabscorp.guests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fabscorp.guests.model.GuestModel
import com.fabscorp.guests.repository.GuestRepository

class GuestViewModel(application: Application) : AndroidViewModel(application) {

    private var repo = GuestRepository (application.applicationContext)
    private val listAllGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuests

    fun getAll() {
        listAllGuests.value = repo.getAll()
    }

    fun deleteGuestById(id: Int) {
        repo.delete(id)
    }

    fun getPresent() {
        listAllGuests.value = repo.getPresent()
    }
    fun getAbsent() {
        listAllGuests.value = repo.getAbsent()
    }
}