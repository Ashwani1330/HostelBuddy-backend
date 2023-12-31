package org.example.hostel_auth.User;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    @NonNull
    private String email;

    @Column(nullable = false)
    @NonNull
    private String password;

    @Column(nullable = true)
    @Nullable
    private String image;
}