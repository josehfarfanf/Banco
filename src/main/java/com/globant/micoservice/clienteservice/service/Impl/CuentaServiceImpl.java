package com.globant.micoservice.clienteservice.service.Impl;

import com.globant.micoservice.clienteservice.exceptions.ClienteNotFoundException;
import com.globant.micoservice.clienteservice.dao.ClienteRepository;
import com.globant.micoservice.clienteservice.dao.CuentaRepository;
import com.globant.micoservice.clienteservice.model.Cuenta;
import com.globant.micoservice.clienteservice.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }

    @Override
    public Optional<Cuenta> findById(Long id) {
        return cuentaRepository.findById(id);
    }

    @Override
    public Cuenta create(Cuenta cuenta) {
        this.validateCuenta(cuenta);
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Optional<Cuenta> update(Cuenta cuenta, Long id) {
        this.validateCuenta(cuenta);
        Optional<Cuenta> cuentaOptional=cuentaRepository.findById(id);
        if(cuentaOptional.isPresent()){
            Cuenta cuentaDb = cuentaOptional.get();
            cuentaDb.setEstado(cuenta.getEstado());
            cuentaDb.setCliente(cuenta.getCliente());
            cuentaDb.setNumero(cuenta.getNumero());
            cuentaDb.setTipo(cuenta.getTipo());
            return Optional.of(cuentaRepository.save(cuentaDb));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Cuenta> delete(Long id) {
        Optional<Cuenta> cuentaOptional=cuentaRepository.findById(id);
        if (cuentaOptional.isPresent()){
            cuentaRepository.delete(cuentaOptional.get());
            return cuentaOptional;
        }
        return Optional.empty();
    }

    private void validateCuenta(Cuenta cuenta){
        if(cuenta.getCliente().getId()==null || !clienteRepository.findById(cuenta.getCliente().getId()).isPresent()){
            throw new ClienteNotFoundException("Cliente id ".concat(cuenta.getCliente().getId().toString()));
        }
    }
}
