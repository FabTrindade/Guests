package com.fabscorp.guests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fabscorp.guests.constants.DataBaseConstants
import com.fabscorp.guests.databinding.FragmentPresentBinding
import com.fabscorp.guests.view.adapter.GuestsAdapter
import com.fabscorp.guests.view.listener.OnGuestListener
import com.fabscorp.guests.viewmodel.GuestViewModel

class PresentFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GuestViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel= ViewModelProvider(this).get(GuestViewModel::class.java)
        _binding = FragmentPresentBinding.inflate(inflater, container, false)

        //Recycler view layout
        binding.recyclerGuests.layoutManager = LinearLayoutManager(context)

        //Recycler view adapter
        binding.recyclerGuests.adapter = adapter

        //Recycler click listener
        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.deleteGuestById(id)
                viewModel.getPresent()
            }
        }

        adapter.attachListener(listener)
        observe()

        return binding.root
    }
    override fun onResume() {
        super.onResume()
        viewModel.getPresent()
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