# Plano de Execução: Integrador e Documentador (Perfil 4)

**Objetivo Técnico:** Padronizar os recursos de texto, gerenciar o controle de versão e preparar a entrega final.

## 🚀 Passo a Passo Independente

### 1. Padronização de Strings (Pode ser feito agora)
Edite o arquivo `src/src/main/res/values/strings.xml`:
- Adicione as chaves em português: `estado_carregando`, `estado_vazio_titulo`, `estado_erro_titulo`, `btn_tentar_novamente`.

### 2. Gestão do Git (Pode ser feito agora)
- Crie a branch: `git checkout -b feature/estados-da-tela`.
- Garanta que os commits sejam granulares e sigam o padrão: `feat:`, `fix:`, `docs:`.

### 3. Revisão do PR (Pode ser feito ao final)
- Verifique se o código segue a regra de "No Else" no `when`.
- Revise o `docs/sabotagem.md` gerado pelo QA.
- Abra o Pull Request detalhando as mudanças e garantindo que todos os estados usem os nomes em Português.

## 🤖 Instruções de IA (Gemini)
- "Gemini, sugira mensagens amigáveis em português para estados de 'Erro de conexão' e 'Nenhum dado encontrado' para um aplicativo de agendamento."
- "Como organizar commits no Git seguindo o padrão Conventional Commits para uma feature de estados de UI."

## ✅ Critérios de Aceite
- [ ] Strings externalizadas em Português BR.
- [ ] Branch criada e commits padronizados.
- [ ] Pull Request aberto com a documentação anexada.
