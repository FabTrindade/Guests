package com.fabscorp.guests.viewmodel

import androidx.lifecycle.ViewModel
import com.fabscorp.guests.repository.GuestRepository

class GuestFormViewModel: ViewModel(){
    private val repository = GuestRepository.getInstance()
}