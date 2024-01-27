package org.example.hostel_auth.Hostel;

import lombok.NonNull;
import org.example.hostel_auth.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HostelRepository extends JpaRepository<HostelEntity, UUID> {

    @NonNull Optional<HostelEntity> findByUserId(UserEntity userId);
    Optional<HostelEntity> findByUsername(UserEntity username);
}