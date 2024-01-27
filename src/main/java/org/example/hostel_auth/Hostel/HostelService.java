package org.example.hostel_auth.Hostel;

import org.example.hostel_auth.Hostel.dtos.CreateHostelRequest;
import org.example.hostel_auth.User.UserRepository;
import org.example.hostel_auth.User.UserService;
import org.springframework.stereotype.Service;

@Service
public class HostelService {
    private final HostelRepository hostelRepository;
    private final UserRepository userRepository;

    public HostelService(HostelRepository hostelRepository, UserRepository userRepository) {
        this.hostelRepository = hostelRepository;
        this.userRepository = userRepository;
    }

    public Iterable<HostelEntity> getAllHostels() {
        return hostelRepository.findAll();
    }

   /* public HostelEntity getHostelByUser(String user) {
        return hostelRepository.findByUsername(user).orElseThrow(() -> new UserService.UserNotFoundException());
    }*/

    public HostelEntity CreateHostelDetails(CreateHostelRequest a) {
        var user = userRepository.findByUsername(a.getUsername()).orElseThrow(() -> new UserService.UserNotFoundException(a.getUsername()));

        return hostelRepository.save(HostelEntity.builder()
                .regNumber(a.getRegNumber())
                .hostelType(a.getHostelType())
                .blockName(a.getBlockName())
                .roomNumber(a.getRoomNumber())
                .username(user)
                .userId(user)
                .build()
        );
    }
}