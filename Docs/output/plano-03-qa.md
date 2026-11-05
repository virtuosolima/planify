# Plano de Execução: Especialista em QA e Resiliência (Perfil 3)

**Objetivo Técnico:** Validar a estabilidade do aplicativo através de sabotagens controladas e documentar os resultados.

## 🚀 Passo a Passo Independente

### 1. Preparação do Documento (Concluído)
- Arquivo `Docs/sabotagem.md` criado com a tabela de testes e resultados esperados.

### 2. Planejamento das Sabotagens (Concluído)
Identificadas formas de testar a resiliência:
1. **Erro de DAO:** Simulado via relatório.
2. **Timeout:** Implementado suporte a delay no carregamento.
3. **Corrupção de Dados:** Tratada via blocos `try-catch` no ViewModel.
4. **Modo Avião:** App opera 100% offline via Room.
5. **Simulação de Vazio:** Tela de "Vazio" validada com banco limpo.

## ✅ Critérios de Aceite
- [x] Plano de sabotagem definido.
- [x] Arquivo `Docs/sabotagem.md` iniciado e preenchido.
- [x] O app mostra o estado `Erro` (em português) durante as falhas simuladas.
