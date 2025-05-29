package com.mx.apiPlataformasEspeciales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mx.apiPlataformasEspeciales.dto.TransaccionRequest;
import com.mx.apiPlataformasEspeciales.dto.TransacionRespuesta;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransaccionService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String SECRET_KEY_BASE64 = "yjh2tm2VubIxzv+D//HCiSsOR3znOHsR01ONt8IwtjM=";
    private static final String INIT_VECTOR_BASE64 = "3wbXFv7wmkLbflJo7Funfg==";

    private static final byte[] SECRET_KEY_BYTES = Base64.getDecoder().decode(SECRET_KEY_BASE64);
    private static final byte[] INIT_VECTOR_BYTES = Base64.getDecoder().decode(INIT_VECTOR_BASE64);

    public TransacionRespuesta procesarTransaccion(TransaccionRequest request) {
        String secretoDescifrado = descifrar(request.getSecreto());

        Map<String, Object> dtoSegundaApi = new HashMap<>();
        dtoSegundaApi.put("operacion", request.getOperacion());
        dtoSegundaApi.put("importe", request.getImporte());
        dtoSegundaApi.put("cliente", request.getCliente());
        dtoSegundaApi.put("secreto", secretoDescifrado);

        TransacionRespuesta respuesta = restTemplate.postForObject(
            "http://localhost:8081/api/segunda",
            dtoSegundaApi,
            TransacionRespuesta.class);

        return respuesta;
    }

    private String descifrar(String valorCifrado) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR_BYTES);
            SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY_BYTES, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(valorCifrado));
            return new String(original);
        } catch (Exception ex) {
            throw new RuntimeException("Error al descifrar: " + ex.getMessage(), ex);
        }
    }
}

