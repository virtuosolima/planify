package br.edu.ifpe.planify.data.remote

import br.edu.ifpe.planify.model.Cliente
import br.edu.ifpe.planify.model.Compromisso
import br.edu.ifpe.planify.model.Servico
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Interface Retrofit que define os endpoints remotos para sincronização e consumo de dados.
 */
interface PlanifyApiService {

    @GET("clientes")
    suspend fun getClientes(): List<Cliente>

    @POST("clientes")
    suspend fun insertCliente(@Body cliente: Cliente): Response<Cliente>

    @GET("clientes/{id}")
    suspend fun getClienteById(@Path("id") id: Int): Response<Cliente>

    @GET("servicos")
    suspend fun getServicos(): List<Servico>

    @POST("servicos")
    suspend fun insertServico(@Body servico: Servico): Response<Servico>

    @GET("compromissos")
    suspend fun getCompromissos(): List<Compromisso>

    @POST("compromissos")
    suspend fun insertCompromisso(@Body compromisso: Compromisso): Response<Compromisso>
}
