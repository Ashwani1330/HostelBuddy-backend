package org.example.hostel_auth.users;

import org.example.hostel_auth.User.UserEntity;
import org.example.hostel_auth.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    void can_create_user() {
        var user = UserEntity.builder()
                .username("ashwani")
                .email("akm@gmail.com")
                .password("akm@123456")
                .build();

        userRepository.save(user);
    }
}
