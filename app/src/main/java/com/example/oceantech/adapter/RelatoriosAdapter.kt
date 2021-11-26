package com.example.oceantech.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oceantech.R
import com.example.oceantech.models.Relatorio

class RelatoriosAdapter(private val relatorios: List<Relatorio>) : RecyclerView.Adapter<RelatoriosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = relatorios.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val relatorio = relatorios[position]
        holder.localidade.text = relatorio.localidade
        holder.data.text = relatorio.data
        holder.temperatura.text = relatorio.temperatura
        holder.ph.text = relatorio.ph
        holder.oxigenioDissolvido.text = relatorio.oxigenioDissolvido
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val localidade: TextView = itemView.findViewById(R.id.localidade)
        val data: TextView = itemView.findViewById(R.id.data)
        val temperatura: TextView = itemView.findViewById(R.id.temperatura)
        val ph: TextView = itemView.findViewById(R.id.ph)
        val oxigenioDissolvido: TextView = itemView.findViewById(R.id.oxigenioDissolvido)
    }

}