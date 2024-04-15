package com.fabscorp.guests.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fabscorp.guests.databinding.RowGuestBinding
import com.fabscorp.guests.model.GuestModel

class GuestViewHolder(private val bind: RowGuestBinding) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        bind.textMame.text = guest.name
    }
}