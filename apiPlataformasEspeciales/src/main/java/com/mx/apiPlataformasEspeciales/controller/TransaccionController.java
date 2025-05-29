package com.mx.apiPlataformasEspeciales.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mx.apiPlataformasEspeciales.dto.TransaccionRequest;
import com.mx.apiPlataformasEspeciales.dto.TransacionRespuesta;
import com.mx.apiPlataformasEspeciales.service.TransaccionService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
@Validated
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @CrossOrigin
    @PostMapping("/procesar")
    public ResponseEntity<TransacionRespuesta> procesar(@Valid @RequestBody TransaccionRequest request) {
    	TransacionRespuesta respuesta = transaccionService.procesarTransaccion(request);
        return ResponseEntity.ok(respuesta);
    }
}



