INSERT INTO clientes (nome, cpf) VALUES
    ('João Silva', '12345678900'),
    ('Maria Oliveira', '09876543210'),
    ('Pedro Souza', '32165498700');

INSERT INTO contas (numero_conta, saldo, status, cliente) VALUES
    ('123456', 1000.00,'ATIVA', 1),
    ('789456', 2000.00,'ATIVA', 2),
    ('321654', 3000.00,'DESATIVADA', 3);

INSERT INTO transferencias (cpf,status, data_hora, valor, conta_origem, conta_destino) VALUES
     ('12345678900','PENDENTE','2023-11-14', 500.00, 1, 2),
     ('09876543210','CONCLUIDA','2023-11-15', 1000.00, 2, 3),
     ('32165498700','CANCELADA','2023-11-16', 200.00, 3, 1);

