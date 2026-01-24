# 🚀 CryptoFolio Manager API

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-green?style=for-the-badge&logo=spring)
![MongoDB](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker)

> Uma API RESTful robusta e de alta performance para gerenciamento de portfólios de criptomoedas, focada em precisão financeira e dados em tempo real.

---

## 📖 Sobre o Projeto

O **CryptoFolio Manager** é uma solução Backend desenvolvida para resolver um problema comum de investidores: o acompanhamento preciso do lucro/prejuízo real de seus ativos. Diferente de simples visualizadores de preço, este sistema implementa o cálculo de **Preço Médio Ponderado**, permitindo um histórico contábil fiel das transações.

O sistema integra-se à API externa da **CoinGecko** para obter cotações em tempo real, utiliza **Redis** para estratégias de caching (reduzindo latência e custos de requisição) e **MongoDB** para persistência escalável dos dados.

### 🎯 Principais Funcionalidades

* **Autenticação Segura:** Login e Registro via **JWT (JSON Web Token)** com criptografia BCrypt.
* **Gestão de Transações:** Registro de compras e vendas com validação de saldo e recalculo automático de preço médio.
* **Integração Externa:** Consulta de preços em tempo real via **OpenFeign** (CoinGecko API).
* **Alta Performance:** Implementação de Cache-Aside pattern com **Redis** para cotações de moedas.
* **Fail-Fast Validation:** Verificação de existência da moeda antes da persistência para garantir integridade dos dados.
* **Documentação Interativa:** API totalmente documentada com **Swagger UI (OpenAPI 3)**.

---

## 🛠️ Tech Stack & Arquitetura

O projeto segue uma arquitetura em camadas focada em **Clean Code** e princípios **SOLID**.

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3.x
* **Persistência:** Spring Data MongoDB
* **Caching:** Spring Data Redis
* **Segurança:** Spring Security 6 + Java-JWT (Auth0)
* **Cliente HTTP:** Spring Cloud OpenFeign
* **Ferramentas:** Lombok, Docker Compose, Maven
* **Documentação:** SpringDoc OpenAPI (Swagger)

---

## 🧠 Regra de Negócio: Preço Médio Ponderado

Um dos diferenciais técnicos deste projeto é a lógica de negócio implementada no `PortfolioService`. Ao realizar uma nova compra, o sistema não apenas soma a quantidade, mas atualiza o preço médio do ativo seguindo a fórmula:

$$PM_{novo} = \frac{(Qtd_{atual} \times PM_{atual}) + (Qtd_{nova} \times Preço_{novo})}{Qtd_{total}}$$

Isso garante que o cálculo de **Lucro/Prejuízo (P&L)** exibido no dashboard (`GET /portfolio`) seja matematicamente preciso em relação ao histórico do investidor, comparando o Custo Médio vs. Preço de Mercado Atual (Redis/CoinGecko).

---

## 🚀 Como Executar

### Pré-requisitos
* Java 17+
* Docker & Docker Compose
* Maven

### 1. Clone o repositório
```bash
git clone [https://github.com/AuanJulio/cryptofolio-manager.git](https://github.com/AuanJulio/cryptofolio-manager.git)
cd cryptofolio-manager
```

## ⚙️ Configuração de Ambiente

O projeto utiliza **variáveis de ambiente** para garantir a segurança de dados sensíveis.  
Você deve fornecer as seguintes chaves ao rodar a aplicação:

- **JWT_SECRET**: Uma string aleatória para assinar os tokens de autenticação.
- **COINGECKO_API_KEY**: Sua chave de API da CoinGecko.

---

## 🐳 Subindo a Infraestrutura (MongoDB & Redis)

Utilize o **Docker Compose** para iniciar os containers do banco de dados e do cache:

```bash
docker-compose up -d
```

---

## ▶️ Executando a Aplicação

Você pode rodar a aplicação via **Maven**, passando as variáveis de ambiente como argumentos:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--cryptofolio.jwt.secret=segredo_super_secreto --cryptofolio.client.coingecko.key=SUA_KEY_AQUI"
```

A aplicação iniciará na porta **8080**.

---

## 📚 Documentação da API

A documentação completa, interativa e em português está disponível via **Swagger UI**.

Após iniciar a aplicação, acesse:

👉 http://localhost:8080/swagger/index.html

---

## 🔌 Endpoints Principais

| Método | Endpoint | Descrição |
|------|---------|-----------|
| POST | `/auth/register` | Cria uma nova conta de usuário 🔓 |
| POST | `/auth/login` | Autentica e retorna o Token JWT 🔓 |
| POST | `/portfolio/transaction` | Registra compra/venda de ativos 🔐 |
| GET | `/portfolio` | Retorna o saldo consolidado e P&L 🔐 |

---

## 🧪 Padrões de Código e Decisões Técnicas

- **DTO Pattern**  
  Uso estrito de *Records* (`UserRequest`, `PortfolioResponse`) para transferência de dados, garantindo imutabilidade e evitando exposição das entidades de persistência.

- **Interface-Driven Controllers**  
  As anotações do Swagger foram extraídas para interfaces (ex: `PortfolioControllerDocs`), mantendo as classes Controller limpas e focadas apenas na orquestração HTTP.

- **Global Exception Handling**  
  Uso de `@RestControllerAdvice` para padronizar respostas de erro (400, 404) em um formato JSON amigável e consistente.

- **Privacy by Design**  
  Os endpoints de portfólio não exigem ID na URL (`/portfolio` em vez de `/portfolio/{id}`).  
  O sistema infere o usuário através do Token de Segurança, prevenindo acesso não autorizado a dados de terceiros.

---

## 📞 Contato

**Auan Julio Galvão dos Santos**  
📧 Email: auanjulio13@gmail.com  
🐙 GitHub: https://github.com/AuanJulio

---

Projeto desenvolvido como **portfólio**, demonstrando domínio em **Java, Spring Boot e Arquitetura de Software**.
