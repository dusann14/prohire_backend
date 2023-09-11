package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "F27OpP4y1vufS8TW1flouaPYg9eePWHeiGQ4ey7FFrcdAu91CpmQgV+cmMWeT/rG";

    //metoda koja izvlaci sve claims-ove iz tokena
    /*
     * treba nam signin key da radimo nesto sa tokenom u ovom slucaju da ga dekodiramo.
     * On se koristi za kreiranje treceg signature dela JWT tokena koji se koristi kako bi se verifikovao korisnik
     * Posle se vrsi parsiranje tokena i sa metodom getBody se dobijaju claims-ovi*/
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //metoda za kreiranje signin key-a
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //izvlacimo samo email/username iz tokena
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    //genericka metoda za izvlacenje samo jednog claim-a iz tokena
    //prvo se izvlacce svi claims-ovi pomocu fje koju smo vec implementirali
    //i onda izvlacimo samo jedan iz svih claims-ova
    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction){
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


}
