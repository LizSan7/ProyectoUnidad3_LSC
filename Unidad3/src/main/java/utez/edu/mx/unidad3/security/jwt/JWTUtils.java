package utez.edu.mx.unidad3.security.jwt;

// Cuarto Paso: Generar las utilerias para jwt

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JWTUtils {
    @Value("${secret.key}")
    private String SECRET_KEY;

    //Esta función ayuda a extraer todas las propiedades del token
    //Es decir, el cuerpo del token

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
    //Esta funcion nos ayuda a extraer las propiedades del token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims CLAIMS = extractAllClaims(token);
        return claimsResolver.apply(CLAIMS);
    }
    //Esta funcion extrae el nombre de usuario del token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //Esta funcion extrae la fecha de expiración
    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
