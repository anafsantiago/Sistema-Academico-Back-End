package org.campusconnect.estudafacil.util;

public class StringUtil {

    public static String gerarSigla(String nome) {
        if (nome == null || nome.isEmpty()) {
            return "";
        }

        String[] palavras = nome.split("\\s+");
        StringBuilder iniciais = new StringBuilder();

        if (palavras.length > 1) {
            for (String palavra : palavras) {
                if (palavra.length() > 2) {
                    iniciais.append(palavra.charAt(0));
                }
            }
        } else {
            if (nome.length() <= 2) {
                return nome.toUpperCase();
            } else {
                return nome.substring(0, 3).toUpperCase();
            }
        }

        return iniciais.toString().toUpperCase().trim();
    }
}