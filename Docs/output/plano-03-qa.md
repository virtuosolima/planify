# Plano de Execução: Especialista em QA e Resiliência (Perfil 3)

**Objetivo Técnico:** Validar a estabilidade do aplicativo através de sabotagens controladas e documentar os resultados.

## 🚀 Passo a Passo Independente

### 1. Preparação do Documento (Pode ser feito agora)
- Crie o arquivo `docs/sabotagem.md` e defina a tabela de testes antes mesmo de começar as sabotagens.

### 2. Planejamento das Sabotagens (Pode ser feito agora)
Identifique onde você pode "quebrar" o código sem depender da UI estar pronta:
1. **Erro de DAO:** Planeje mudar o nome da query no arquivo DAO.
2. **Timeout:** Planeje inserir um `delay` infinito no Repository.
3. **Corrupção de Dados:** Planeje forçar uma conversão de tipo errada.
4. **Modo Avião:** Teste o comportamento da rede.
5. **Simulação de Vazio:** Garanta que o banco esteja limpo.

## 🤖 Instruções de IA (Gemini)
- "Gemini, sugira 5 formas criativas de sabotar um app Android com Room e Compose para testar se ele crasha ou mostra a tela de Erro corretamente."
- "Como documentar testes de resiliência em Markdown?"

## ✅ Critérios de Aceite
- [ ] Plano de sabotagem definido.
- [ ] Arquivo `docs/sabotagem.md` iniciado.
- [ ] O app deve mostrar o estado `Erro` (em português) durante as falhas.
