package org.example.hostel_auth.Hostel;

import jakarta.persistence.*;
import lombok.*;
import org.example.hostel_auth.User.UserEntity;

@Entity
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "hostel")
public class HostelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long Id;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, columnDefinition = "BIGINT")
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private UserEntity username;

    private String regNumber;
    private String hostelType;
    private String blockName;
    private int roomNumber;
}