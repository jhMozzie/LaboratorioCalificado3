package com.flores.joseph.laboratoriocalificado03

import android.content.Intent
import android.net.Uri
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
            binding.tvLastName.text = profesor.last_name
            binding.tvPhone.text = profesor.phone_number
            binding.tvEmail.text = profesor.email
            Glide
                .with(itemView)
                .load(profesor.getProfesorImage())
                .into(binding.ivProfesor)

            binding.tvPhone.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${profesor.phone_number}")
                }
                itemView.context.startActivity(intent)
            }

            binding.tvEmail.setOnLongClickListener {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:") // solo aplicaciones de correo electrónico deberían manejar esto
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(profesor.email)) // destinatarios
                    putExtra(Intent.EXTRA_SUBJECT, "Consulta Importante")
                    putExtra(Intent.EXTRA_TEXT, "Estimado/a ${profesor.name},")
                }
                itemView.context.startActivity(Intent.createChooser(intent, "Enviar correo electrónico usando:"))
                true // Indica que el evento de long click fue manejado
            }
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