package com.universodoandroid.smartbuilding.module.sensors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.universodoandroid.smartbuilding.R
import com.universodoandroid.smartbuilding.databinding.SensorItemBinding
import com.universodoandroid.smartbuilding.module.sensors.dto.SensorDto

class SensorsAdapter(private val sensors: List<SensorDto>,
                     private val onChange: (Int, Boolean, onComplete: () -> Unit, onError: () -> Unit) -> Unit) :
    RecyclerView.Adapter<SensorsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sensor_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.run {
            val currentSensor = sensors[position]
            sensor = currentSensor

            executePendingBindings()

            stateSwitch.setOnClickListener {
                holder.setItemsVisibility(View.VISIBLE, View.GONE)
                val isChecked = stateSwitch.isChecked

                onChange(currentSensor.id, isChecked, {
                    holder.setItemsVisibility(View.GONE, View.VISIBLE)
                }) {
                    holder.setItemsVisibility(View.GONE, View.VISIBLE)
                    stateSwitch.isChecked = !isChecked
                }
            }
        }
    }

    override fun getItemCount(): Int = sensors.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding = DataBindingUtil.bind<SensorItemBinding>(view)

        fun setItemsVisibility(progressBarVisibility: Int, switchVisibility: Int) {
            binding?.progressBar?.visibility = progressBarVisibility
            binding?.stateSwitch?.visibility = switchVisibility
        }
    }

}