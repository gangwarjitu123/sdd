package com.sdd.jwt;

import com.sdd.exception.SDDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class HeaderUtils {

    @Autowired
    private HttpServletRequest httpServletRequest;

    public   static final String USER_ID ="user_id";
    public   static final String ROLE_ID ="role_id";
    public   static final String LEVEL ="level";

    public String getTokeFromHeader(){

        String token = httpServletRequest.getHeader("token");
        if(token==null){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(),"invalid token found in the request");
        }
        return token;
    }


    /**
     * 	public String generateJwtToken(LoginDetails loginDetails) {
     * 		return Jwts.builder()
     * 				.setSubject((loginDetails.getUserId()+": "+loginDetails.getRoleId()+ " : "+ loginDetails.getLevel()))
     * 				.setIssuedAt(new Date())
     * 				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
     * 				.signWith(SignatureAlgorithm.HS512, jwtSecret)
     * 				.compact();
     *        }
     * @param token
     * @return
     */

    public Map<String,Integer> getUserCurrentDetails(String token){
        String[] arr = token.split(":");
        Map<String,Integer> loggedInUserDetails = new HashMap<>();
        loggedInUserDetails.put(USER_ID,Integer.parseInt(arr[0].trim()));
        loggedInUserDetails.put(ROLE_ID,Integer.parseInt(arr[1].trim()));
        loggedInUserDetails.put(LEVEL,Integer.parseInt(arr[2].trim()));
        return Collections.unmodifiableMap(loggedInUserDetails);
    }
}
