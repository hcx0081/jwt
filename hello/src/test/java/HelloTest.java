import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * {@code @Description:}
 */
public class HelloTest {
    @Test
    public void encoded() {
        String jwt = JWT.create()
                        .withClaim("user", "zs")
                        .withIssuer("auth0")
                        .withExpiresAt(Date.from(LocalDateTime.now().plusMinutes(20).atZone(ZoneId.systemDefault()).toInstant()))
                        .sign(Algorithm.HMAC256("hello"));
        System.out.println(jwt);// eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImV4cCI6MTY3OTcxNjc1MSwidXNlciI6InpzIn0.Um6yqHpbYlz9hqT2GKf02WuT1OBAH9d13EfKlpSV7Q4
    }
    
    @Test
    public void decoded() {
        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImV4cCI6MTY3OTcyMDU4MCwidXNlciI6InpzIn0.ysNu4y83uDNKganPv40JFS6o-gfbwttFVdV1t-sBw6I";
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512("hello1"))
                                  .build();
        String user = verifier.verify(jwt).getClaim("user").asString();
        
        System.out.println(user);
    }
}