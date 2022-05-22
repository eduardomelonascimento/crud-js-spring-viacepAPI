package com.nascyimento.padroesprojetospring.service;

import com.nascyimento.padroesprojetospring.models.Cliente;


public interface ClientService {

    Iterable<Cliente> buscaTodos();

    Cliente buscaPorId(Long id);

    void criaCliente(Cliente cliente);

    void atualizaCliente(Long id, Cliente cliente);

    void deletaCliente(Long id);

}
