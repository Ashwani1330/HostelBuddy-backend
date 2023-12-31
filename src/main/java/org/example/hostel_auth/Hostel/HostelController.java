package org.example.hostel_auth.Hostel;

import org.example.hostel_auth.User.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/hostel")
public class HostelController {

    private final HostelRepository hostelRepository;

    public HostelController(HostelRepository hostelRepository) {
        this.hostelRepository = hostelRepository;
    }

    @PostMapping("")
    String createHostel(@AuthenticationPrincipal UserEntity user) {
        return "Hostel details enter by user: " + user.getUsername();
    }


    @GetMapping("/{id}")
    public ResponseEntity<HostelEntity> getHostelById(@PathVariable("id") UUID id) {
        Optional<HostelEntity> _hostel = hostelRepository.findById(id);
        return _hostel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<HostelEntity> updateHostel(@PathVariable("id") UUID id, @RequestBody HostelEntity hostel) {
        Optional<HostelEntity> _hostel = hostelRepository.findById(id);
        if (_hostel.isPresent()) {
            HostelEntity _updatedHostel = hostelRepository.save(hostel);
            return ResponseEntity.ok().body(_updatedHostel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHostel(@PathVariable("id") UUID id) {
        Optional<HostelEntity> _hostel = hostelRepository.findById(id);
        if (_hostel.isPresent()) {
            hostelRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
