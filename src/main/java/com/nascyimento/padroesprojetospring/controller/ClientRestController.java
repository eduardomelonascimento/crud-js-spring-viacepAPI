package com.nascyimento.padroesprojetospring.controller;

import com.nascyimento.padroesprojetospring.models.Cliente;
import com.nascyimento.padroesprojetospring.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clientes")
public class ClientRestController {
 
    @Autowired
    private ClientService clientService;

    @GetMapping
    @CrossOrigin(origins = "http://127.0.0.1:5500/src/main/pages/index.html")
    public ResponseEntity<Iterable<Cliente>> buscaTodos() {
        return ResponseEntity.ok(clientService.buscaTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.buscaPorId(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> adicionaCliente(@RequestBody Cliente cliente) {
        clientService.criaCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizaCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        clientService.atualizaCliente(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaCliente(@PathVariable Long id) {
        clientService.deletaCliente(id);
        return ResponseEntity.ok().build();
    }

}