CREATE TABLE ordens_servico (
	codigo BIGINT NOT NULL AUTO_INCREMENT, 
    cod_cliente BIGINT NOT NULL, 
    descricao TEXT NOT NULL, 
    preco DECIMAL(10, 2) NOT NULL, 
    status VARCHAR(20) NOT NULL, 
    data_abertura DATETIME NOT NULL, 
    data_finalizacao DATETIME, 
    
    PRIMARY KEY (codigo)
);

ALTER TABLE ordens_servico add constraint fk_ordens_servico_clientes FOREIGN KEY (cod_cliente) REFERENCES clientes(codigo);