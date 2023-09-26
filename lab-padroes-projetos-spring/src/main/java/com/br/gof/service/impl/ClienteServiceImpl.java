package com.br.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.gof.model.Cliente;
import com.br.gof.model.ClienteRepository;
import com.br.gof.model.Endereco;
import com.br.gof.model.EnderecoRepository;
import com.br.gof.service.ClienteService;
import com.br.gof.service.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	//Singleton: Injetar os componentes do Spring com @Autowired
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ViaCepService viaCepService;
	
	//Strategy: implementar os metodos definidos na interface
	//Facade: abstrair integrações com subsistemas, provendo uma interface simples

    @Override
    public Iterable<Cliente> buscarTodos() {
        // Buscar todos os Clientes
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
    	// Buscar Cliente por Id
    	Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
    	salvarClienteComCep(cliente);
    }


    @Override
    public void atualizar(Long id, Cliente cliente) {
    	// Buscar todos os Clientes
    	Optional<Cliente> clienteDB = clienteRepository.findById(id);
    	if (clienteDB.isPresent()) {
			//Verificar se o endereco do cliente ja existe pelo cep
    		//Caso não exista, integrar com o viaCep e persistir o retorno
    		//Alterar cliente, vinculando o endereco novo ou existente
    		salvarClienteComCep(cliente);
		}
    }


    @Override
    public void deletar(Long id) {
    	// Deletar todos os Clientes
    	clienteRepository.deleteById(id);

    }

	private void salvarClienteComCep(Cliente cliente) {
		// Verificar se o Endereco do Clientesjá existe pelo(CEP).
    	String cep = cliente.getEndereco().getCep();
    	Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
    		// Caso não exista, integrar com ViaCep e persistir o retorno.
    		Endereco novoEndereco = viaCepService.consultarCep(cep);
    		enderecoRepository.save(novoEndereco);
    		return novoEndereco;
    	});
    	cliente.setEndereco(endereco);
    	// Inserir Cliente, vinculando o Endereço (novo ou existente).
    	clienteRepository.save(cliente);
	}

}
