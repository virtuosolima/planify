package br.edu.ifpe.planify.data.repository

import br.edu.ifpe.planify.data.local.dao.ClienteDao
import br.edu.ifpe.planify.data.remote.PlanifyApiService
import br.edu.ifpe.planify.data.remote.RetrofitInstance
import br.edu.ifpe.planify.model.Cliente
import kotlinx.coroutines.flow.Flow

class ClienteRepository(
    private val clienteDao: ClienteDao,
    private val apiService: PlanifyApiService = RetrofitInstance.apiService
) {
    val allClientes: Flow<List<Cliente>> = clienteDao.getAllClientes()

    suspend fun getClienteById(id: Int): Cliente? = clienteDao.getClienteById(id)

    suspend fun insert(cliente: Cliente) = clienteDao.insertCliente(cliente)

    suspend fun update(cliente: Cliente) = clienteDao.updateCliente(cliente)

    suspend fun delete(cliente: Cliente) = clienteDao.deleteCliente(cliente)

    // Integração com Retrofit: buscar clientes remotos
    suspend fun fetchClientesRemotos(): Result<List<Cliente>> = try {
        val remotos = apiService.getClientes()
        Result.success(remotos)
    } catch (e: Exception) {
        Result.failure(e)
    }

    // Integração com Retrofit: enviar cliente para o backend
    suspend fun syncClienteRemoto(cliente: Cliente): Result<Cliente?> = try {
        val response = apiService.insertCliente(cliente)
        if (response.isSuccessful) {
            Result.success(response.body())
        } else {
            Result.failure(Exception("Erro na resposta da API: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}
