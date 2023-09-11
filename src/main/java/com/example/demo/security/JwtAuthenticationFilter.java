package com.example.demo.security;

import com.example.demo.exception.custom.TokenExpiredException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//anotacija Component znaci da se pravi bean za ovu klasu
@Component
@RequiredArgsConstructor

//on je deo filter chain-a
//svaki put kada korisnik posalje zahtev na nasu aplikaciju poziva se niz filtera da obradi taj zahtev to se zove filter chain
//jedan od tih filtera je i JwtAuthenticationFilter koji smo dodali jer je nasledio klasu OncePerRequestFilter
//pitanje je koji su ostali filteri koji su u filter chain-u
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    //apstraktna metoda iz klase OncePerRequestFilter koja se ovde implementira
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        //uzima se header iz zahteva
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        //prvo se radi provera da li postoji jwt token
        //ako je header prazan ili ne pocinje sa Bearer onda se zahtev prosledjuje sledecm filteru, prazan ili ne pocinje sa Bearer
        //znaci da nema tokena to jest token nije poslat sa zahtevom
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        //uzima se token iz header-a
        jwt = authHeader.substring(7);

        /*jwt se sastoji od tri dela:
        * prvi deo se zove header - header ima dva dela prvi je tip tokena na primer JWT(Json Web Token) i algoritam za hesiranje to jest za potpis
        * drugi deo se zove payload - tu se nalaze claims to jest podaci o korisniku
        * treci deo se zove signature - koristi se da bi se verifikovao korisnik
        * sto znaci da se u drugom delu nalazi i email korisnika jer se tamo nalaze njegovi podaci*/
        //izvlacenje korisnikovog email-a iz tokena
        userEmail = jwtService.extractUsername(jwt);

        //kad smo izvukli email idemo dalje na algoritam
        //prvo se proverava da li je email null i da korisnik nije authenticated to se vidi iz sec context holdera koji oko sebe ima context koji ima
        //authentication sto sve izvlacimo i poredimo sa null i ako nije null onda je korisnik vec authenticated
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){

            //u slucaju da korisnik nije auth moramo da proverimo da li korisnik postoji u bazi sto i radimo
            UserDetails user = userDetailsService.loadUserByUsername(userEmail);

            //sad se proverava da li je token validan
            //ako jeste onda radimo update security context-a to jest postavljamo ono sto smo malopre proverili da ne bude null
            if(jwtService.isTokenValid(jwt, user)){
                //ad bi se izvrsio update contexta moramo da napravimo objekat klase UsernamePasswordAuthenticationToken
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                //ovako se vrsi update context holder-a
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}
