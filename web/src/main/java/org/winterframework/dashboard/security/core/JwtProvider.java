package org.winterframework.dashboard.security.core;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
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
    private final int expireInSeconds;
    private final int refreshTokenExpireInSeconds;
    private JwtParser jwtParser;

    public JwtProvider(@Value("${security.jwt.token.secret-key:'YouMustChangeThisSecretKey,OK?'}") String secretKey,
                       @Value("${security.jwt.token.expire-in:1800}") int expireInSeconds,
                       @Value("${security.jwt.refresh-token.expire-in:2592000}") int refreshTokenExpireInSeconds) {
        this.rawSecretKey = secretKey;
        this.expireInSeconds = expireInSeconds;
        this.refreshTokenExpireInSeconds = refreshTokenExpireInSeconds;
    }

    public int getRefreshTokenExpireInSeconds() {
        return refreshTokenExpireInSeconds;
    }

    @PostConstruct
    public void postConstruct() {
        log.info("JwtProvider initialized");
        this.key = Keys.hmacShaKeyFor(rawSecretKey.getBytes(StandardCharsets.UTF_8));
        this.jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    public String createToken(String tokenId, String userId, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("roles", roles);
        Date now = new Date();
        Date validity = new Date(now.getTime() + (expireInSeconds * 1000L));
        return Jwts.builder()
                   .setId(tokenId)
                   .setClaims(claims)
                   .setIssuedAt(now)
                   .setExpiration(validity)
                   .signWith(key)
                   .compact();
    }

    public String createRefreshToken(String tokenId, String userId) {
        Claims claims = Jwts.claims().setSubject(userId);
        Date now = new Date();
        Date validity = new Date(now.getTime() + (refreshTokenExpireInSeconds * 1000L));
        return Jwts.builder()
                   .setId(tokenId)
                   .setClaims(claims)
                   .setIssuedAt(now)
                   .setExpiration(validity)
                   .signWith(key)
                   .compact();
    }

    public String validateRefreshToken(String token) {
        try {
            Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
            return claimsJws.getBody().getSubject();
        } catch (ExpiredJwtException e) {
            log.warn("Refresh token expired", e);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            log.warn("Invalid refresh token", e);
        }
        return null;
    }


    public Authentication applyToken(String token) {
        try {
            Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return new JwtAuthenticationToken(claims);
        } catch (UnsupportedJwtException | SecurityException | MalformedJwtException | IllegalArgumentException e) {
            SecurityUtils.setAuthenticationState(SecurityUtils.JWT_TOKEN_INVALID);
        } catch (ExpiredJwtException e) {
            SecurityUtils.setAuthenticationState(SecurityUtils.JWT_TOKEN_EXPIRED);
        }
        return null;
    }

}
