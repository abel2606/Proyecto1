/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.banco;

import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

/**
 *
 * @author Abe
 */
public class Encriptar {
private static final String algoritmo = "AES";
    private static final String claveSecreta= "RicardoAbel12345";
    
    public static String encriptar(String contrasena) {
        try {
            Key clave = new SecretKeySpec(claveSecreta.getBytes(), algoritmo);
            Cipher cifrador = Cipher.getInstance(algoritmo);
            cifrador.init(Cipher.ENCRYPT_MODE, clave);
            byte[] valorEncriptado = cifrador.doFinal(contrasena.getBytes());
            return Base64.getEncoder().encodeToString(valorEncriptado);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    
}
