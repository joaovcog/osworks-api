package com.algaworks.osworks.api.model;

import javax.validation.constraints.NotNull;

public class CodClienteInput {

	@NotNull
	private Long codigo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

}
