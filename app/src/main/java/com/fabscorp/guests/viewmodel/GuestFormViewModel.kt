package com.fabscorp.guests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabscorp.guests.R
import com.fabscorp.guests.model.GuestModel
import com.fabscorp.guests.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository.getInstance(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guests: LiveData<GuestModel> = guestModel

    private val _guestSaved = MutableLiveData<String>()
    val guestSaved: LiveData<String> = _guestSaved

    fun insert(guestModel: GuestModel) {
        repository.insert(guestModel)
    }

    fun get(Id: Int) {
        guestModel.value = repository.get(Id)
    }

    fun save(model: GuestModel) {

        if (model.id == 0) {
            if (repository.insert(model)) {
                _guestSaved.value = "Insert completed successfully!"
                return
            }
        } else {
            if (repository.update(model)) {
                _guestSaved.value = "Update completed successfully!"
                return
            }
        }
        _guestSaved.value = "Operation failed!"
    }
}