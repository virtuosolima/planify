# PRD — Documento de Requisitos do Produto

O que é um PRD? É o documento que responde o quê o app faz e por quê — não como ele é programado. Quem lê o PRD deve conseguir entender o app inteiro sem abrir o código.

| | |
|---|---|
| **App** | Planify |
| **Grupo** | STL + A (Grupo 01) |
| **Autores** | Thales, Abner, Sara e Letícia |
| **Versão do documento** | 1.2 |
| **Última atualização** | 07/10/2026 |
| **Status** | ( ) Rascunho (X) Em revisão ( ) Aprovado |

## 1. Visão do produto

**Pitch:** O Planify ajuda empreendedores e profissionais autônomos a organizar seus clientes, serviços, horários e resultados financeiros em um único aplicativo, sem depender de agendas de papel, conversas espalhadas ou vários aplicativos diferentes.

**Problema:** Pequenos empreendedores e profissionais autônomos frequentemente precisam administrar sozinhos clientes, horários, serviços e valores recebidos. Essas informações ficam espalhadas entre WhatsApp, agendas de papel, anotações e alarmes genéricos, causando esquecimento de compromissos, horários sobrepostos e dificuldade para acompanhar quanto realmente foi recebido no mês.

**Por que vale a pena fazer isso:** O Planify centraliza clientes, serviços, compromissos, valores e lembretes em um único aplicativo, permitindo que o usuário organize sua agenda e acompanhe seu faturamento de forma simples, usando persistência local (Room) para garantir funcionamento offline.

## 2. Público e cenário de uso

**Usuário-alvo:** Pequenos empreendedores e profissionais autônomos, principalmente pessoas entre 25 e 45 anos que atendem clientes ou administram compromissos por conta própria, sem equipe administrativa.

Exemplos:
- cabeleireiros
- manicures
- barbeiros
- personal trainers
- consultores
- prestadores de serviços em geral
- donos de pequenos negócios locais

**Usuário de referência:** Ana Cláudia, dona de um salão de beleza.

**História de uso:**

> "São 19h, Ana Cláudia terminou um atendimento e precisa organizar seus compromissos. Ela abre o Planify e visualiza o resumo do mês, conferindo a quantidade de atendimentos e o faturamento. Em seguida, acessa a área de clientes e cadastra um novo cliente. Ela então seleciona um serviço do catálogo (ex: Corte) e adiciona um novo compromisso informando o horário e o valor. Ao finalizar, o compromisso fica registrado e o lembrete automático é programado para uma hora antes do atendimento."

## 3. Objetivos e não-objetivos

**Objetivos desta versão (v1.0):**
- Permitir o cadastro e consulta de clientes.
- Permitir a gestão de um catálogo de serviços com nomes e preços.
- Permitir a criação, edição, exclusão e acompanhamento de compromissos vinculando cliente e serviço.
- Permitir o registro de valores e lembretes dos compromissos.
- Apresentar um resumo mensal com indicadores de atendimentos e faturamento.

**Não-objetivos (fora do escopo):**
- ❌ Sincronização em nuvem ou múltiplos dispositivos.
- ❌ Pagamentos online reais (apenas registro de valor).
- ❌ Chat ou comunicação com clientes.
- ❌ Mapa ou localização.

## 4. Requisitos funcionais

| ID | História de usuário | Critério de aceite | Prioridade |
|---|---|---|---|
| RF01 | Como usuário, quero visualizar um resumo dos meus compromissos e resultados do mês. | Ao abrir a tela principal, o mês selecionado apresenta os compromissos registrados e indicadores de faturamento e média. | Must |
| RF02 | Como usuário, quero cadastrar clientes para ter seus contatos. | Ao salvar nome e telefone, o cliente aparece na lista e pode ser selecionado ao criar um compromisso. | Must |
| RF03 | Como usuário, quero gerenciar serviços e preços no catálogo. | Cadastro de serviços com nome e valor padrão para uso rápido nos compromissos. | Must |
| RF04 | Como usuário, quero adicionar um compromisso vinculado a um cliente e serviço. | O usuário preenche os campos, vincula o Cliente e o Serviço, e o item aparece na agenda do dia. | Must |
| RF05 | Como usuário, quero registrar o valor de cada compromisso. | Ao salvar um compromisso, o valor informado entra no cálculo do faturamento do mês. | Must |
| RF06 | Como usuário, quero editar ou excluir registros para corrigir informações. | Possibilidade de alterar ou remover clientes, serviços ou compromissos. | Must |
| RF07 | Como usuário, quero ativar lembretes automáticos para não esquecer os horários. | O aplicativo deve disparar uma notificação local com a antecedência escolhida pelo usuário (ex: 15 min, 1h, 1 dia antes). | Should |

**Definição dos indicadores do resumo mensal**
- **Atendimentos:** quantidade de compromissos registrados no mês.
- **Faturamento:** soma dos valores dos compromissos registrados no mês.
- **Clientes:** quantidade de clientes únicos atendidos no mês.
- **Média:** faturamento dividido pela quantidade de atendimentos do mês.

## 5. Requisitos não funcionais

| ID | Requisito | Como será verificado |
|---|---|---|
| RNF01 | O app não pode fechar sozinho durante o uso normal. | 5 minutos de uso contínuo sem crash, em dispositivo real. |
| RNF02 | Persistência de dados offline via Room. | Os dados devem permanecer salvos após fechar e abrir o aplicativo. |
| RNF03 | Toda operação que pode falhar deverá possuir tratamento de exceção adequado. | Revisão do código e testes de erro (try/catch). |
| RNF04 | O app deverá rodar a partir do Android 7.0 (API 24). | Instalação em dispositivo real compatível. |
| RNF05 | O projeto deverá possuir organização de código profissional na pasta `src/`. | Verificação da estrutura de diretórios conforme solicitado pelo professor. |

## 6. Telas e navegação

**Mapa de navegação:**
- Tela Principal (Agenda/Resumo)
  - Acessa Clientes → Lista de Clientes
    - Adicionar → Cadastro de Cliente
  - Acessa Serviços → Catálogo de Serviços
    - Adicionar → Cadastro de Serviço
  - Novo Agendamento → Formulário de Compromisso

| Tela | O que mostra | Ações disponíveis |
|---|---|---|
| Principal | Resumo mensal, indicadores e lista de compromissos do dia. | Navegar entre dias, visualizar detalhes, adicionar novo. |
| Cadastro de Cliente | Campos para nome e telefone. | Preencher dados e salvar. |
| Catálogo de Serviços | Lista de serviços cadastrados e seus respectivos preços. | Adicionar, editar ou excluir serviços do catálogo. |
| Cadastro de Compromisso | Seleção de Cliente, Serviço, Data, Hora, Valor e Lembrete. | Vincular as entidades, preencher horários e salvar. |

**Estado vazio da tela principal:**
> "Nenhum compromisso registrado para este dia."

**Rascunhos das telas:**
- `docs/telas/01-principal.png`
- `docs/telas/02-compromisso.png`
- `docs/telas/03-clientes.png`
- `docs/telas/04-cadastro-cliente.png`

## 7. Dados

**Entidade: Cliente**

| Campo | Tipo | Obrigatório | Observação |
|---|---|---|---|
| id | Long | sim | Chave primária (PK), autogerada. |
| nome | String | sim | Nome completo do cliente. |
| telefone | String | sim | Contato para avisos. |

**Entidade: Servico**

| Campo | Tipo | Obrigatório | Observação |
|---|---|---|---|
| id | Long | sim | Chave primária (PK), autogerada. |
| nome | String | sim | Nome do serviço. |
| preco | Double | sim | Valor base do serviço. |

**Entidade: Compromisso**

| Campo | Tipo | Obrigatório | Observação |
|---|---|---|---|
| id | Long | sim | Chave primária (PK), autogerada. |
| clienteId | Long | sim | FK vinculando ao cliente atendido. |
| servicoId | Long | sim | FK vinculando ao serviço realizado. |
| dataHora | Long | sim | Data e hora do atendimento (timestamp). |
| valor | Double | sim | Valor cobrado no atendimento. |

**Entidade: Lembrete**

| Campo | Tipo | Obrigatório | Observação |
|---|---|---|---|
| id | Long | sim | Chave primária (PK), autogerada. |
| compromissoId | Long | sim | FK vinculando ao compromisso. |
| antecedenciaMinutos | Int | sim | Quanto tempo antes o lembrete deve disparar. |
| ativo | Boolean | sim | Se o lembrete está ativo. |

**Operações necessárias:**
(X) inserir (X) listar (X) atualizar (X) excluir

## 8. Arquitetura e tecnologias

| Item | Escolha |
|---|---|
| Linguagem | Kotlin |
| Interface | (X) Jetpack Compose ( ) XML/Views |
| Persistência | (X) Room ( ) — |
| Outras bibliotecas | Material 3, Navigation Compose, Coroutines, Flow, AlarmManager/WorkManager |
| minSdk / targetSdk | 24 (Android 7.0) / 35 (Android 15) |

## 9. Tratamento de erros

| Situação de falha | O que o app faz | Mensagem para o usuário |
|---|---|---|
| Campo obrigatório em branco | Impede o salvamento e destaca os campos necessários. | "Preencha os campos obrigatórios antes de salvar." |
| Erro ao salvar no banco | Mantém o usuário na tela e informa a falha. | "Não foi possível salvar os dados. Tente novamente." |
| Falha ao agendar lembrete | Salva o compromisso mas avisa sobre a notificação. | "Compromisso salvo, mas o lembrete não pôde ser ativado." |
| Confirmação de exclusão | Apresenta uma caixa de diálogo de confirmação. | "Tem certeza que deseja excluir este registro?" |

## 10. Identidade visual e publicação

| Item | Definição | Onde fica |
|---|---|---|
| Nome do app | Planify | strings.xml |
| Cor principal | Azul-marinho (#010736) | Color.kt |
| applicationId | br.edu.ifpe.planify | build.gradle.kts |
| versionName / versionCode | 1.0 / 1 | build.gradle.kts |

## 11. Plano de testes

| # | O que testar | Passos | Resultado esperado |
|---|---|---|---|
| T1 | Cadastrar Cliente | Adicionar nome e telefone e salvar. | O cliente aparece na lista. |
| T2 | Criar Compromisso | Selecionar cliente, serviço, data e valor. | Aparece na agenda do dia e atualiza o faturamento. |
| T3 | Testar Lembrete | Criar compromisso com lembrete ativo. | Notificação disparada no horário programado. |
| T4 | Reabrir o app | Cadastrar dados, fechar e abrir. | Todos os dados continuam disponíveis (persistência). |

## 12. Cronograma

| Marco | Prazo | Responsável | Status |
|---|---|---|---|
| M1 — Canvas + repositório | 16/09 | Grupo | Concluído |
| M2 — PRD aprovado | 30/09 | Grupo | Concluído |
| M3 — Funcionalidade base funcional | 21/10 | Grupo | [EM ABERTO] |
| M4 — Room completo (4 entidades) | 11/11 | Grupo | [EM ABERTO] |
| M5 — Identidade visual + .apk testado | 25/11 | Grupo | [EM ABERTO] |
| M6 — .aab + material de loja + README.md | 02/12 | Grupo | [EM ABERTO] |
| Entrega e apresentação final | 10/12 | Grupo | [EM ABERTO] |

## 13. Riscos

| Risco | Impacto | Plano B |
|---|---|---|
| Problemas com persistência (Room) | Alto | Simplificar os relacionamentos e testar migrações precocemente. |
| Falha nos lembretes (AlarmManager) | Médio | Validar permissões e testar em dispositivos físicos. |
| Um integrante ficar sobrecarregado | Médio | Revisar semanalmente quem está travado e redistribuir tarefas pequenas. |

## 14. Como vamos orientar a implementação com IA

A implementação usa o Gemini no Android Studio. Este PRD é o documento que diz à IA o que construir.

**Regras que colocamos no AGENTS.md:**
- Respeitar rigorosamente a estrutura de pastas `src/src/main/java`.
- Não implementar funções fora deste PRD sem aprovação do grupo.
- Explicar o funcionamento de Flow, Room e Coroutines em cada alteração.

**Divisão do perímetro explicável:**

| Parte do código | Responsável |
|---|---|
| Cliente | Thales |
| Compromisso | Abner |
| Serviço | Sara |
| Lembrete | Letícia |

## 15. Histórico de versões deste documento

| Versão | Data | Autor | O que mudou |
|---|---|---|---|
| 1.0 | 23/09/2026 | Grupo | Estrutura inicial conforme modelo oficial. |
| 1.1 | 09/10/2026 | Grupo | Atualização com 4 entidades (incluindo Pet) e ajuste de escopo. |
| 1.2 | 07/10/2026 | Grupo | Remoção do escopo de petshop; retorno ao foco original do Canvas (agenda e gestão financeira para empreendedores em geral). Entidade Pet substituída por Lembrete. |
