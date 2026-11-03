package br.edu.ifpe.planify.ui.viewmodel

import androidx.lifecycle.*
import br.edu.ifpe.planify.data.repository.ClienteRepository
import br.edu.ifpe.planify.model.Cliente
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ClienteViewModel(private val repository: ClienteRepository) : ViewModel() {

    val allClientes: StateFlow<List<Cliente>> = repository.allClientes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun insert(cliente: Cliente) = viewModelScope.launch {
        repository.insert(cliente)
    }

    fun update(cliente: Cliente) = viewModelScope.launch {
        repository.update(cliente)
    }

    fun delete(cliente: Cliente) = viewModelScope.launch {
        repository.delete(cliente)
    }

    suspend fun getClienteById(id: Int): Cliente? = repository.getClienteById(id)

    // Integração com Retrofit
    fun fetchClientesRemotos(onResult: (Result<List<Cliente>>) -> Unit = {}) = viewModelScope.launch {
        val result = repository.fetchClientesRemotos()
        onResult(result)
    }

    fun enviarClienteRemoto(cliente: Cliente, onResult: (Result<Cliente?>) -> Unit = {}) = viewModelScope.launch {
        val result = repository.syncClienteRemoto(cliente)
        onResult(result)
    }
}

class ClienteViewModelFactory(private val repository: ClienteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClienteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClienteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
