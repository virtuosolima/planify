package br.edu.ifpe.planify.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objeto Singleton que configura e provê a instância do serviço Retrofit.
 */
object RetrofitInstance {

    private const val BASE_URL = "https://api.planify.ifpe.edu.br/"

    val apiService: PlanifyApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlanifyApiService::class.java)
    }
}
