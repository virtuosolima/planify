package br.edu.ifpe.planify.ui.viewmodel

import androidx.lifecycle.*
import br.edu.ifpe.planify.data.repository.CompromissoRepository
import br.edu.ifpe.planify.model.Compromisso
import br.edu.ifpe.planify.notification.NotificationScheduler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class CompromissoViewModel(
    private val repository: CompromissoRepository,
    private val notificationScheduler: NotificationScheduler
) : ViewModel() {

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate

    val compromissosForDate: StateFlow<List<Compromisso>> = _selectedDate
        .flatMapLatest { date -> repository.getCompromissosByDate(date) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allCompromissos: StateFlow<List<Compromisso>> = repository.allCompromissos
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Estado da UI (HomeUiState)
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Carregando)
    val uiState: StateFlow<HomeUiState> = _uiState

    private var carregarJob: Job? = null

    init {
        carregarCompromissos()
    }

    fun carregarCompromissos() {
        carregarJob?.cancel()
        carregarJob = viewModelScope.launch {
            _uiState.value = HomeUiState.Carregando
            try {
                repository.getCompromissosByDate(_selectedDate.value)
                    .catch { emitError ->
                        _uiState.value = HomeUiState.Erro(
                            emitError.localizedMessage ?: "Não foi possível carregar os compromissos. Tente novamente."
                        )
                    }
                    .collect { lista ->
                        _uiState.value = if (lista.isEmpty()) {
                            HomeUiState.Vazio
                        } else {
                            HomeUiState.Sucesso(lista)
                        }
                    }
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Erro("Não foi possível carregar os compromissos. Tente novamente.")
            }
        }
    }

    /**
     * Função chamada para tentar novamente após um erro na UI
     */
    fun recarregar() {
        carregarCompromissos()
    }

    fun selectDate(date: LocalDate) {
        _selectedDate.value = date
        carregarCompromissos()
    }

    fun insert(compromisso: Compromisso) = viewModelScope.launch {
        val id = repository.insert(compromisso)
        if (compromisso.temLembrete) {
            // Criamos uma cópia com o ID real gerado pelo Room para o alarme
            notificationScheduler.scheduleNotification(compromisso.copy(id = id.toInt()))
        }
    }

    fun update(compromisso: Compromisso) = viewModelScope.launch {
        repository.update(compromisso)
        if (compromisso.temLembrete) {
            notificationScheduler.scheduleNotification(compromisso)
        } else {
            notificationScheduler.cancelNotification(compromisso.id)
        }
    }

    fun delete(compromisso: Compromisso) = viewModelScope.launch {
        repository.delete(compromisso)
        notificationScheduler.cancelNotification(compromisso.id)
    }
}

class CompromissoViewModelFactory(
    private val repository: CompromissoRepository,
    private val notificationScheduler: NotificationScheduler
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CompromissoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CompromissoViewModel(repository, notificationScheduler) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
