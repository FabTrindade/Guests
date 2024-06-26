package com.fabscorp.guests.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fabscorp.guests.databinding.RowGuestBinding
import com.fabscorp.guests.model.GuestModel
import com.fabscorp.guests.view.listener.OnGuestListener
import com.fabscorp.guests.view.viewholder.GuestViewHolder

class GuestsAdapter: RecyclerView.Adapter<GuestViewHolder>(){
    private var guestList: List<GuestModel> = listOf()
    lateinit var guestListener: OnGuestListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(item, guestListener)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, pos: Int) {
        holder.bind(guestList[pos])
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    fun updateGuests(list: List<GuestModel>) {
        guestList = list
        notifyDataSetChanged()
    }

    fun attachListener(listener: OnGuestListener) {
        guestListener = listener
    }


}