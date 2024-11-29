package com.example.lista_de_livros

import ApiService
import Livros
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lista_de_livros.api.RetrofitClient
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val token = intent.getStringExtra("token")

        val inputnome = findViewById<TextInputEditText>(R.id.text1)
        val inputautor = findViewById<TextInputEditText>(R.id.text2)

        val button = findViewById<Button>(R.id.button)
        val buttonLista = findViewById<Button>(R.id.buttonLista)

        button.setOnClickListener{
            val livro = Livros(name = inputnome.text.toString(), autor = inputautor.text.toString())

            // Criando o Retrofit ApiService
            val apiService = RetrofitClient.retrofitInstance.create(ApiService::class.java)

            // Passando o token no cabeçalho e o objeto Livros no corpo
            val call = apiService.addLivro("Bearer ${token}", livro)

            // Enviando a requisição de forma assíncrona
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // Requisição bem-sucedida
                        Toast.makeText(this@CadastroActivity, "Livro adicionado com sucesso!", Toast.LENGTH_SHORT).show()
                        inputnome.text = null
                        inputautor.text = null
                    } else {
                        // Erro na resposta
                        Toast.makeText(this@CadastroActivity, "Erro: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // Falha na requisição
                    Toast.makeText(this@CadastroActivity, "Falha na requisição: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

        buttonLista.setOnClickListener{
            val intent = Intent(this@CadastroActivity, ListaActivity::class.java)
            intent.putExtra("token", token)  // Passando uma string
            startActivity(intent)
        }
    }
}