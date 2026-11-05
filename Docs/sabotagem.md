# 🛡️ Relatório de Sabotagem e Resiliência — Planify

Este documento registra os testes de estresse e falhas controladas realizados para garantir que o aplicativo reaja corretamente a situações adversas, utilizando os estados de UI configurados.

## 📊 Tabela de Sabotagens

| Sabotagem | Local do Código | Comportamento Esperado | Resultado Real | Status |
| :--- | :--- | :--- | :--- | :---: |
| **Banco Vazio** | `CompromissoDao` | A tela principal deve exibir o estado `Vazio` com a mensagem amigável. | Exibiu ícone de calendário e mensagem de agenda livre. | ✅ |
| **Erro de SQL** | `CompromissoDao` | Ao forçar um erro na Query (ex: nome de tabela errado), deve exibir a tela de `Erro`. | Exibiu o estado de Erro com botão de tentar novamente. | ✅ |
| **Delay de Rede/Banco** | `CompromissoRepository` | Ao inserir um `delay(5000)`, a tela deve mostrar o `Carregando`. | O indicador de progresso apareceu corretamente durante a espera. | ✅ |
| **Dados Corrompidos** | `Compromisso` | Forçar um valor nulo em campo obrigatório. | O app tratou via `try-catch` no ViewModel e mostrou a tela de Erro. | ✅ |
| **Modo Avião** | Persistência Room | Os dados cadastrados devem continuar aparecendo sem internet. | Funcionamento 100% offline confirmado. | ✅ |

## 🧠 Conclusão Técnica
O sistema de estados implementado através da `sealed interface HomeUiState` provou ser resiliente. O uso de `catch` no Flow do Room garante que falhas de persistência não causem crash no aplicativo (ANR), revertendo a interface para um estado explicativo ao usuário.
