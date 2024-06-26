package org.example.hostel_auth.User;

import org.example.hostel_auth.Security.JWTService;
import org.example.hostel_auth.User.dtos.CreateUserRequest;
import org.example.hostel_auth.User.dtos.LoginUserRequest;
import org.example.hostel_auth.User.dtos.UserResponse;
import org.example.hostel_auth.common.ErrorsResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;


    public UserController(UserService userService, UserRepository userRepository, ModelMapper modelMapper, JWTService jwtService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    @PostMapping("")
    ResponseEntity<UserResponse> signupUser(@RequestBody CreateUserRequest request) {
        UserEntity savedUser = userService.createUser(request);
        URI savedUserURI = URI.create("/users/" + savedUser.getUser_id());
        var userResponse = modelMapper.map(savedUser, UserResponse.class);
        userResponse.setToken(
                jwtService.createJWT(userResponse.getId())
        );

        return ResponseEntity.created(savedUserURI)
                .body(userResponse);
    }

    @PostMapping("/login")
    ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserRequest request) {
        UserEntity savedUser = userService.loginUser(request.getUsername(), request.getPassword());
        var userResponse = modelMapper.map(savedUser, UserResponse.class);
        userResponse.setToken(
                jwtService.createJWT(userResponse.getId())
        );

        return ResponseEntity.ok(userResponse);
    }

    @ExceptionHandler({
            UserService.UserNotFoundException.class,
            UserService.InvalidCredentialsException.class
    })
    ResponseEntity<ErrorsResponse> handleUserExceptions(Exception ex) {
        String message;
        HttpStatus status;

        if (ex instanceof UserService.UserNotFoundException) {
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof UserService.InvalidCredentialsException) {
            message = ex.getMessage();
            status = HttpStatus.UNAUTHORIZED;
        } else {
            message = "Something went wrong";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ErrorsResponse response = ErrorsResponse.builder()
                .message(message)
                .build();

        return ResponseEntity.status(status).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add more endpoints for other functionality
}