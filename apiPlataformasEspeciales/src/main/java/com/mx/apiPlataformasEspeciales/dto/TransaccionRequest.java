package com.mx.apiPlataformasEspeciales.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TransaccionRequest {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Operación solo puede contener letras")
    private String operacion;

    @NotBlank
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Importe debe ser formato moneda (ejemplo: 100.00)")
    private String importe;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]{3,20}$", message = "Cliente debe contener solo letras (3-20 caracteres)")
    private String cliente;

    @NotBlank
    private String secreto;
}

