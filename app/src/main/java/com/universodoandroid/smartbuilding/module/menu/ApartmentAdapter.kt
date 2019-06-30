package com.universodoandroid.smartbuilding.module.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.universodoandroid.smartbuilding.R
import com.universodoandroid.smartbuilding.databinding.ApartmentItemBinding
import com.universodoandroid.smartbuilding.module.menu.dto.ApartmentDto

class ApartmentAdapter(private val apartments: List<ApartmentDto>, private val onClick: (Int) -> Unit) :
        RecyclerView.Adapter<ApartmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.apartment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.run {
            val currentApartment = apartments[position]
            apartment = currentApartment

            executePendingBindings()

            apartmentFab.setOnClickListener { onClick(currentApartment.id) }
        }
    }

    override fun getItemCount(): Int = apartments.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ApartmentItemBinding>(view)
    }

}
