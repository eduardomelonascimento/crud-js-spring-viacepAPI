package com.nascyimento.padroesprojetospring.service.implementation;

import java.util.Optional;

import com.nascyimento.padroesprojetospring.models.Cliente;
import com.nascyimento.padroesprojetospring.models.ClienteRepository;
import com.nascyimento.padroesprojetospring.models.Endereco;
import com.nascyimento.padroesprojetospring.models.EnderecoRepository;
import com.nascyimento.padroesprojetospring.service.ClientService;
import com.nascyimento.padroesprojetospring.service.ViaCepService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImplementation implements ClientService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscaTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscaPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void criaCliente(Cliente cliente) {
        salvaCliente(cliente);
    }

    @Override
    public void atualizaCliente(Long id, Cliente cliente) {
        Optional<Cliente> clientBD = clienteRepository.findById(id);
        if (clientBD.isPresent()) {
            salvaCliente(cliente);
        }
    }

    @Override
    public void deletaCliente(Long id) {
        clienteRepository.deleteById(id);

    }

    private void salvaCliente(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultaCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

}
