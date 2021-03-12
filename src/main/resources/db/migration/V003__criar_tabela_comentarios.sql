CREATE TABLE comentarios (
	codigo BIGINT NOT NULL AUTO_INCREMENT, 
    cod_ordem_servico BIGINT NOT NULL, 
    descricao TEXT NOT NULL, 
    data_envio DATETIME NOT NULL, 
    
    PRIMARY KEY (codigo)
);

ALTER TABLE comentarios add constraint fk_comentarios_ordens_servico FOREIGN KEY (cod_ordem_servico) REFERENCES ordens_servico(codigo);