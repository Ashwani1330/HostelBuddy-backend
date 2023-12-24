package org.example.hostel_auth.Security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JWTServiceTests {
    JWTService jwtService = new JWTService();

    @Test
    void canCreateJwtFromUserId() {
        var jwt = jwtService.createJWT(100L);

        assertNotNull(jwt);
    }
}
