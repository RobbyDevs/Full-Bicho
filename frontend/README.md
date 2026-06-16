# Full Bicho Frontend

Frontend Angular standalone + Bootstrap para o projeto Full Bicho.

## Como rodar

```bash
npm install
npm start
```

A API está configurada em `src/environments/environment.ts` apontando para:

```ts
http://localhost:8080
```

## Telas implementadas

- Login
- Cadastro
- Dashboard com carteira, grid dos 25 animais, formulário de aposta, confirmação e resultado
- Histórico com total de apostas, taxa de acerto, total ganho, total perdido e lucro
- Perfil com atualização básica
- Admin com listagem de usuários, criação/finalização de sorteios e consulta de rodadas

## Observações de integração

O backend atual não retorna JWT no login; ele retorna o objeto `User`. Por isso o frontend mantém uma sessão local via `localStorage`. O interceptor já está preparado para enviar `Bearer token` caso o backend passe a retornar um token futuramente.

Foram necessários pequenos ajustes no backend para o frontend funcionar melhor em navegador: CORS global, status HTTP 200 em consultas de apostas, getters completos na entidade `Bet`, data de criação da aposta e endpoints básicos de consulta de sorteios/rodadas.
