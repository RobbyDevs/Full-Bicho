# Full-Bicho

Sistema web desenvolvido para fins acadêmicos durante a disciplina de **Laboratório de Produção de Software**, ministrada pelo professor **Ronem Lavareda**, no **Instituto Federal do Amazonas — Campus Parintins**.

O projeto simula uma plataforma de apostas baseada no jogo do bicho, com foco em arquitetura, integração entre front-end e back-end, persistência de dados, autenticação, regras de negócio e boas práticas de desenvolvimento de software.

> Projeto desenvolvido exclusivamente para fins educacionais.

## Tecnologias utilizadas

### Front-end

* Angular
* TypeScript
* Bootstrap
* Bootstrap Icons
* HTML5
* CSS3

### Back-end

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* Maven
* Lombok

### Banco de dados

* MySQL

### Documentação e DevOps

* Swagger / OpenAPI
* GitHub Actions

## Arquitetura

O projeto segue uma arquitetura monolítica baseada no padrão **MVC (Model-View-Controller)**, separando responsabilidades entre camadas de apresentação, controle, regra de negócio e persistência.

No back-end, a estrutura principal é organizada em:

* **Controller**: responsável por expor os endpoints da API.
* **Service**: responsável pelas regras de negócio.
* **Repository**: responsável pela comunicação com o banco de dados.
* **Model/Entity**: representa as entidades persistidas.
* **DTO**: utilizado para transportar dados entre cliente e servidor.

No front-end, a aplicação é organizada em:

* **Pages/Components**: telas e componentes reutilizáveis.
* **Services**: comunicação com a API.
* **Models/Interfaces**: representação dos dados usados pela aplicação.
* **Guards**: controle de acesso às rotas.
* **Interceptors**: preparação para tratamento global de requisições.

## Funcionalidades

* Cadastro de usuários
* Login de usuários
* Perfil do usuário
* Carteira com saldo inicial
* Listagem dos animais disponíveis
* Realização de apostas
* Seleção do tipo de aposta
* Geração de sorteios
* Processamento de vitórias e derrotas
* Histórico de apostas
* Painel administrativo
* Listagem de usuários no painel administrativo
* Documentação da API com Swagger

## Regras principais do sistema

O sistema trabalha com 25 animais, cada um associado a 4 dezenas.

Cada sorteio possui 5 rodadas:

* Cabeça
* Segunda
* Terceira
* Quarta
* Quinta

O resultado de cada rodada é formado por um número de 0 a 9999. Os dois últimos dígitos desse número definem o grupo/animal correspondente.

As apostas podem considerar diferentes modalidades, como:

* Grupo
* Dezena
* Centena
* Milhar

A carteira do usuário é atualizada de acordo com o resultado da aposta.

## Segurança

As senhas dos usuários são armazenadas de forma criptografada utilizando hash com BCrypt.

A autenticação JWT está prevista como melhoria futura do projeto.

## Como executar o projeto

### Pré-requisitos

Antes de iniciar, é necessário ter instalado:

* Java 17 ou superior
* Node.js
* Angular CLI
* MySQL
* Maven ou Maven Wrapper

## Back-end

Acesse a pasta do back-end:

```bash
cd backend/app
```

Execute o projeto:

```bash
./mvnw spring-boot:run
```

No Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

A API será executada em:

```text
http://localhost:8080
```

## Front-end

Acesse a pasta do front-end:

```bash
cd frontend
```

Instale as dependências:

```bash
npm install
```

Execute a aplicação:

```bash
npm start
```

O front-end será executado em:

```text
http://localhost:4200
```

## Documentação da API

Com o back-end em execução, acesse:

```text
http://localhost:8080/swagger-ui.html
```

Também é possível acessar a especificação OpenAPI em:

```text
http://localhost:8080/v3/api-docs
```

## Estrutura geral do projeto

```text
Full-Bicho/
├── backend/
│   └── app/
│       ├── src/
│       ├── pom.xml
│       └── mvnw
│
├── frontend/
│   ├── src/
│   ├── package.json
│   └── angular.json
│
└── README.md
```

## Status do projeto

Projeto em desenvolvimento acadêmico.

Funcionalidades principais implementadas:

* Back-end com Spring Boot
* Front-end com Angular
* Integração entre front-end e back-end
* Cadastro e login de usuários
* Sistema de apostas
* Histórico de apostas
* Painel administrativo
* Documentação com Swagger
* Hash de senhas com BCrypt

Melhorias futuras:

* Implementação completa de autenticação JWT
* Proteção de rotas administrativas no back-end
* Testes automatizados
* Melhorias na interface administrativa
* Tratamento global de exceções
* Deploy da aplicação

## Autor

Projeto desenvolvido por Robert Marialva Cruz, acadêmico do curso de Engenharia de Software do Instituto Federal do Amazonas — Campus Parintins.

## Licença

Este projeto é de uso acadêmico e não possui finalidade comercial, tampouco incentiva prática de jogos de azar.
