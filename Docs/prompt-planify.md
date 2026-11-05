Prompt das telas do Planify

1. Objetivo do aplicativo

Criar as telas do aplicativo Planify, um aplicativo para ajudar empreendedores e profissionais autônomos a organizar clientes, serviços, compromissos e resultados financeiros em um único lugar.

O aplicativo deve ter uma interface simples, profissional, intuitiva e agradável, facilitando o uso no dia a dia.

2. Público

O público principal são pequenos empreendedores e profissionais autônomos, principalmente entre 25 e 45 anos, como:

* Cabeleireiros
* Manicures
* Barbeiros
* Personal trainers
* Consultores
* Prestadores de serviços locais

A interface deve transmitir organização, confiança e praticidade.

3. Stack utilizada

Considerar a stack definida para o projeto:

* Kotlin
* Jetpack Compose
* Material 3
* Room
* Navigation Compose
* Coroutines
* Flow
* AlarmManager / WorkManager

O projeto possui como requisito mínimo o Android API 24 e utiliza target SDK 35.

O protótipo visual deve ser pensado para uma aplicação mobile Android.

4. Entidades do aplicativo

Considerar as seguintes entidades do Planify:

Cliente

* id
* nome
* telefone

Serviço

* id
* nome
* preco

Compromisso

* id
* clienteId
* servicoId
* dataHora
* valor

Lembrete

* id
* compromissoId
* antecedenciaMinutos
* ativo

Os dados apresentados nas telas podem utilizar informações fictícias apenas para demonstração do funcionamento da interface.

5. Identidade visual

A cor principal do aplicativo deve ser:

#010736

Utilizar uma identidade visual:

* Moderna
* Limpa
* Profissional
* Minimalista
* Fácil de entender
* Adequada para uso cotidiano
* Com boa hierarquia visual
* Com bastante espaço em branco

Utilizar cards, botões e campos com cantos levemente arredondados, sem exageros.

A tipografia deve ser moderna, legível e bem hierarquizada.

Os ícones devem ser simples e coerentes com cada ação.

6. Telas principais

Criar as seguintes telas:

6.1 Principal — Agenda e Resumo

Deve apresentar:

* Resumo mensal
* Atendimentos
* Faturamento
* Clientes
* Média
* Compromissos do dia

Também deve permitir:

* Navegar entre os dias
* Visualizar os compromissos
* Criar um novo compromisso
* Acessar clientes
* Acessar serviços

Quando não houver compromissos no dia selecionado, mostrar:

Nenhum compromisso registrado para este dia.

6.2 Lista de Clientes

Apresentar os clientes cadastrados.

Cada item deve mostrar informações relevantes, como:

* Nome
* Telefone

Permitir:

* Adicionar cliente
* Editar cliente
* Excluir cliente
* Selecionar cliente para um compromisso

6.3 Cadastro de Cliente

Campos:

* Nome
* Telefone

Ações:

* Salvar
* Cancelar/voltar

Campos obrigatórios devem possuir validação.

6.4 Catálogo de Serviços

Apresentar os serviços cadastrados e seus respectivos preços.

Permitir:

* Adicionar serviço
* Editar serviço
* Excluir serviço

6.5 Cadastro de Serviço

Campos:

* Nome
* Preço

Ações:

* Salvar
* Cancelar/voltar

6.6 Cadastro de Compromisso

Campos:

* Cliente
* Serviço
* Data
* Horário
* Valor
* Lembrete

O lembrete deve permitir as opções:

* 15 minutos antes
* 1 hora antes
* 1 dia antes

Ações:

* Salvar
* Cancelar/voltar

Depois de salvo, o compromisso deve aparecer na agenda e seu valor deve ser considerado no faturamento mensal.

7. Navegação

A navegação deve seguir uma estrutura simples e intuitiva.

Principal → Clientes

Principal → Lista de Clientes → Adicionar → Cadastro de Cliente

Depois de salvar, retornar para a lista de clientes.

Principal → Serviços

Principal → Catálogo de Serviços → Adicionar → Cadastro de Serviço

Depois de salvar, retornar para o catálogo.

Principal → Novo Compromisso

Principal → Novo Compromisso → Formulário de Compromisso

Depois de salvar, retornar para a tela principal e exibir o novo compromisso na agenda.

8. Estados e dados de demonstração

Utilizar dados fictícios realistas para demonstrar o funcionamento das telas.

Exemplos:

* Clientes com nomes e telefones fictícios
* Serviços com nomes e preços em reais
* Compromissos distribuídos em diferentes horários
* Diferentes valores de atendimento
* Diferentes situações de lembrete

Os dados devem fazer com que o aplicativo pareça funcional durante a demonstração.

Utilizar valores em Real brasileiro (R$).

Também devem ser demonstrados estados vazios quando não houver dados.

9. Comportamentos importantes

A interface deve representar os principais comportamentos do aplicativo:

* Adicionar, editar e excluir clientes
* Adicionar, editar e excluir serviços
* Criar compromissos
* Selecionar cliente e serviço
* Definir data e horário
* Definir valor
* Configurar lembretes
* Atualizar os indicadores do resumo mensal
* Exibir confirmações antes de exclusões
* Exibir mensagens de erro quando necessário

Mensagens previstas:

Preencha os campos obrigatórios antes de salvar.

Não foi possível salvar os dados. Tente novamente.

Compromisso salvo, mas o lembrete não pôde ser ativado.

Tem certeza que deseja excluir este registro?

10. Direção geral da interface

A interface deve priorizar:

* Clareza
* Hierarquia visual
* Facilidade de navegação
* Poucos elementos por tela
* Informações importantes facilmente identificáveis
* Botões e ações fáceis de encontrar
* Boa adaptação para telas de celular

A experiência deve parecer a de um aplicativo real utilizado diariamente por um profissional autônomo.

Não exagerar na quantidade de cards, cores, ícones ou elementos decorativos.

11. Importante — evitar aparência de IA

Não criar uma interface com aparência genérica ou claramente gerada por inteligência artificial.

Evitar:

* Gradientes excessivos
* Elementos futuristas
* Efeitos 3D
* Glassmorphism exagerado
* Brilhos e efeitos neon
* Ícones decorativos sem função
* Cards excessivamente grandes ou numerosos
* Layouts genéricos de templates de IA
* Excesso de elementos arredondados
* Combinações de cores chamativas sem necessidade
* Textos ou elementos visuais que pareçam artificiais

A interface deve parecer um produto real, profissional e cuidadosamente projetado por um designer, e não uma demonstração genérica de IA.

Priorizar simplicidade, consistência, funcionalidade e boa experiência de usuário.

12. Referências

Usar o Notion como referência de organização, clareza e hierarquia de informações.

A referência não deve ser copiada literalmente. Utilizar apenas os princípios de organização e simplicidade que forem adequados ao Planify.

Também considerar princípios de:

* UX
* Gestalt
* Hierarquia visual
* Neuroestética

13. Restrições

Não adicionar funcionalidades que não fazem parte do escopo definido para o Planify.

Não incluir:

* Pagamentos online reais
* Chat
* Mapas ou localização
* Sincronização em nuvem
* Funcionalidades de rede social
* Funcionalidades relacionadas a pets

O foco deve permanecer em:

Clientes + Serviços + Compromissos + Lembretes + Agenda + Resumo financeiro.

14. Resultado esperado

Gerar um protótipo mobile completo das telas principais do Planify, com navegação coerente entre elas, dados de demonstração e aparência profissional.

O resultado deve ser funcional para demonstração e seguir a identidade visual e os requisitos definidos neste documento.
