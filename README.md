# Solução Completa para Consulta de Saldo e Transferência entre Contas na AWS #

## Objetivo:

Avaliar a aptidão técnica do candidato à vaga de desenvolvedor de software, verificando seus conhecimentos em engenharia de software e arquitetura de soluções, além de sua aderência aos valores e cultura.

## Desafio:

Desenvolver e apresentar uma solução completa para uma API REST que permita aos clientes realizar consultas de saldo e transferências entre contas, com os seguintes requisitos:

### Desafio de Engenharia de Software:

* Funcionalidades:
Consultar saldo do cliente (utilizando API de cadastro mock).
Validar se a conta corrente está ativa.
Validar se o cliente tem limite disponível na conta corrente para realizar a transferência.
Validar se a transferência excedeu o limite diário de R$ 1.000,00.
Notificar o BACEN (mock) de forma síncrona sobre o sucesso da transação.
Requisitos técnicos:
Linguagem de desenvolvimento: Java/Spring Boot.
Testes unitários e automatizados.
Exploração de design patterns.
Implementação de padrões de resiliência.
Requisitos de performance:
Latência inferior a 100ms.
Suporte a 6 mil transações por segundo.
### Desafio de Arquitetura de Solução:

* Desenvolver um desenho de solução na AWS para a API:
Proposta de escalonamento para lidar com oscilações de carga.
Proposta de observabilidade da solução.
Justificativa da escolha do banco de dados.
Justificativa do uso de cache (se aplicável).
Garantia de tempo de resposta inferior a 100ms.
Suporte a alto throughput (6 mil transações por segundo).
Estratégia para lidar com falhas de dependências sem impactar o cliente.
Estratégia para lidar com throttling (HTTP status 429) do BACEN.

## Entregas:

### Desenvolvimento da API REST:

Código-fonte da API em Java/Spring Boot.
Documentação da API.
Testes unitários e automatizados.
Descrição dos design patterns e padrões de resiliência utilizados.
### Arquitetura de Solução:

Diagrama da arquitetura da solução na AWS.
Descrição da proposta de escalonamento.
Descrição da proposta de observabilidade.
Justificativa da escolha do banco de dados.
Justificativa do uso de cache (se aplicável).
Descrição da estratégia para lidar com falhas de dependências.
Descrição da estratégia para lidar com throttling do BACEN.
## Critérios de Avaliação:

### Desenvolvimento da API REST:
Qualidade do código e organização do projeto.
Abrangência dos testes unitários e automatizados.
Eficiência e performance da API.
Correta implementação dos design patterns e padrões de resiliência.

### Arquitetura de Solução:
Clareza e detalhamento do diagrama da arquitetura.
Adequação da solução aos requisitos de performance e escalabilidade.
Eficiência da proposta de observabilidade.
Justificativa consistente das escolhas tecnológicas.
Eficácia das estratégias para lidar com falhas e throttling.

## Observações:

O candidato pode utilizar frameworks e bibliotecas de sua preferência para o desenvolvimento da API.
A solução de arquitetura deve considerar os custos e a segurança da infraestrutura AWS.
O candidato deve estar preparado para apresentar e defender sua solução em uma entrevista técnica.

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

* CloudFront: Atua como uma CDN (Content Delivery Network), distribuindo o conteúdo da API de forma rápida e segura para usuários globais. O CloudFront armazena em cache as APIs em data centers ao redor do mundo, reduzindo a latência e o tempo de carregamento.

* API Gateway: Serve como um portal único para gerenciar e proteger o acesso à API de transferência. O API Gateway permite:

* Gerenciamento de APIs: Criar, publicar e monitorar APIs.
Segurança: Controlar o acesso à API através de autenticação e autorização.
Monitoramento: Obter insights sobre o desempenho e uso da API.
Target Group: Define um conjunto de instâncias do Amazon EC2 que podem receber tráfego da API. O Target Group permite:

* Balanceamento de carga: Distribuir o tráfego entre as instâncias do EC2 de forma eficiente.
Auto Scaling: Adicionar ou remover instâncias do EC2 automaticamente com base na demanda.

* ECS (Elastic Container Service): Orquestra containers que executam a API de transferência. O ECS oferece:

Gerenciamento de containers: Iniciar, parar e monitorar containers.
Auto Scaling: Ajustar automaticamente a quantidade de containers em execução com base na demanda.
Auto Scaling: Ajusta automaticamente a quantidade de instâncias do EC2 ou containers em execução com base na demanda. O Auto Scaling garante:

Escalabilidade: A API pode lidar com o aumento ou a diminuição do tráfego sem problemas.
Eficiência: Reduz custos ao provisionar recursos apenas quando necessário.

* CloudWatch: Monitora o desempenho e a saúde da API de transferência. O CloudWatch fornece:

Métricas: Coleta e armazena métricas como tempo de resposta, taxa de erros e utilização de recursos.
Alarmes: Notifica sobre problemas de desempenho ou falhas na API.

* RDS (Relational Database Service): Armazena dados transacionais da API de transferência. O RDS oferece:

Banco de dados gerenciado: Reduz a necessidade de gerenciar o banco de dados manualmente.
Alta disponibilidade: Garante que a API esteja sempre disponível.
Segurança: Protege os dados contra acessos não autorizados.

* Load Balancer: Distribui o tráfego entre várias instâncias do EC2 ou containers. O Load Balancer garante:

Escalabilidade: A API pode lidar com o aumento do tráfego sem problemas.
Alta disponibilidade: A API permanece disponível mesmo se uma instância falhar.

* VPCLink: Conecta a API de transferência à sua rede VPC (Virtual Private Cloud). O VPCLink permite:

Segurança: Protege a API contra acessos não autorizados da internet pública.
Isolamento: Mantém a API em uma rede privada separada.

* Resumo:

Cada recurso contribui para a funcionalidade, confiabilidade e segurança da API de transferência:

CloudFront: Distribuição rápida e segura da API.
API Gateway: Gerenciamento, segurança e monitoramento da API.
Target Group: Balanceamento de carga e auto scaling.
ECS: Execução e gerenciamento de containers.
Auto Scaling: Escalabilidade e eficiência de recursos.
CloudWatch: Monitoramento de desempenho e saúde da API.
RDS: Armazenamento seguro e confiável de dados.
Load Balancer: Distribuição de tráfego e alta disponibilidade.
VPCLink: Segurança e isolamento da API.
Benefícios da Integração:

* Desempenho aprimorado: Menor latência e tempo de resposta.
Escalabilidade: Lidar com o aumento do tráfego sem problemas.
Alta disponibilidade: API sempre disponível.
Segurança: Proteção contra acessos não autorizados.
Eficiência de custos: Provisionamento de recursos apenas quando necessário.


