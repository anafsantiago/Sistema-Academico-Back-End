package org.campusconnect.estudafacil.util;

public class StringUtil {

    public static String gerarSigla(String nome) {
        if (nome == null || nome.isEmpty()) {
            return "";
        }
        String[] palavras = nome.split(" ");
        StringBuilder iniciais = new StringBuilder();
        if (palavras.length > 1) {
            for (String palavra : palavras) {
                if (!palavra.isEmpty()) {
                    iniciais.append(palavra, 0, Math.min(palavra.length(), 3));
                }
            }
        } else {
            iniciais.append(nome, 0, Math.min(nome.length(), 4));
        }
        return iniciais.toString().toUpperCase().trim();
    }

}