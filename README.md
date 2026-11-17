# Cadastro de Clientes — API REST com Spring Boot

Projeto didático baseado no roteiro **Cadastro de Cliente** (PDF fornecido). Implementa CRUD de clientes e cadastro/consulta de profissões.

## Pré-requisitos
- JDK 21
- Maven 3.9+ (ou Maven instalado pelo IntelliJ IDEA)

## Executar
```bash
mvn spring-boot:run
```
A aplicação estará em `http://localhost:8080`.

## Testes
```bash
mvn test
```

## Passo 1 — cadastrar profissão
```bash
curl -i -X POST http://localhost:8080/profissoes -H "Content-Type: application/json" -d '{"nome":"Desenvolvedor"}'
```
A resposta terá um `id`. Use-o no campo `profissaoId` do cadastro de clientes.

## Passo 2 — cadastrar cliente
```bash
curl -i -X POST http://localhost:8080/clientes -H "Content-Type: application/json" -d '{"nome":"Maria Silva","email":"maria@example.com","telefone":"11999999999","documento":"529.982.247-25","profissaoId":1}'
```
O CPF é apenas um exemplo público de teste de dígitos verificadores; não use dados pessoais reais no H2 de demonstração.

## Endpoints
| Método | Rota | Função |
|---|---|---|
| POST | `/profissoes` | Criar profissão |
| GET | `/profissoes` | Listar profissões |
| GET | `/profissoes/{id}` | Buscar profissão |
| POST | `/clientes` | Criar cliente |
| GET | `/clientes` | Listar clientes |
| GET | `/clientes/{id}` | Buscar cliente |
| PUT | `/clientes/{id}` | Atualizar cliente |
| DELETE | `/clientes/{id}` | Excluir cliente |

## Banco H2
Abra `http://localhost:8080/h2-console` e configure JDBC URL `jdbc:h2:mem:cadastrodb`, usuário `sa`, senha em branco. O banco é **em memória**, portanto os registros desaparecem ao reiniciar a aplicação.

## Arquitetura
`HTTP → Controller → Service → Repository → H2`

- `controller`: recebe JSON e responde HTTP
- `dto`: formatos de entrada e saída, com validações
- `service`: regras de negócio e transações
- `repository`: persistência com Spring Data JPA
- `entity`: mapeamento JPA de tabelas
- `validation`: verificação de dígitos de CPF e CNPJ
- `exception`: respostas padronizadas para erros

## Regras implementadas
- Documento armazenado somente com dígitos, CPF (11) ou CNPJ (14)
- Verificação de dígitos verificadores de CPF/CNPJ
- Documento único por cliente (regra e índice único)
- Profissão obrigatória e existente
- Nome, email, telefone e documento obrigatórios
- Profissões com nomes únicos, sem distinção de maiúsculas na checagem da aplicação
- Exclusão de cliente sem excluir sua profissão

## Limitações didáticas
- Não há autenticação, autorização, paginação nem auditoria
- A validação de telefone é sintática e simplificada
- A validação de CPF/CNPJ verifica dígitos, não situação cadastral na Receita Federal
- Não existe endpoint para editar/excluir profissões (o CRUD solicitado no roteiro é o de clientes)
- H2 em memória e `ddl-auto=create-drop` são escolhas para estudo, não produção
- Em concorrência, a restrição do banco protege duplicidade, retornando conflito

## Sequência de estudo
1. Leia `entity/Cliente.java` e `entity/Profissao.java`.
2. Observe como `repository/ClienteRepository.java` estende `JpaRepository`.
3. Siga `POST /clientes`: Controller → DTO → Service → Repository.
4. Compare os status HTTP 201, 400, 404, 409 e 204.
5. Rode `mvn test` e altere uma regra para observar um teste falhar.
6. Como exercício, adicione paginação, Swagger/OpenAPI e PostgreSQL com Flyway.

## Observação
Projeto gerado como base de estudo. A execução dos testes depende de Maven e das dependências estarem disponíveis no ambiente local.
