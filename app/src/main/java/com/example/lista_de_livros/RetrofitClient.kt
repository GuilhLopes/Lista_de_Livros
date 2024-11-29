package com.example.lista_de_livros.api

import okhttp3.OkHttpClient
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object RetrofitClient {

    private const val BASE_URL = "https://trabalho-final-api-production.up.railway.app/" // Substitua pela URL base da sua API

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val token = "seu_token_aqui"  // Aqui você pode pegar o token de alguma forma (por exemplo, de SharedPreferences)
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")  // Adicionando o token no cabeçalho
                    .build()
                return chain.proceed(request)
            }
        })
        .build()

    val retrofitInstance: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)  // Usando o cliente OkHttp com o interceptor
        .addConverterFactory(GsonConverterFactory.create())  // Usando o converter do Gson
        .build()
}
