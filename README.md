# 🤟❓ Libras Forum Project

> **Trabalho Final da Disciplina de Banco de Dados 1**

Aplicação de fórum voltada à comunidade de Libras (Língua Brasileira de Sinais), desenvolvida em **Java** com banco de dados **PostgreSQL**. O sistema permite o cadastro de usuários e a criação de publicações (perguntas, respostas e comentários), simulando um ambiente de fórum regionalizado.

Esse projeto roda diretamente na CLI. Foi desenvolvido principalmente para desenvolver conhecimentos de **Banco de Dados**, **MVC** e **Orientação a Objetos**.

## 📋 Funcionalidades

| # | Funcionalidade |
|---|----------------|
| 1 | Inserir um novo usuário |
| 2 | Inserir uma nova publicação (pergunta, resposta ou comentário) |
| 3 | Listar todos os usuários |
| 4 | Listar todas as publicações |
| 5 | Exibir publicações com os dados de seus autores |
| 6 | Exibir a pergunta com mais likes da mesma região de um usuário (que não seja o autor) |

## 🗄️ Modelo de Dados

O banco de dados é composto por três tabelas principais:

- **`regiao`** — Armazena cidade e estado, permitindo a regionalização do fórum.
- **`usuario`** — Cadastro de usuários com nome, e-mail, região, avaliação, instituição, profissão e flag de administrador.
- **`publicacao`** — Publicações do fórum com suporte a tipos (`P` = Pergunta, `R` = Resposta, `C` = Comentário), likes/deslikes, menções a outros usuários e anexo de arquivos.

## 🏗️ Estrutura do Projeto

```
libras-forum-project/
├── src/
│ ├── Beans/ # Entidades de Dados
│ │ ├── PublicacaoBean.java
│ │ ├── RegiaoBean.java
│ │ └── UsuarioBean.java
│ ├── Controllers/ # Lógica de Negócio
│ │ ├── PublicacaoController.java
│ │ └── UsuarioController.java
│ ├── Models/ # Modelos de acesso ao BD (DAO)
│ │ ├── PublicacaoModel.java
│ │ ├── RegiaoModel.java
│ │ └── UsuarioModel.java
│ ├── Conexao.java # Conexão com PostgreSQL
│ └── Principal.java # Classe principal (Menu interativo)
├── startDb.sql # Script SQL de criação e população do banco
├── compile.sh # Script de compilação
└── run.sh # Script de execução
```

## 🛠️ Tecnologias

- **Java** (aplicação CLI)
- **PostgreSQL** (banco de dados relacional)
- **JDBC** (driver `postgresql-42.2.2.jre7.jar`)

## 🚀 Como Executar

### Pré-requisitos

- Java 11 JDK instalado
- PostgreSQL instalado e em execução
- Driver JDBC do PostgreSQL em `lib/postgresql-42.2.2.jre7.jar`

### 1. Configurar o banco de dados

Crie o banco e execute o script de inicialização:

```
psql -U seu_usuario -d seu_banco -f "startDb.sql"
```

Recomendo:

```
psql -U postgres -d libras -f "startDb.sql"
```

### 2. Compilar o projeto

```bash
chmod +x compile.sh
./compile.sh
```

### 3. Executar a aplicação

```bash
chmod +x run.sh
./run.sh
```

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos.
