package br.edu.ifpe.planify.ui.state

sealed class UiState {
    data object Carregando : UiState()
    data object Vazio : UiState()
    data class Erro(val mensagem: String) : UiState()
    data class Sucesso<T>(val dados: T) : UiState()
}
