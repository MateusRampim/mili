CREATE TABLE produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(10) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    em_promocao BOOLEAN DEFAULT FALSE,
    peso DECIMAL(10,2),
    tamanho DECIMAL(10,2)
);

-- Adicionar índices
CREATE INDEX idx_produto_tipo ON produto(tipo);
CREATE INDEX idx_produto_nome ON produto(nome);
