package com.fabscorp.guests.view.viewholder

import android.view.View
import android.view.View.OnClickListener
import androidx.recyclerview.widget.RecyclerView
import com.fabscorp.guests.databinding.RowGuestBinding
import com.fabscorp.guests.model.GuestModel
import com.fabscorp.guests.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        bind.textMame.text = guest.name

        bind.textMame.setOnClickListener() {
            listener.onClick(guest.id)
        }

    }
}