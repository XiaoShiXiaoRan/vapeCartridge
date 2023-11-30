package com.fidnortech.xjx.security;



import com.fidnortech.xjx.common.UserDetailsInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;


public class JwtUtil {


    private static String header = "Authorization";

    private static String startWith ="Bearer";


    private static String secretKey ="xjx20010328";


    public static Long validateSecond = 36000L;;

    public static final String USER_KEY = "user";


    public static String getToken(HttpServletRequest request) {
        String token = "";
        String bearerToken = request.getHeader(header);
        String d = request.getParameter("token");
        if (StringUtils.hasText(d) && d.startsWith(startWith)) {
            token = d.substring(startWith.length());
        }
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(startWith)) {
            token = bearerToken.substring(startWith.length());
        }
        return token;
    }
    public static UsernamePasswordAuthenticationToken getAuthentication(String token) throws AuthenticationException {
        Claims claims = validate(token);
        if (claims == null) {
            return null;
        }

        HashMap map = (HashMap) claims.get(USER_KEY);
        Collection< ? extends GrantedAuthority> authorities =
                ((List<Map<String, String>>) map.get("authorities")).stream()
                        .map(a -> new SimpleGrantedAuthority(a.get("authority")))
                        .collect(Collectors.toList());
        UserDetailsInfo principal = new UserDetailsInfo(String.valueOf(map.get("id")),(String) map.get("username"), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    /**
     * 生成jwt  token
     * @param user
     * @return
     */
    public static String generate(UserDetailsInfo user) {
        String token = Jwts.builder()
                .claim(USER_KEY, user)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validateSecond * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
        return token;
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public static Claims validate(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
