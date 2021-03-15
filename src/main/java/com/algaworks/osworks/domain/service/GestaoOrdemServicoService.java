package com.algaworks.osworks.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.osworks.domain.exception.NegocioException;
import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.model.Comentario;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.model.StatusOrdemServico;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.algaworks.osworks.domain.repository.ComentarioRepository;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ComentarioRepository comentarioRepository;

	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getCodigo())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado."));

		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());

		return ordemServicoRepository.save(ordemServico);
	}

	public void finalizar(Long codOrdemServico) {
		OrdemServico ordemServico = buscar(codOrdemServico);
		
		ordemServico.finalizar();
		
		ordemServicoRepository.save(ordemServico);
	}
	
	public void cancelar(Long codOrdemServico) {
		OrdemServico ordemServico = buscar(codOrdemServico);
		
		ordemServico.cancelar();
		
		ordemServicoRepository.save(ordemServico);
	}

	public Comentario adicionarComentario(Long codOrdemServico, String descricao) {
		OrdemServico ordemServico = buscar(codOrdemServico);

		Comentario comentario = new Comentario();
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		comentario.setDataEnvio(OffsetDateTime.now());

		return comentarioRepository.save(comentario);
	}
	
	private OrdemServico buscar(Long codOrdemServico) {
		return ordemServicoRepository.findById(codOrdemServico)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada."));
	}

}
