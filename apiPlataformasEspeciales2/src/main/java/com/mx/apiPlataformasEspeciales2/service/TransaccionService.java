package com.mx.apiPlataformasEspeciales2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.apiPlataformasEspeciales2.dao.TransaccionRepository;
import com.mx.apiPlataformasEspeciales2.dao.TransaccionRequest;
import com.mx.apiPlataformasEspeciales2.dto.Transaccion;

import java.util.List;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository repository;

    public Transaccion guardarTransaccion(TransaccionRequest request) {
        Transaccion t = new Transaccion();
        t.setOperacion(request.getOperacion());
        t.setImporte(request.getImporte());
        t.setCliente(request.getCliente());
        t.setSecreto(request.getSecreto());
        t.setReferencia(String.valueOf((int)(Math.random() * 900000) + 100000));
        t.setEstatus("Aprobada");
        return repository.save(t);
    }

    public Transaccion actualizarEstatus(Long id, String referencia, String estatus) {
        Transaccion t = repository.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
        if (!t.getReferencia().equals(referencia)) throw new RuntimeException("Referencia no coincide");
        t.setEstatus(estatus);
        return repository.save(t);
    }

    public List<Transaccion> obtenerTodas() {
        return repository.findAll();
    }

    public Transaccion obtenerPorReferencia(String referencia) {
        return repository.findByReferencia(referencia).orElseThrow(() -> new RuntimeException("No encontrado"));
    }
}


