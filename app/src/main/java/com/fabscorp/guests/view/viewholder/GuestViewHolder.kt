package com.fabscorp.guests.view.viewholder

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.fabscorp.guests.R
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

        bind.textMame.setOnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle(itemView.context.getString(R.string.guest_remove_alert))
                .setMessage(itemView.context.getString(R.string.guest_remove))
                .setNegativeButton(
                    itemView.context.getString(R.string.no)
                ) { dialog, wich -> //example with lambda
                    Toast.makeText(
                        itemView.context,
                        itemView.context.getString(R.string.op_cancelled),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setPositiveButton(
                    itemView.context.getString(R.string.yes),
                    object : DialogInterface.OnClickListener { //example without lambda
                        override fun onClick(dialog: DialogInterface?, wich: Int) {
                            listener.onDelete(guest.id)
                        }
                    })
                .create()
                .show()
            true
        }

    }
}