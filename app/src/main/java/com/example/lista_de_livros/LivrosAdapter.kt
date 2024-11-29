package com.example.lista_de_livros

import Livro
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LivrosAdapter(private val livros: MutableList<Livro>) : RecyclerView.Adapter<LivrosAdapter.LivroViewHolder>() {

    class LivroViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.nameTextView)
        val autor: TextView = view.findViewById(R.id.autorTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_livro, parent, false)
        return LivroViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: LivroViewHolder, position: Int) {
        val livro = livros[position]
        holder.titulo.text = "Nome do Livro: ${livro.data.name}"
        holder.autor.text = "Autor: ${livro.data.autor}"
    }

    override fun getItemCount(): Int = livros.size
}

