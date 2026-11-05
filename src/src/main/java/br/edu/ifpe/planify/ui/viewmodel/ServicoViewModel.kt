package br.edu.ifpe.planify.ui.viewmodel

import androidx.lifecycle.*
import br.edu.ifpe.planify.data.repository.ServicoRepository
import br.edu.ifpe.planify.model.Servico
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ServicoViewModel(private val repository: ServicoRepository) : ViewModel() {

    val allServicos: StateFlow<List<Servico>> = repository.allServicos
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun insert(servico: Servico) = viewModelScope.launch {
        repository.insert(servico)
    }

    fun update(servico: Servico) = viewModelScope.launch {
        repository.update(servico)
    }

    fun delete(servico: Servico) = viewModelScope.launch {
        repository.delete(servico)
    }

    suspend fun getServicoById(id: Int): Servico? = repository.getServicoById(id)
}

class ServicoViewModelFactory(private val repository: ServicoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ServicoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ServicoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
