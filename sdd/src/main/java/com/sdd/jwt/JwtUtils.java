package com.sdd.jwt;

import java.util.Date;

import com.sdd.entities.LoginDetails;
import com.sdd.entities.User;
import com.sdd.exception.SDDException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${bezkoder.app.jwtSecret}")
	private String jwtSecret;

	@Value("${bezkoder.app.jwtExpirationMs}")
	private int jwtExpirationMs;


	public String generateJwtToken(LoginDetails loginDetails) {
		return Jwts.builder()
				.setSubject((loginDetails.getUserId()+": "+loginDetails.getRoleId()+ " : "+ loginDetails.getLevel()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		String parseToken  =  validateJwtToken(token);
		if(parseToken==null){
			throw new SDDException(HttpStatus.UNAUTHORIZED.value(),"TOKEN INVALID");
		}
		return parseToken;
	}

	public String validateJwtToken(String authToken) {
		try {
			return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken).getBody().getSubject();

		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
			throw new SDDException(HttpStatus.UNAUTHORIZED.value(),"token expired please login first");
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
			throw new SDDException(HttpStatus.UNAUTHORIZED.value(),"token expired please login first");
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
			throw new SDDException(HttpStatus.UNAUTHORIZED.value(),"token expired please login first");

		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
			throw new SDDException(HttpStatus.UNAUTHORIZED.value(),"token expired please login first");

		}

		return null;
	}
}
