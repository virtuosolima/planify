# Planify 

Projeto final desenvolvido para a disciplina de **Desenvolvimento para Dispositivos Móveis**.

O **Planify** é um sistema de agendamento e organização de compromissos para pequenos empreendedores e autônomos, com foco em controle de clientes, serviços e horários.

## 📂 Estrutura do Projeto

Para facilitar a avaliação, o projeto foi organizado seguindo a solicitação do docente:
- **`src/`**: Pasta raiz contendo todo o código-fonte, configurações de build e documentação técnica.
- **`src/agents.md`**: Documento detalhando os domínios e a divisão de trabalho da equipe.

## 🛠 Membros e Entidades
O projeto é dividido em 4 pilares fundamentais:
- **Clientes**: Gestão completa de contatos, incluindo busca e exclusão rápida.
- **Compromissos**: Agenda inteligente com controle de horários e status de pagamento.
- **Serviços**: Catálogo de serviços com precificação.
- **Lembretes**: Sistema de notificações locais para redução de faltas.

## 🔔 Detalhamento das Funcionalidades Implementadas

### 1. Sistema de Lembretes (Background)
A lógica de notificações foi construída para funcionar de forma independente da interface:
- **Tecnologia**: Utiliza `AlarmManager` para garantir precisão no disparo e `BroadcastReceiver` para processar o alerta mesmo com o app fechado.
- **Automação**: Ao marcar "Ativar Lembrete" no formulário de agendamento, o app calcula automaticamente 30 minutos antes do horário de início e agenda uma notificação no sistema Android.
- **Permissões**: Implementado o fluxo de requisição de `POST_NOTIFICATIONS` para compatibilidade com Android 13+.

### 2. Gestão de Listas (UX)
Para uma experiência moderna e limpa, implementamos:
- **Swipe to Delete**: Gesto de deslizar para a esquerda em Clientes e Serviços para exclusão imediata.
- **Undo (Desfazer)**: Integração com `Snackbar` que permite ao usuário reverter uma exclusão acidental em até 4 segundos, recuperando os dados diretamente no banco Room.
- **Busca em Tempo Real**: Filtro reativo na lista de clientes utilizando `StateFlow`.

## 🚀 Tecnologias Utilizadas
- Jetpack Compose (UI)
- Room Database (Persistência Local)
- Retrofit 2 & Gson (Comunicação Remota)
- Coroutines & Flow (Concorrência)
- Navigation Compose (Fluxo de Telas)
- AlarmManager / WorkManager (Lembretes locais)
