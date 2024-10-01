package org.campusconnect.estudafacil.auth;

import org.campusconnect.estudafacil.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenService {

    public String generate(Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        return Jwts
                .builder()
                .subject(usuario.getUsername())
                .expiration(new Date(System.currentTimeMillis() + 300_000))
                .signWith(getSigningKey())
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isValidToken(String token) {
        return !isExpired(token);
    }

    private boolean isExpired(String token) {
        return getClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private static SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode("bXktc2VjcmV0LWNvbXBsZXgtdXNlci1rZXktZm9yLXNlY3VyaXR5LWFuZC10b2tlbg==");
        return Keys.hmacShaKeyFor(keyBytes);
    }

}