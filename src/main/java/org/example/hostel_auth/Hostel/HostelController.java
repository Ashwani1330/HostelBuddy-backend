package org.example.hostel_auth.Hostel;

import org.example.hostel_auth.Hostel.dtos.CreateHostelRequest;
import org.example.hostel_auth.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/hostel")
public class HostelController {


    private final HostelRepository hostelRepository;
    private final HostelService hostelService;

    @Autowired
    public HostelController(HostelService hostelService, HostelRepository hostelRepository) {
        this.hostelService = hostelService;
        this.hostelRepository = hostelRepository;
    }


 /*   @PostMapping("")
    String createHostel(@AuthenticationPrincipal UserEntity user) {
        return "Hostel details enter by user: " + user.getUsername();
    }*/

    @PostMapping("")
    public ResponseEntity<HostelEntity> createHostel(@RequestBody CreateHostelRequest createHostelRequest) {
        try {
            HostelEntity createdHostel = hostelService.CreateHostelDetails(createHostelRequest);
            return new ResponseEntity<>(createdHostel, HttpStatus.CREATED);
        } catch (UserService.UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
