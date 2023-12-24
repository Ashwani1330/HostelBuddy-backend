package org.example.hostel_auth.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    // TODO: Move the key to a separate .properties file (not in git)
    private static final String JWT_KEY = "afljaldfj232aifdal1239ewrw782oznz8cj02nu90bsbkk232jah";
    private Algorithm algorithm  = Algorithm.HMAC256(JWT_KEY);

    public String createJWT(Long userId) {
        return JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(new Date())
                // .withExpiresAt(new Date())  // Todo: setup and expiry parameter
                .sign(algorithm);
    }

    public long retrieveUserId(String jwtString) {
        var decodedJWT = JWT.decode(jwtString);
        var userId = Long.valueOf(decodedJWT.getSubject());
        return userId;
    }
}
