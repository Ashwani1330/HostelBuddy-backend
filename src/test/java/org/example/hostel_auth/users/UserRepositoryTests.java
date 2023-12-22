package org.example.hostel_auth.users;

import org.example.hostel_auth.User.UserEntity;
import org.example.hostel_auth.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    void can_create_user() {
        var user = UserEntity.builder()
                .username("ashwani")
                .password("noice")
                .email("akm@gmail.com")
                .build();

        userRepository.save(user);
    }
}
