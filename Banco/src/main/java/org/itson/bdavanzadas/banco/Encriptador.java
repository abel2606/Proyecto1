package org.itson.bdavanzadas.banco;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Encriptador {

    private static final String ALGORITMO = "AES";
    private static final String CLAVE_SECRETA = "RicardoAbel12345";

    /**
     * Permite encriptar la cadena de texto del parámetro.
     *
     * @param cadena La cadena de texto a encriptar
     * @return La cadena de texto encriptada
     */
    public static String encriptar(String cadena) {
        try {
            Key clave = new SecretKeySpec(CLAVE_SECRETA.getBytes(), ALGORITMO);
            Cipher cifrador = Cipher.getInstance(ALGORITMO);
            cifrador.init(Cipher.ENCRYPT_MODE, clave);
            byte[] valorEncriptado = cifrador.doFinal(cadena.getBytes());
            return Base64.getEncoder().encodeToString(valorEncriptado);
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
