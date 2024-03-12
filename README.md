# Solução Completa para Consulta de Saldo e Transferência entre Contas na AWS #
Projeto para realizar as Operações de Consulta de Saldo e Transferência entre contas.

## Visão Geral:

Esta solução abrangente oferece aos clientes a capacidade de realizar consultas de saldo e transferências entre contas de maneira eficiente, resiliente e escalável na AWS. A arquitetura se baseia em microsserviços, containers, bancos de dados gerenciados e outros serviços da AWS para garantir alta disponibilidade, latência mínima e capacidade de lidar com alto volume de transações.

## Arquitetura de Aplicação para API de Transferência Bancária :shipit:
  
![Alt text](Diagram.drawio.png?raw=true "Title")

## Design Patterns para API de Transferência Bancária :shipit:
  
### [CAMADA DOMAIN]
Esta é a camada central da nossa aplicação e a mais importante, ela é a mais próxima das regras de negócio da aplicação, ou seja, e quando definidas não sofrem tantas mudanças, como nas outras camadas e ainda é independente de todas as outras camadas.

* UseCases: nessa camada ficará as classes responsáveis em dizer quais as ações o usuário poderá executar no sistema, como comprar um produto ou até mesmo cancelar um pedido.
> “O software da camada de casos de uso contém as regras de negócio específicas da aplicação”

Trecho de: Robert C. Martin. “Arquitetura Limpa (Robert C. Martin)”. Apple Books.

* Entities: As entidades são uma representação dos objetos de negócio da aplicação.
> “As Entidades reúnem as Regras Cruciais de Negócios da empresa inteira”

Trecho de: Robert C. Martin. “Arquitetura Limpa (Robert C. Martin)”. Apple Books.

* Repositories: Os repositories nessa camada são a representação por meio de interfaces abstratas, enquanto a implementação é feita em outra camada.
  
### [CAMADA DATA]

Esta camada é a responsável pela comunicação entre nossa aplicação e o mundo exterior, como exemplo, a comunicação com uma API ou um banco de dados.

* Repositories: esta camada é a implementação dos repositories abstratos, contidos em outra camada, a implementação faz operações como busca, remoção, atualização ou inserção de dados entre nossa aplicação a
camada datasources, fazendo assim o meio de campo entre os dois extremos.
* Models: são espelhos das entities, porém podem conter métodos, como conversão de dados de entrada e saída, hash code e outros.
* DataSources: executará solicitações HTTP GET na API ou simplesmente armazenará em cache os dados usando o banco de dados h2.

### Arquitetura de Solução para API de Transferência Bancária

![Alt text](projeto-contas.jpg?raw=true "Title")


