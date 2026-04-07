# 🎲 Jogo do Bicho — Sistema Completo

 Projeto desenvolvido para fins acadêmicos durante disciplina de Laboratório de Produção de Software; ministrada por Ronem Lavareda no Instituto Federal do Amazonas - Campus Parintins.  

---

## 📌 Sobre o Projeto

Este projeto simula o funcionamento do jogo do bicho, permitindo:

* Cadastro de usuários
* Realização de apostas
* Geração de sorteios (draws)
* Cálculo de resultados
* Validação de regras do jogo

A aplicação foi construída com arquitetura organizada, visando escalabilidade e clareza no código.

---

## 🧠 Regras do Jogo

O sistema segue a lógica tradicional:

* Um número aleatório de **0 a 9999** é gerado
* A partir dele, são extraídas:

  * **Milhar** → número completo (ex: 9413)
  * **Centena** → últimos 3 dígitos (413)
  * **Dezena** → últimos 2 dígitos (13)
  * **Grupo** → baseado na dezena

Cada grupo representa um animal, contendo **4 números consecutivos**.

Exemplo:

* Grupo do Galo → números 49, 50, 51, 52

---

## 🏗️ Estrutura do Projeto

### 📦 Backend (Spring Boot)

Organização baseada em camadas:

```
controller/     → Recebe requisições HTTP
service/        → Regras de negócio
repository/     → Acesso ao banco de dados
dto/            → Transferência de dados
entity/   → Representação das entidades
exception/      → Handler de erros básicos
util/           → Emuns e auxiliares
```

---

### 🎨 Frontend

AINDA EM DESENVOLVIMENTO!

---

## 🔐 Usuários

Tipos de usuários:

* **REGULAR**

* ADMIN

* Restrições de acesso a funcionalidades

* Controle de permissões

---

## 🎯 Funcionalidades Principais

* ✅ Cadastro de usuários
* ✅ Login e autenticação
* ✅ Criação de sorteios (draw)
* ✅ Geração automática de números
* ✅ Registro de apostas
* ✅ Validação de apostas vencedoras
* ✅ Tratamento de exceções
* ✅ Regras específicas por tipo de aposta
*  ✅ Histórico de aposta

---

## 🔢 Tipos de Aposta

* Milhar
* Centena
* Dezena
* Grupo

Cada tipo possui:

* Probabilidade diferente
* Retorno/premiação diferente

---

## ⚙️ Tecnologias Utilizadas

### Backend:

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* Jakarta Validation

### Frontend:

* Angular /  **MDBootstrap**

### Banco de Dados:

* MySQL

---

## 📊 Melhorias Futuras

* FRONTEND COMPLETO
* RELATÓRIO WIN/LOSE

---

## ⚠️ Aviso

Este projeto é apenas para fins **educacionais** e **didáticos**, não incentivando práticas de jogos de azar.

---

## 👤 Autor

Desenvolvido por **Robert Marialva Cruz**
