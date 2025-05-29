package com.mx.apiPlataformasEspeciales.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AesUtil {

    // Clave y IV codificados en Base64
    private static final String SECRET_KEY_BASE64 = "yjh2tm2VubIxzv+D//HCiSsOR3znOHsR01ONt8IwtjM=";
    private static final String INIT_VECTOR_BASE64 = "3wbXFv7wmkLbflJo7Funfg==";

    // Decodificar Base64 a bytes
    private static final byte[] SECRET_KEY_BYTES = Base64.getDecoder().decode(SECRET_KEY_BASE64);
    private static final byte[] INIT_VECTOR_BYTES = Base64.getDecoder().decode(INIT_VECTOR_BASE64);

    public static String descifrar(String valorCifrado) {
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