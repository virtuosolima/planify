# Relatório de Sabotagem e Resiliência - Planify

Este documento registra os testes de resiliência e simulação de falhas (sabotagem controlada) para validar o comportamento dos estados de UI (`HomeUiState`) na tela principal (Home).

## 🧪 Casos de Teste de Resiliência

| ID | Cenário de Sabotagem | Comportamento Esperado | Status |
|----|----------------------|------------------------|--------|
| T01 | **Erro de Banco de Dados / DAO** (Simular exceção na query) | O ViewModel deve capturar a exceção e emitir `HomeUiState.Erro`. A `HomeScreen` deve exibir a mensagem de erro e o botão **"Tentar Novamente"**. | [x] Aprovado |
| T02 | **Base de Dados Vazia** (Sem compromissos no dia selecionado) | O ViewModel deve emitir `HomeUiState.Vazio`. A `HomeScreen` deve exibir a mensagem amigável de estado vazio. | [x] Aprovado |
| T03 | **Carregamento Lento / Latência** | O ViewModel deve emitir inicialmente `HomeUiState.Carregando` exibindo o indicador de progresso circular (`CircularProgressIndicator`). | [x] Aprovado |
| T04 | **Recuperação após Erro** (Clicar em "Tentar Novamente") | A função `recarregar()` deve ser acionada, restabelecendo o fluxo de busca de compromissos. | [x] Aprovado |
