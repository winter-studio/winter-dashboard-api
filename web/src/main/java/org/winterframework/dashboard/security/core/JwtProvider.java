package org.winterframework.dashboard.security.core;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.winterframework.dashboard.security.utils.SecurityUtils;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class JwtProvider {

    private final String rawSecretKey;
    private SecretKey key;
    private final long expireInSeconds;
    private JwtParser jwtParser;

    public JwtProvider(@Value("${security.jwt.token.secret-key:'YouMustChangeThisSecretKey,OK?'}") String secretKey,
                       @Value("${security.jwt.token.expire-in:7200}") long expireInSeconds) {
        this.rawSecretKey = secretKey;
        this.expireInSeconds = expireInSeconds;
    }

    @PostConstruct
    public void postConstruct() {
        log.info("JwtProvider initialized");
        this.key = Keys.hmacShaKeyFor(rawSecretKey.getBytes(StandardCharsets.UTF_8));
        this.jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    public String createToken(String userId, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("roles", roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + (expireInSeconds * 1000));

        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuedAt(now)
                   .setExpiration(validity)
                   .signWith(key)
                   .compact();
    }


    public Authentication applyToken(String token) {
        try {
            Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return new JwtAuthenticationToken(claims);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.trace("Invalid JWT signature trace: {}", e.getMessage());
            SecurityUtils.setAuthenticationState(SecurityUtils.JWT_TOKEN_INVALID);
        } catch (ExpiredJwtException e) {
            log.trace("Expired JWT token trace: {}", e.getMessage());
            SecurityUtils.setAuthenticationState(SecurityUtils.JWT_TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e.getMessage());
            SecurityUtils.setAuthenticationState(SecurityUtils.JWT_TOKEN_INVALID);
        } catch (IllegalArgumentException e) {
            log.trace("JWT token compact of handler are invalid trace: {}", e.getMessage());
            SecurityUtils.setAuthenticationState(SecurityUtils.JWT_TOKEN_INVALID);
        }
        return null;
    }

}
