package com.example.lista_de_livros

import ApiService
import Livro
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var livrosAdapter: LivrosAdapter
    private var livros = mutableListOf<Livro>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        recyclerView = findViewById(R.id.recyclerViewLivros)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Verifique se o token foi passado corretamente
        val token = intent.getStringExtra("token")

        // Verifica se o token está válido antes de fazer a requisição
        if (token.isNullOrEmpty()) {
            Toast.makeText(this, "Token inválido ou não fornecido!", Toast.LENGTH_LONG).show()
            return
        }

        val apiService = RetrofitClient.retrofitInstance.create(ApiService::class.java)
        val call = apiService.getLivros("Bearer $token")

        // Inicializa o adapter vazio no início
        livrosAdapter = LivrosAdapter(livros)
        recyclerView.adapter = livrosAdapter

        // Faça a requisição
        call.enqueue(object : Callback<List<Livro>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<Livro>>, response: Response<List<Livro>>) {
                if (response.isSuccessful) {
                    val livrosResponse = response.body()
                    livrosResponse?.let {
                        livros.clear()
                        livros.addAll(it)
                        livrosAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(this@ListaActivity, "Erro: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Livro>>, t: Throwable) {
                Toast.makeText(this@ListaActivity, "Falha na requisição: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }
}

