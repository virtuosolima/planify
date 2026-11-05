# 🐙 Guia do GitHub — Projeto Final

Tudo do projeto vive em **um repositório por grupo**. O que não está no GitHub, não foi entregue.

---

## 1. Criar o repositório (uma vez, até 16/09)

Um integrante cria; os outros entram como colaboradores.

1. GitHub → **New repository**
2. Nome: `projeto-final-<nome-do-app>` (tudo minúsculo, sem acento, com hífens)
3. **Public** · marque **Add a README file** · **Add .gitignore → Android**
4. **Settings → Collaborators → Add people** → adicione os outros integrantes e o professor
5. Cole o link do repositório no Bloco de identificação do `CANVAS.md`

---

## 2. Estrutura esperada

```
projeto-final-<app>/
├── README.md              ← cartão de visita do app (modelo em docs/README_MODELO.md)
├── CANVAS.md              ← Canvas do projeto (M1)
├── PRD.md                 ← requisitos do produto (M2)
├── RUBRICA.md             ← cópia da rubrica, para consulta
├── AGENTS.md              ← regras que o Gemini deve seguir neste projeto (M1)
├── .gitignore             ← modelo Android
├── .aiexclude             ← arquivos que a IA não pode ler
├── app/                   ← código do projeto Android
├── docs/
│   ├── USO_DE_IA.md       ← registro obrigatório
│   ├── GUIA_GITHUB.md
│   └── telas/             ← rascunhos e prints das telas
└── loja/
    ├── icone-512.png
    ├── destaque-1024x500.png
    ├── screenshots/
    ├── descricao.md       ← título (30 car.) e descrição (80 car.)
    ├── privacidade.md
    └── app-release.aab
```

---

## 3. Fluxo de trabalho

O `main` é a versão que **sempre funciona**. Ninguém programa direto nele.

```bash
# uma vez, na máquina de cada integrante
git clone https://github.com/<usuario>/projeto-final-<app>.git
cd projeto-final-<app>

# a cada nova tarefa
git checkout main
git pull                          # sempre antes de começar!
git checkout -b feat/tela-lista   # nome da branch = o que você vai fazer

# ... programa, testa no celular ...

git add .
git commit -m "feat: lista de produtos na tela principal"
git push -u origin feat/tela-lista
```

Depois, no GitHub: **Compare & pull request** → descreva o que mudou → outro integrante revisa → **Merge**.

**Nomes de branch:** `feat/` nova funcionalidade · `fix/` correção · `docs/` documentação · `chore/` ajustes gerais

**Mensagens de commit** — curtas, no que foi feito, em português:

| ✅ Bom | ❌ Ruim |
|---|---|
| `feat: salvar produto no Room` | `atualizacao` |
| `fix: tratar erro de rede na busca` | `arrumei umas coisas` |
| `docs: preencher PRD seção 7` | `commit` |
| `chore: ícone e cor principal` | `asdasd` |

### ⚠️ Commits pequenos — isso é avaliado

O histórico de commits é uma das provas de autoria (critério C3 da rubrica). O padrão esperado:

- **Um commit por coisa feita**, no dia em que foi feita. Se a mensagem precisa de "e" duas vezes, são dois commits.
- **Depois de cada bloco aceito no Agent Mode**, commite. Assim dá para ver o que entrou e quando.
- ❌ **O que chama atenção na correção:** um push de 900 linhas em 05/12, ou três commits no projeto inteiro.

O Gemini pode gerar a mensagem de commit — mas ela precisa descrever o que **vocês** fizeram. Mensagem bonita em cima de um despejo gigante de código é o sinal mais visível de que ninguém acompanhou o que entrou.

---

## 4. Organizar as tarefas (Issues)

Cada funcionalidade do MVP vira uma **Issue**. Cada Issue tem um responsável.

1. Aba **Issues → New issue**
2. Título: `RF02 — Adicionar item à lista`
3. Descrição: copie a história de usuário e o critério de aceite do PRD
4. **Assignees:** quem vai fazer · **Labels:** `must` / `should` / `could`
5. No commit que resolve, escreva `Closes #2` — a Issue fecha sozinha ao dar merge

Opcional e recomendado: **Projects → New project → Board** com as colunas *A fazer · Fazendo · Em teste · Pronto*.

---

## 5. Checkpoints — o que o professor confere no GitHub

| Marco | Prazo | O que precisa estar no `main` |
|---|---|---|
| M1 | 16/09 | Repositório criado, todos como colaboradores, `CANVAS.md` e `AGENTS.md` |
| M2 | 30/09 | `PRD.md` preenchido, telas em `docs/telas/`, Issues criadas |
| M3 | 21/10 | App compila; tela principal lista dados; 1 ação do usuário; `try/catch`; comentários de fronteira nos arquivos |
| M4 | 11/11 | Room ou Retrofit funcionando; erros tratados com mensagem ao usuário |
| M5 | 25/11 | Ícone, cores, nome, `applicationId`, versão; `.apk` testado por 2 pessoas de fora |
| M6 | 02/12 | `loja/` completa com `.aab`; `README.md` final; `docs/USO_DE_IA.md` preenchido |
| **Final** | **10/12** | Tag `v1.0` criada e apresentação feita |

**Criar a tag da entrega final:**

```bash
git checkout main
git pull
git tag -a v1.0 -m "Entrega final do projeto"
git push origin v1.0
```

Depois, no GitHub: **Releases → Draft a new release** → escolha a tag `v1.0` → anexe o `.apk` → **Publish**.

---

## 6. Problemas comuns

| Situação | O que fazer |
|---|---|
| `error: failed to push` | Alguém subiu algo antes. `git pull --rebase` e tente de novo. |
| Conflito de merge | Abra o arquivo, escolha o que fica entre `<<<<<<<` e `>>>>>>>`, apague as marcações, `git add` e `git commit`. |
| Subiu arquivo grande demais | O `.gitignore` do Android deve estar no repositório. Nunca suba a pasta `build/`. |
| Chave de API no código | Nunca suba chave. Coloque em `local.properties` (que já está no `.gitignore`), acrescente ao `.aiexclude` e documente isso no PRD. |
| O Agent Mode mexeu em arquivos demais | Rejeite e peça de novo, menor. Antes de aceitar, use a aba de alterações do Git para ver o diff completo. |
| Esqueceu de dar `pull` e o código divergiu | Salve seu trabalho numa branch nova antes de qualquer coisa: `git checkout -b backup/meu-trabalho`. |

---

## 7. Antes de dizer "entreguei"

- [ ] O `main` compila e roda no celular
- [ ] `CANVAS.md`, `PRD.md`, `README.md`, `AGENTS.md` e `docs/USO_DE_IA.md` estão preenchidos e coerentes com o app
- [ ] A pasta `loja/` tem `.aab`, ícone, imagem de destaque, screenshots, descrição e privacidade
- [ ] Todos os integrantes têm commits próprios, distribuídos ao longo dos marcos
- [ ] Cada integrante fez ao menos uma vez, sozinho, uma mudança pequena no app (treino para 10/12)
- [ ] Nenhuma chave ou senha no repositório
- [ ] Tag `v1.0` criada e release publicada com o `.apk`
- [ ] Link do repositório entregue ao professor
