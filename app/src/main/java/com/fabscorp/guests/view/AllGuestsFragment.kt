package com.fabscorp.guests.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fabscorp.guests.databinding.FragmentAllGuestsBinding
import com.fabscorp.guests.view.adapter.GuestsAdapter
import com.fabscorp.guests.view.listener.OnGuestListener
import com.fabscorp.guests.viewmodel.AllGuestViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: AllGuestViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {

        viewModel = ViewModelProvider(this).get(AllGuestViewModel::class.java)
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        //Recycler view layout
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        //Recycler view adapter
        binding.recyclerAllGuests.adapter = adapter

        //Recycler click listener
        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                Toast.makeText(context, "OnClick Event! Id: $id", Toast.LENGTH_SHORT).show()
            }

            override fun onDelete(id: Int) {
                viewModel.deleteGuestById(id)
                viewModel.getAll()
            }
        }

        adapter.attachListener(listener)

        viewModel.getAll()

        observe()

        return binding.root
    }

    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}