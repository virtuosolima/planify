# Prompt Inicial: Implementação de Estados de UI no Planify

**Contexto:**
Somos um time de 4 desenvolvedores trabalhando no aplicativo **Planify** (Android/Jetpack Compose/Room). Nosso objetivo hoje é implementar o padrão de estados de UI (Carregando, Vazio, Erro e Sucesso) na tela principal e garantir a resiliência do app através de testes de sabotagem.

**Objetivo Comum:**
Implementar o tratamento de estados na `HomeScreen` e garantir que o app nunca trave, mesmo sob falhas provocadas. A entrega será um Pull Request na branch `feature/estados-da-tela` com o arquivo `docs/sabotagem.md` preenchido.

**Instruções de Saída:**
Com base nas tarefas abaixo, gere **4 Planos de Execução Detalhados**, um para cada perfil de desenvolvedor. 

**REGRAS DE GERAÇÃO:**
1. Gerar 4 arquivos de saída, um para cada plano.
2. Salvar todos os arquivos na pasta `Docs/output/` na raiz do projeto.
3. Cada plano deve ser independente, permitindo que os desenvolvedores trabalhem em paralelo sem depender da conclusão de outro plano para iniciar.
4. **Os nomes dos estados de UI devem ser obrigatoriamente em Português BR: `Carregando`, `Vazio`, `Sucesso` e `Erro`.**

---

### Perfil 1: O Arquiteto de Dados (Foco: Modelagem e ViewModel)
*   **Missão:** Criar a estrutura técnica dos estados de interface.
*   **Tarefas:** 
    *   Definir o arquivo `UiState.kt` com a `sealed interface` contendo os estados `Carregando`, `Vazio`, `Sucesso` e `Erro`.
    *   Preparar o `ViewModel` para expor um `StateFlow` que represente esses estados.
    *   Implementar a lógica de captura de erros (`try-catch`) para transicionar para o estado `Erro`.
    *   Garantir que a lógica de negócio mapeie listas vazias para o estado `Vazio`.

### Perfil 2: O Desenvolvedor UI/UX (Foco: Compose e Recursos)
*   **Missão:** Criar os componentes visuais para cada estado de UI.
*   **Tarefas:** 
    *   Desenvolver os Composables para `Carregando` (progress), `Vazio` (ícone+texto), `Erro` (alerta+botão de repetição) e `Sucesso` (lista de dados).
    *   Implementar a estrutura de navegação visual na `HomeScreen` usando um bloco `when` exaustivo para alternar entre esses componentes.
    *   Garantir que o botão "Tentar Novamente" esteja pronto para ser vinculado à lógica de recarga.

### Perfil 3: O Especialista em QA e Resiliência (Foco: Sabotagem e Estabilidade)
*   **Missão:** Validar a robustez do app através de falhas provocadas.
*   **Tarefas:** 
    *   Realizar o "Teste de Sabotagem": identificar e documentar 5 cenários de erro (ex: banco de dados inacessível, dados corrompidos, atraso de rede simulado).
    *   Preencher o arquivo `docs/sabotagem.md` com os relatos técnicos.
    *   Garantir que o app apresente o estado de `Erro` amigável em vez de encerrar inesperadamente.

### Perfil 4: O Integrador e Documentador (Foco: Git, PR e Strings)
*   **Missão:** Padronizar a comunicação com o usuário e organizar a entrega.
*   **Tarefas:** 
    *   Centralizar todos os textos dos estados (`Carregando`, `Vazio`, `Erro`) no arquivo `strings.xml` com mensagens profissionais.
    *   Organizar a branch `feature/estados-da-tela` e revisar o histórico de commits.
    *   Validar se a implementação segue a regra de "No Else" no código Compose.

---

**Diretrizes para a IA na criação dos planos:**
1.  **Uso de IA (Gemini):** O plano deve incluir momentos de consulta à IA, mas reforçar que o desenvolvedor deve saber explicar cada linha.
2.  **Qualidade do Código:** Reforce a proibição do `else` no final do `when` conforme a Seção 9 do material de apoio.
3.  **Fechamento:** O plano deve prever os 10 minutos finais para a demonstração em modo avião.
