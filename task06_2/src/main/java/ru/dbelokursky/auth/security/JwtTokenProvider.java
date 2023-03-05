package ru.dbelokursky.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.dbelokursky.auth.entity.Role;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  private final UserDetailsService userDetailsService;
  private final BCryptPasswordEncoder passwordEncoder;

  @Value("${jwt.token.secret}")
  private String secret;

  @Value("${jwt.token.lifetime}")
  private long tokenLifeTime;

  @PostConstruct
  void init() {
    secret = Base64.getEncoder().encodeToString(secret.getBytes());
  }

  public String createToken(String username, List<Role> roles) {
    Claims claims = Jwts.claims().setSubject(username);

    Date now = new Date();
    Date expirationDate = new Date(now.getTime() + tokenLifeTime);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expirationDate)
        // creates a spec-compliant secure-random key:
        // SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256)
        // or HS384 or HS512
        // If you wanted to store the generated key as a String, you could presumably Base64 encode it:
        // String base64Key = Encoders.BASE64.encode(key.getEncoded());
        // https://www.baeldung.com/java-secret-key-to-string
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  public String getUsername(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(secret)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public Authentication getAuthentication(String token) {
    String username = getUsername(token);
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    String token = "";

    if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
      token = bearerToken.substring(7);
    }

    return token;
  }


  public boolean validate(String token) {
    Jws<Claims> claims = Jwts.parserBuilder()
        .setSigningKey(secret)
        .build()
        .parseClaimsJws(token);

    return claims.getBody().getExpiration().after(new Date());
  }

  public List<String> getRoleNames(List<Role> roles) {
    return roles.stream().map(Role::getName).toList();
  }
}
