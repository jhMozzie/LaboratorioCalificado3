package com.flores.joseph.laboratoriocalificado03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flores.joseph.laboratoriocalificado03.databinding.ItemProfesorBinding

class ProfesorAdapter(
    var List: List<ProfesorResponse>
) :RecyclerView.Adapter<ProfesorAdapter.ViewHolder>(){
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemProfesorBinding.bind(view)

        fun bind(profesor: ProfesorResponse){
            binding.tvName.text = profesor.name
            Glide
                .with(itemView)
                .load(profesor.getProfesorImage())
                .into(binding.ivProfesor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemProfesorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view.root)
    }

    override fun getItemCount(): Int = List.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemProfesor = List[position]
        holder.bind(itemProfesor)
    }
}