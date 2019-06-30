package com.universodoandroid.smartbuilding.module.menu.dialogs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.universodoandroid.smartbuilding.R
import com.universodoandroid.smartbuilding.databinding.SensorItemBinding
import com.universodoandroid.smartbuilding.module.menu.dto.SensorDto

class SensorsDialogAdapter(private val sensors: List<SensorDto>, private val onChange: (Boolean) -> Unit) :
    RecyclerView.Adapter<SensorsDialogAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sensor_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.run {
            val currentSensor = sensors[position]
            sensor = currentSensor

            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = sensors.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<SensorItemBinding>(view)
    }

}