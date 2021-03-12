package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.algaworks.osworks.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CadastroClienteService cadastroClienteService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return cadastroClienteService.salvar(cliente);
	}
	
	@PutMapping("/{codCliente}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long codCliente, 
			@Valid @RequestBody Cliente cliente) {
		if (!clienteRepository.existsById(codCliente)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setCodigo(codCliente);
		cliente = cadastroClienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{codCliente}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long codCliente) {
		Optional<Cliente> optCliente = clienteRepository.findById(codCliente);
		
		if (optCliente.isPresent()) {
			return ResponseEntity.ok(optCliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codCliente}")
	public ResponseEntity<Void> remover(@PathVariable Long codCliente) {
		if (!clienteRepository.existsById(codCliente)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroClienteService.excluir(codCliente);
		
		return ResponseEntity.noContent().build();
	}
	
}