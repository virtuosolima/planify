package br.edu.ifpe.planify.ui.viewmodel

import br.edu.ifpe.planify.model.Compromisso

/**
 * Modelagem de estados da tela inicial (Home) conforme definido no plano de arquitetura.
 */
sealed interface HomeUiState {
    data object Carregando : HomeUiState
    data object Vazio : HomeUiState
    data class Sucesso(val compromissos: List<Compromisso>) : HomeUiState
    data class Erro(val mensagem: String) : HomeUiState
}
