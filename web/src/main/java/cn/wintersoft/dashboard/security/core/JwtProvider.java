package cn.wintersoft.dashboard.security.core;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import cn.wintersoft.dashboard.security.utils.SecurityUtils;

import jakarta.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class JwtProvider {

    private final String rawSecretKey;
    private SecretKey key;
    private final int expireInSeconds;
    @Getter
    private final int refreshTokenExpireInSeconds;
    private JwtParser jwtParser;
    public final static String REDIS_KEY_PREFIX = "REVOKED_TOKEN:";
    public final static String TOKEN_TYPE_REFRESH = "REFRESH";
    public final static String TOKEN_TYPE_ACCESS = "ACCESS";

    private final StringRedisTemplate stringRedisTemplate;

    public JwtProvider(@Value("${security.jwt.token.secret-key:'YouMustChangeThisSecretKey,OK?'}") String secretKey,
                       @Value("${security.jwt.token.expire-in:1800}") int expireInSeconds,
                       @Value("${security.jwt.refresh-token.expire-in:2592000}") int refreshTokenExpireInSeconds,
                       StringRedisTemplate stringRedisTemplate) {
        this.rawSecretKey = secretKey;
        this.expireInSeconds = expireInSeconds;
        this.refreshTokenExpireInSeconds = refreshTokenExpireInSeconds;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @PostConstruct
    public void postConstruct() {
        log.info("JwtProvider initialized");
        this.key = Keys.hmacShaKeyFor(rawSecretKey.getBytes(StandardCharsets.UTF_8));
        this.jwtParser = Jwts.parser().verifyWith(key).build();
    }

    public String createToken(Long userId, List<String> roles) {
        Claims claims = Jwts.claims()
                            .id(UUID.randomUUID().toString())
                            .subject(userId.toString())
                            .add("roles", roles)
                            .build();
        Date now = new Date();
        Date validity = new Date(now.getTime() + (expireInSeconds * 1000L));
        return Jwts.builder()
                   .claims(claims)
                   .issuedAt(now)
                   .expiration(validity)
                   .signWith(key)
                   .compact();
    }

    public String createRefreshToken(String userId) {
        Claims claims = Jwts.claims()
                            .id(UUID.randomUUID().toString())
                            .subject(userId)
                            .build();
        Date now = new Date();
        Date validity = new Date(now.getTime() + (refreshTokenExpireInSeconds * 1000L));
        return Jwts.builder()
                   .claims(claims)
                   .issuedAt(now)
                   .expiration(validity)
                   .signWith(key)
                   .compact();
    }

    public String validateRefreshToken(String token) {
        try {
            Jws<Claims> claimsJws = jwtParser.parseSignedClaims(token);
            if (isTokenRevoked(TOKEN_TYPE_REFRESH, claimsJws.getPayload().getId())) {
                return null;
            }
            return claimsJws.getPayload().getSubject();
        } catch (ExpiredJwtException e) {
            log.warn("Refresh token expired", e);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            log.warn("Invalid refresh token", e);
        }
        return null;
    }

    public Claims getClaims(String token) {
        try {
            Jws<Claims> claimsJws = jwtParser.parseSignedClaims(token);
            return claimsJws.getPayload();
        } catch (Exception ignored) {
        }
        return null;
    }

    public Authentication applyToken(String token) {
        try {
            Jws<Claims> claimsJws = jwtParser.parseSignedClaims(token);
            Claims claims = claimsJws.getPayload();
            if (isTokenRevoked(TOKEN_TYPE_ACCESS, claims.getId())) {
                SecurityUtils.setAuthenticationState(SecurityUtils.JWT_TOKEN_EXPIRED);
                return null;
            }
            return new JwtAuthenticationToken(claims);
        } catch (UnsupportedJwtException | SecurityException | MalformedJwtException | IllegalArgumentException e) {
            SecurityUtils.setAuthenticationState(SecurityUtils.JWT_TOKEN_INVALID);
        } catch (ExpiredJwtException e) {
            SecurityUtils.setAuthenticationState(SecurityUtils.JWT_TOKEN_EXPIRED);
        }
        return null;
    }

    public void revokeToken(String type, Claims claims) {
        if (claims != null) {
            Date expiration = claims.getExpiration();
            Date now = new Date();
            long expirationInMs = expiration.getTime() - now.getTime();
            if (expirationInMs > 0) {
                String id = claims.getId();
                if (id != null) {
                    ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
                    opsForValue.set(REDIS_KEY_PREFIX + type + ":" + id, claims.getSubject(), expirationInMs,
                            TimeUnit.MILLISECONDS);
                }
            }
        }
    }

    public boolean isTokenRevoked(String type, String id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        String value = opsForValue.get(REDIS_KEY_PREFIX + type + ":" + id);
        return value != null;
    }
}
