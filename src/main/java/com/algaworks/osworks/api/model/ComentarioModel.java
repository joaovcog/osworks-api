package com.algaworks.osworks.api.model;

import java.time.OffsetDateTime;

public class ComentarioModel {

	private Long codigo;
	private String descricao;
	private OffsetDateTime dataEnvio;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public OffsetDateTime getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(OffsetDateTime dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

}
