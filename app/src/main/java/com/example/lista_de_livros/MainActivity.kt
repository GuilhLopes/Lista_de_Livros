package com.example.lista_de_livros

import ApiService
import TolkenResponse
import UserRequest
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitClient.retrofitInstance

        val apiService = retrofit.create(ApiService::class.java)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val input = findViewById<TextInputEditText>(R.id.text)
            val userRequest = UserRequest(
                username = input.text.toString()
            )
            val call = apiService.geraTolken(userRequest)

            call.enqueue(object : Callback<TolkenResponse> {
                override fun onResponse(call: Call<TolkenResponse>, response: Response<TolkenResponse>) {
                    if (response.isSuccessful) {
                        // Se a requisição for bem-sucedida
                        val tolkenResponse = response.body()
                        tolkenResponse?.let {
                            Toast.makeText(this@MainActivity, "Tolken criado: ${it.token}", Toast.LENGTH_LONG).show()

                            val intent = Intent(this@MainActivity, CadastroActivity::class.java)
                            intent.putExtra("token", it.token)  // Passando uma string
                            startActivity(intent)
                        }
                    } else {
                        // Caso haja erro na resposta
                        Toast.makeText(this@MainActivity, "Erro: ${response.message()}", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<TolkenResponse>, t: Throwable) {
                    // Caso haja erro na requisição
                    Toast.makeText(this@MainActivity, "Falha: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}