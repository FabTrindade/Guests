package com.fabscorp.guests.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fabscorp.guests.databinding.FragmentAllGuestsBinding
import com.fabscorp.guests.view.adapter.GuestsAdapter
import com.fabscorp.guests.viewmodel.AllGuestViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: AllGuestViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {

        viewModel = ViewModelProvider(this).get(AllGuestViewModel::class.java)
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        //Recycler view layout
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        //Recycler view adapter
        binding.recyclerAllGuests.adapter = GuestsAdapter()

        viewModel.getAll()

        observe()

        return binding.root
    }

    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            val s = ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}