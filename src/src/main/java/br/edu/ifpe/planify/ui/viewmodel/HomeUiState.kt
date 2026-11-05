package br.edu.ifpe.planify.ui.viewmodel

import br.edu.ifpe.planify.model.Compromisso

sealed interface HomeUiState {
    object Carregando : HomeUiState
    object Vazio : HomeUiState
    data class Sucesso(val compromissos: List<Compromisso>) : HomeUiState
    data class Erro(val mensagem: String) : HomeUiState
}
