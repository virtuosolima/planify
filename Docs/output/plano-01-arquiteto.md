# Plano de Execução: Arquiteto de Dados (Perfil 1)

**Objetivo Técnico:** Implementar a modelagem de estados de UI e a lógica de transição no ViewModel para a tela principal (Home).

## 🚀 Passo a Passo Independente

### 1. Definição do UiState (Pode ser feito agora)
Crie o arquivo `src/src/main/java/br/edu/ifpe/planify/ui/viewmodel/HomeUiState.kt`.
- Use uma `sealed interface` para representar os 4 estados em Português: `Carregando`, `Vazio`, `Sucesso` (contendo a lista de compromissos) e `Erro` (contendo a mensagem de erro).

### 2. Preparação do ViewModel (Pode ser feito agora)
Edite `br.edu.ifpe.planify.ui.viewmodel.CompromissoViewModel`:
- Adicione um `MutableStateFlow<HomeUiState>` inicializado como `Carregando`.
- Crie a estrutura da função `fetchCompromissos()` com blocos `try-catch`.
- Se os dados ainda não estiverem vindo do Repository, use `delay(1000)` e emita um estado fixo para teste.

## 🤖 Instruções de IA (Gemini)
- "Gemini, crie uma sealed interface em Kotlin chamada HomeUiState com os estados Carregando, Vazio, Sucesso(list) e Erro(message)."
- "Como estruturar um ViewModel para alternar entre estados de UI em português usando StateFlow."

## ✅ Critérios de Aceite
- [ ] Arquivo `HomeUiState.kt` criado com nomes em Português.
- [ ] ViewModel preparado para gerenciar os estados de forma independente.
