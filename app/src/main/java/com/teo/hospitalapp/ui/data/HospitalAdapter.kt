package com.teo.hospitalapp.ui.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teo.hospitalapp.data.Hospital
import com.teo.hospitalapp.databinding.ListItemHospitalBinding
import com.teo.hospitalapp.ui.main.HospitalFragmentDirections

class HospitalAdapter  : ListAdapter<Hospital, HospitalAdapter.ViewHolder>(com.teo.hospitalapp.ui.data.DiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hospital = getItem(position)
        holder.apply {
            bind(createOnClickListener( hospital.organisationID), hospital)
            itemView.tag = hospital
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemHospitalBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    private fun createOnClickListener( orgId: Int): View.OnClickListener {
        return View.OnClickListener {
            val direction = HospitalFragmentDirections.actionToHospitalDetailFragment(orgId)
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
        private val binding: ListItemHospitalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Hospital) {
            binding.apply {
                clickListener = listener
                hospital = item
                executePendingBindings()
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Hospital>() {

    override fun areItemsTheSame(oldItem: Hospital, newItem: Hospital): Boolean {
        return oldItem.organisationID == newItem.organisationID
    }

    override fun areContentsTheSame(oldItem: Hospital, newItem: Hospital): Boolean {
        return oldItem == newItem
    }

}