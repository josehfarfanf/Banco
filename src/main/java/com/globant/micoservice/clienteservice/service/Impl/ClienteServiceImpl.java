package com.globant.micoservice.clienteservice.service.Impl;

import com.globant.micoservice.clienteservice.dao.ClienteRepository;
import com.globant.micoservice.clienteservice.dao.PersonaRepository;
import com.globant.micoservice.clienteservice.model.Cliente;
import com.globant.micoservice.clienteservice.model.Persona;
import com.globant.micoservice.clienteservice.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        clientes.forEach(c->System.out.println(c.getId()));
        return clienteRepository.findAll();

    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente create(Cliente cliente) {
        cliente.setPersona(validatePersonClient(cliente.getPersona()));
        cliente.setContrasena(this.encrypt(cliente.getContrasena()));
        return clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> update(Cliente cliente, Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isPresent()){
            Cliente clienteDb=clienteOptional.get();
            clienteDb.setPersona(validatePersonClient(cliente.getPersona()));
            clienteDb.setContrasena(this.encrypt(cliente.getContrasena()));
            clienteDb.setEstado(cliente.getEstado());

            clienteDb.getPersona().setDireccion(cliente.getPersona().getDireccion());
            clienteDb.getPersona().setGenero(cliente.getPersona().getGenero());
            clienteDb.getPersona().setEstado(cliente.getPersona().getEstado());
            clienteDb.getPersona().setIdentificacion(cliente.getPersona().getIdentificacion());
            clienteDb.getPersona().setNombre(cliente.getPersona().getNombre());
            clienteDb.getPersona().setTelefono(cliente.getPersona().getTelefono());
            return Optional.of(clienteRepository.save(clienteDb));
        }
        return Optional.empty();
    }


    @Override
    public Optional<Cliente> delete(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isPresent()){
            clienteRepository.delete(clienteOptional.get());
            return clienteOptional;
        }
        return Optional.empty();
    }

    private String encrypt(String password){
        System.out.println(BCrypt.hashpw(password,BCrypt.gensalt()));
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    private Persona validatePersonClient(Persona persona){
        if (persona.getId()==null){
            return personaRepository.save(persona);
        }else{
            Optional<Persona> personaDb = personaRepository.findById(persona.getId());
            if(personaDb.isPresent())
                return personaDb.get();
            else
                return personaRepository.save(persona);
        }
    }
}
