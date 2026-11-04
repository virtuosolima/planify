# Plano de Execução: Desenvolvedor UI/UX (Perfil 2)

**Objetivo Técnico:** Implementar a renderização reativa dos estados de UI na `HomeScreen` utilizando Jetpack Compose.

## 🚀 Passo a Passo Independente

### 1. Criação dos Componentes de Estado (Pode ser feito agora)
Você pode criar as funções Composable independentemente da lógica do ViewModel:
- **ViewCarregando:** Um `Box` centralizado com `CircularProgressIndicator`.
- **ViewVazio:** Um `Column` com um ícone ilustrativo e o texto "Nenhum compromisso encontrado".
- **ViewErro:** Um `Column` com ícone de alerta, mensagem de erro e um `Button` "Tentar Novamente".

### 2. Estrutura da HomeScreen (Pode ser feito agora)
- Prepare o bloco `when` na `HomeScreen`. 
- Caso o `UiState` ainda não esteja pronto, você pode usar uma variável temporária ou `remember { mutableStateOf(...) }` para testar visualmente cada estado alternando manualmente.
- **IMPORTANTE:** Mapeie explicitamente `HomeUiState.Carregando`, `HomeUiState.Vazio`, `HomeUiState.Sucesso` e `HomeUiState.Erro`. Não use `else`.

## 🤖 Instruções de IA (Gemini)
- "Gemini, como criar uma tela de erro no Jetpack Compose com um ícone de aviso e um botão de 'Tentar Novamente' centralizados?"
- "Crie quatro componentes Compose simples para representar estados de Carregando, Vazio, Sucesso e Erro."

## ✅ Critérios de Aceite
- [ ] Componentes visuais para os 4 estados criados.
- [ ] Uso do bloco `when` com nomes em Português e sem `else`.
