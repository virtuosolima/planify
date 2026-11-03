# Plano de Desenvolvimento - Planify

## 👥 Time e Responsabilidades (Entidades)

Cada membro será responsável por uma entidade core do sistema, implementando seu **Model, DAO, Repository e UI**:

1.  **Membro A: Agente de Clientes (`model/Cliente.kt`)**
    *   Foco: Cadastro de clientes e informações de contato.
2.  **Membro B: Agente de Agendamentos (`model/Compromisso.kt`)**
    *   Foco: Gestão de datas e horários.
3.  **Membro C: Agente de Serviços (`model/Servico.kt`)**
    *   Foco: Catálogo de serviços e valores.
4.  **Membro D: Lembretes (`model/Lembretes.kt`)**
    *   Foco: Ativação e desativação de lembretes, configuração da antecedência e agendamento de notificações locais.

## 🛠 Tech Stack
- **Interface:** Jetpack Compose + Material 3
- **Persistência:** Room Database
- **Comunicação Remota:** Retrofit 2 + Gson
- **Navegação:** Navigation Compose
- **Assincronismo:** Coroutines & Flow

## 🌿 Padrão de Git e Commits

### Convenção de Branches
Todo desenvolvimento deve ser realizado em branches temáticas antes de integrar à `main`:
- `feature/<nome>`: Desenvolvimento de novas funcionalidades (ex: `feature/cadastro-cliente`, `feature/interface-retrofit`).
- `fix/<nome>`: Correção de bugs ou ajustes (ex: `fix/horario-compromisso`).
- `docs/<nome>`: Alterações em documentação (ex: `docs/atualizar-prd`).

### Padrão de Mensagens de Commit
Utilizar o padrão Conventional Commits com mensagens claras e objetivas em português:
- `feat:` Nova funcionalidade implementada (ex: `feat: criar interface retrofit e integracao com viewmodel`).
- `fix:` Correção de bug (ex: `fix: corrigir tipo do id na entidade servico`).
- `docs:` Alterações e inclusões na documentação (ex: `docs: definir convencoes de branches e commits`).
- `chore:` Tarefas de configuração, dependências e build (ex: `chore: adicionar permissao de internet`).
