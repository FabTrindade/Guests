package com.fabscorp.guests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabscorp.guests.model.GuestModel
import com.fabscorp.guests.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application){
    private val repository = GuestRepository.getInstance(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guests: LiveData<GuestModel> = guestModel

    fun insert (guestModel: GuestModel){
        repository.insert(guestModel)
    }
    fun get(Id: Int) {
        guestModel.value = repository.get(Id)
    }
    fun save(model: GuestModel) {
        if(model.id == 0){
            repository.insert(model)
        } else {
            repository.update(model)
        }
    }
}