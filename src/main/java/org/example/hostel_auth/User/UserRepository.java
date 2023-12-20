package org.example.hostel_auth.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Optional<UserEntity> findByUsername(String username);
    @NonNull Optional<UserEntity> findById(@NonNull Long Id);

    Optional<UserEntity> findByUsername(String username);
}
