package com.globant.micoservice.clienteservice.service.Impl;

import com.globant.micoservice.clienteservice.dao.CuentaRepository;
import com.globant.micoservice.clienteservice.dao.MovimientoRepository;
import com.globant.micoservice.clienteservice.exceptions.ClienteNotFoundException;
import com.globant.micoservice.clienteservice.exceptions.CuentaNotFoundException;
import com.globant.micoservice.clienteservice.exceptions.InvalidTipoMovimientoException;
import com.globant.micoservice.clienteservice.exceptions.SaldoInsuficienteException;
import com.globant.micoservice.clienteservice.model.Cuenta;
import com.globant.micoservice.clienteservice.model.Movimiento;
import com.globant.micoservice.clienteservice.service.MovimientoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public List<Movimiento> findAll() {
        return movimientoRepository.findAll();
    }

    @Override
    public Optional<Movimiento> findById(Long id) {
        return movimientoRepository.findById(id);
    }

    @Override
    @Transactional
    public Movimiento create(Movimiento movimiento) {
        this.validateMovimiento(movimiento);
        this.updateBalance(movimiento);
        cuentaRepository.save(movimiento.getCuenta());
        return movimientoRepository.save(movimiento);
    }

    @Override
    public Optional<Movimiento> update(Movimiento movimiento, Long id) {
        this.validateMovimiento(movimiento);
        Optional<Movimiento> movimientoOptional =movimientoRepository.findById(id);
        if(movimientoOptional.isPresent()){
            Movimiento movimientoDb=movimientoOptional.get();
            movimientoDb.setFecha(movimiento.getFecha());
            movimientoDb.setCuenta(movimiento.getCuenta());
            movimientoDb.setSaldo(movimiento.getSaldo());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Movimiento> delete(Long id) {
        Optional<Movimiento> movimientoOptional =movimientoRepository.findById(id);
        if(movimientoOptional.isPresent()){
            movimientoRepository.save(movimientoOptional.get());
            return movimientoOptional;
        }
        return Optional.empty();
    }

    @Override
    public List<Movimiento> findByFechasAndCliente(Date fromDate, Date toDate, Long clienteId) {
        return movimientoRepository.findBetweenDateAndUser(fromDate,toDate,clienteId);
    }

    private void validateMovimiento(Movimiento movimiento){
        if(movimiento.getCuenta().getId()==null || !cuentaRepository.findById(movimiento.getCuenta().getId()).isPresent()){
            throw new CuentaNotFoundException("Cuenta id ".concat(movimiento.getCuenta().getNumero().toString()));
        }
    }

    private void updateBalance(Movimiento movimiento){
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getId()).get();
        if (movimiento.getTipo().equalsIgnoreCase("DEBITO")){
            if(movimiento.getSaldo().compareTo(cuenta.getSaldo())>0){
                throw new SaldoInsuficienteException("Cuenta Nº ".concat(cuenta.getNumero()));
            }
            cuenta.setSaldo(cuenta.getSaldo().subtract(movimiento.getSaldo()));
            movimiento.setCuenta(cuenta);
        }else if (movimiento.getTipo().equalsIgnoreCase("CREDITO")){
            cuenta.setSaldo(cuenta.getSaldo().add(movimiento.getSaldo()));
            movimiento.setCuenta(cuenta);
        }else{
            throw new InvalidTipoMovimientoException(movimiento.getTipo());
        }
    }
}
