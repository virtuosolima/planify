# 🎯 Canvas do Projeto Final — App Android

| | |
|---|---|
| **Grupo nº** | 01 |
| **Integrantes (3 a 4)** | Thales, Abner, Sara e Leticia |
| **Turma** | 3º ano B — EMI IPI |
| **Repositório** | `https://github.com/virtuosolima/-projeto-final--planify-` |
| **Data de preenchimento** | 09/09/2026 |
| **Entrega final** | **10/12/2026** |

---

## 🧩 Bloco 1 — Nome e pitch do app

**Nome do app:** Planify

**Pitch em uma frase:**
> "O [Planify] ajuda [microempreendedores e autônomos] a [organizar seus clientes, serviços e compromissos] sem precisar de [agendas de papel ou vários apps soltos]."

---

## 😖 Bloco 2 — Problema

Qual dor real vocês estão resolvendo? Descrevam uma situação concreta que alguém vive hoje.
- Muitos pequenos empreendedores (donos de salão, loja, prestadores de serviço autônomo, etc.) cuidam sozinhos de vários compromissos ao mesmo tempo: atendimento a clientes, reuniões com fornecedores, horários de entrega, e tarefas pessoais. Como não têm uma secretária ou sistema de agenda profissional, esses horários ficam espalhados entre WhatsApp, papel, cabeça e lembretes soltos no celular. O resultado: compromissos esquecidos, horários que se sobrepõem (marcar dois clientes no mesmo horário, por exemplo), e tempo perdido tentando lembrar o que precisa ser feito no dia. A falta de centralização dificulta o controle sobre o faturamento real e o histórico de atendimentos de cada cliente.


**Como esse problema é resolvido hoje (sem o app)?**

- Hoje, esse problema costuma ser resolvido de formas improvisadas e pouco confiáveis. Muitos empreendedores anotam seus compromissos em cadernos ou agendas de papel, o que funciona até certo ponto, mas não oferece nenhum tipo de aviso ou lembrete automático. Outros acabam deixando a própria conversa no WhatsApp virar uma espécie de agenda, com lembretes soltos misturados ao atendimento do cliente, o que facilita perder informações importantes no meio da rotina. Também é comum recorrer a alarmes e lembretes genéricos no celular, que avisam sobre um horário, mas sem contexto nenhum sobre o compromisso em si. Algumas pessoas tentam organizar tudo em planilhas simples no Excel ou Google Sheets, mas isso exige atualização manual constante e não gera nenhum tipo de aviso quando um horário está se aproximando. E, no fim das contas, muita gente ainda depende só da própria memória — o famoso "vou lembrar" — que falha justamente quando a rotina fica mais corrida e cheia de compromissos.


---

## 👥 Bloco 3 — Público-alvo

Para quem é o app? Sejam específicos (idade, contexto, com que frequência usariam).

- **Perfil principal:** Pequenos empreendedores e autônomos, entre 25 e 45 anos, que atendem clientes ou gerenciam compromissos por conta própria e não têm equipe administrativa — como cabeleireiros, manicures, personal trainers, consultores, prestadores de serviço em geral e donos de pequenos negócios locais. 
- **Quando/onde usam:** Usam o app diariamente, principalmente pela manhã (para revisar os compromissos do dia) e ao longo do dia sempre que um novo horário é marcado ou remarcado — seja no próprio local de trabalho, entre atendimentos, ou em qualquer lugar pelo celular.
- **Uma pessoa real que testaria o app:**  Ana Cláudia — dona de um salão de beleza e prestadora de serviços autônomos. 


---

## 💡 Bloco 4 — Solução em uma tela

Descreva o que a **tela principal** mostra e o que o usuário consegue fazer nela.

- **A tela principal lista:** os compromissos organizados por data, com foco no dia atual por padrão. Para cada compromisso, a lista exibe: o horário de início, o Nome do Cliente, o Serviço solicitado (ex: Corte de Cabelo), e um indicador visual de status de pagamento (PAGO ou PENDENTE). Os compromissos aparecem ordenados cronologicamente, do mais cedo para o mais tarde, e os que já passaram do horário ficam visualmente diferenciados — por exemplo, em cinza ou com um aviso de atraso. Se não houver nenhum compromisso cadastrado para o dia selecionado, a tela mostra uma mensagem simples incentivando o usuário a adicionar o primeiro compromisso.

- **A ação principal do usuário é:** adicionar um novo compromisso preenchendo um formulário que vincula: o Cliente, o Serviço (selecionado do catálogo), a data e o horário, permitindo definir se o pagamento já foi realizado e se deseja um lembrete automático.
- **Depois de agir, o usuário vê:** o retorno automático para a tela principal, onde o novo compromisso já aparece na posição correta da lista, ordenado por horário. Caso o lembrete tenha sido ativado, o sistema agenda a notificação local automaticamente, sem exigir nenhuma ação extra do usuário.

---

## ✅ Bloco 5 — Funcionalidades do MVP

Máximo de **4 funcionalidades**. Se tiver mais, corte. Lembre: *qualidade acima de complexidade*.

| # | Funcionalidade | Essencial? | Quem faz |
|---|---|---|---|
| F1 | Gestão de Clientes (Adicionar, editar e excluir contatos) | Sim | Thales |
| F2 | Catálogo de Serviços (Tipos de serviço e preços) | Sim | Sara |
| F3 | Agendamento de Compromissos (Vínculo cliente/serviço e ordenação) | Sim | Abner |
| F4 | Sistema de Notificação e Lembretes automáticos | Sim | Leticia |

---

## 🚫 Bloco 6 — Fora do escopo

- ❌ Login/cadastro de usuário e múltiplas contas — uso apenas local.
- ❌ Sincronização em nuvem entre dispositivos.
- ❌ Pagamento real via PIX/Cartão — apenas marcação visual de status.
- ❌ Chat ou comunicação direta com clientes via app.

---

## ⚙️ Bloco 7 — Caminho técnico

- [x] **Opção A — Room:** dados salvos no próprio celular (agenda, clientes, serviços e lembretes)

**Bibliotecas:** Room, ViewModel, Flow, Jetpack Compose, AlarmManager, Navigation Component.

## Tratamento de erros — try/catch

| Pode falhar | O usuário vê a mensagem |
|---|---|
| Campo obrigatório vazio | "Preencha todos os campos obrigatórios antes de salvar." |
| Erro ao salvar no banco | "Não foi possível salvar os dados. Tente novamente." |
| Falha ao agendar lembrete | "Não foi possível ativar o lembrete. Verifique as permissões." |

---

## 🎨 Bloco 8 — Identidade visual

| Item | Definição do grupo |
|---|---|
| Nome exibido | Planify |
| Cor principal | `#010736` (Azul Marinho) |
| Ideia do ícone | Um calendário minimalista focado em organização e tempo. |
| `applicationId` | `br.edu.ifpe.planify` |
| Versão inicial | `1.0` |

---

## 👤 Bloco 9 — Equipe, papéis e riscos

| Integrante | Papel principal | Responsável por |
|---|---|---|
| Thales | Dev / Telas | Entidade **Cliente** |
| Abner | Dev / Dados | Entidade **Compromisso** |
| Sara | Design e Identidade | Entidade **Servico** |
| Leticia | Doc e Entrega | Entidade **Lembrete** |

---

## 🤖 Bloco 10 — Acordo de trabalho com IA

1. Seguir rigorosamente a estrutura de pastas solicitada (src/ na raiz).
2. Não implementar funcionalidades extras fora do escopo do PRD.
3. Explicar a lógica de concorrência e o uso do Room em cada arquivo modificado.

---

## 🗓️ Bloco 11 — Marcos até 10/12

| Marco | Prazo | Entrega |
|---|---|---|
| M1 | 16/09 | Repositório + CANVAS.md |
| M2 | 30/09 | PRD.md aprovado |
| M3 | 21/10 | Funcionalidade base funcional |
| M4 | 11/11 | Room completo (4 entidades) |
| M6 | 02/12 | APK final e README completo |

---

## 🏁 Bloco 12 — Definição de pronto

- [x] O app abre e não fecha sozinho.
- [x] CRUD completo das 4 entidades funcionando.
- [x] Status de pagamento visível e funcional.
- [x] O app tem nome, ícone e cor (#010736) próprios.
- [x] O `README.md` explica o funcionamento e o build.
