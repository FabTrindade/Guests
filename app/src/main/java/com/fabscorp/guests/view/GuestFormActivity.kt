package com.fabscorp.guests.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fabscorp.guests.R
import com.fabscorp.guests.constants.DataBaseConstants
import com.fabscorp.guests.databinding.ActivityGuestFormBinding
import com.fabscorp.guests.model.GuestModel
import com.fabscorp.guests.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    var guestId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true
        observe()
        loadData()
    }

    private fun observe() {
        viewModel.guests.observe(this, Observer {
            binding.editName.setText(it.name)
            binding.radioPresent.isChecked = it.presence
            binding.radioAbsent.isChecked = !it.presence
        })

        viewModel.guestSaved.observe(this, Observer {
            if (it != "") {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            val model = GuestModel().apply {// .apply = Just this instance
                this.id = guestId
                this.name = name
                this.presence = presence
            }
            viewModel.save(model)
        }
    }
}