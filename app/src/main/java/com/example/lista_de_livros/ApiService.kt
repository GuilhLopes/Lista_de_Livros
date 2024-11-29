
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("login")
    fun geraTolken(@Body userRequest: UserRequest): Call<TolkenResponse>

    @POST("identified/saveData")
    fun addLivro(
        @Header("Authorization") authorization: String,
        @Body livro: Livros
    ): Call<Void>

    @GET("identified/getData")  // A rota que você quer acessar para listar os livros
    fun getLivros(
        @Header("Authorization") token: String  // Passando o token no cabeçalho
    ): Call<List<Livro>>  // O retorno será o modelo LivrosResponse
}
