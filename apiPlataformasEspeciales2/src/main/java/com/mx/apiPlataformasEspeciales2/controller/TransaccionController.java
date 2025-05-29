package com.mx.apiPlataformasEspeciales2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mx.apiPlataformasEspeciales2.dao.TransaccionRequest;
import com.mx.apiPlataformasEspeciales2.dto.Transaccion;
import com.mx.apiPlataformasEspeciales2.service.TransaccionService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/segunda")
public class TransaccionController {

    @Autowired
    private TransaccionService service;

    @PostMapping
    public ResponseEntity<?> recibirTransaccion(@RequestBody TransaccionRequest request) {
        Transaccion t = service.guardarTransaccion(request);
        return ResponseEntity.ok(Map.of("id", t.getId(), "estatus", t.getEstatus(), "referencia", t.getReferencia(), "operacion", t.getOperacion()));
    }

    @PatchMapping
    public ResponseEntity<?> actualizarEstatus(@RequestBody Map<String, String> datos) {
        Long id = Long.valueOf(datos.get("id"));
        String referencia = datos.get("referencia");
        String estatus = datos.get("estatus");
        Transaccion t = service.actualizarEstatus(id, referencia, estatus);
        return ResponseEntity.ok(Map.of("id", t.getId(), "estatus", t.getEstatus(), "referencia", t.getReferencia()));
    }

    @GetMapping
    public List<Transaccion> obtenerTodas() {
        return service.obtenerTodas();
    }

    @GetMapping("/{referencia}")
    public Transaccion obtenerPorReferencia(@PathVariable String referencia) {
        return service.obtenerPorReferencia(referencia);
    }
}


