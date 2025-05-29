package com.mx.apiPlataformasEspeciales.dto;

import lombok.Data;

@Data
public class TransacionRespuesta {

	private Long id;
    private String estatus;
    private String referencia;
    private String operacion;
}
