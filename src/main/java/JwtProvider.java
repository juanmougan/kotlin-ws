import io.jsonwebtoken.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Handles the creation and verification of JWTs
 * Created by juanma on 18/3/17.
 */
public class JwtProvider {

    public String createJwt() {
        LocalDateTime oneHourAfterNow = LocalDateTime.now().plusHours(1l);
        Date expDate = Date.from(oneHourAfterNow.atZone(ZoneId.systemDefault()).toInstant());
        String jwt =
                Jwts.builder().setIssuer("http://trustyapp.com/")
                        .setSubject("users/1300819380")
                        .setExpiration(expDate)
                        //.put("scope", "self api/buy")
                        .signWith(SignatureAlgorithm.HS256, getSignatureKey())
                        .compact();
        return jwt;
    }

    public String verifyJwt(String jwt) {
        String subject = "UNVERIFIED";
        try {
            Jws jwtClaims =
                    Jwts.parser().setSigningKey(getSignatureKey()).parseClaimsJws(jwt);

            //subject = jwtClaims.getBody().getSubject();
            subject = jwtClaims.getBody().toString();

            //OK, we can trust this JWT

        } catch (SignatureException e) {

            //don't trust the JWT!
        }
        return subject;
    }

    // TODO this sucks
    private byte[] getSignatureKey() {
        return new byte[]{10, 20, 30, 40, 50, 60};
    }

}
