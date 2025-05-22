# API REST (Spring Boot) - Gerenciamento de Pagamentos em Jogos de Futebol
Desenvolvimento de uma **API RESTful** utilizando **Java + Spring Boot** e seguindo o padrão **MVC**. O projeto tem como objetivo gerenciar pagamentos de mensalistas em jogos de futebol.

---
## Modelo conceitual
* **Descrição**: a modelagem consiste num relacionamento **(1:n)** entre as entidades **jogador** e **pagamento**, onde um jogador pode receber vários pagamentos e cada pagamento diz respeito a um único jogador.
![Modelo Conceitual](https://github.com/user-attachments/assets/6af438c3-513f-4836-93a2-f3a8483093b0)
---

## Tecnologias utilizadas
- Java 17+
- Spring Boot
  - Spring Data JPA
  - Spring Web
- Maven

---

## Como executar o projeto

```bash
# Clone o repositório
git clone https://github.com/guilhermebastosalves/API-futebol

# Acesse o diretório
cd API-futebol

# executar o projeto
./mvn spring-boot:run
```
---
## Autor
### Guilherme Bastos Alves ###
