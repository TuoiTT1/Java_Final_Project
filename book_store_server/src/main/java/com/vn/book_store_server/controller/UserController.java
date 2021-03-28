package com.vn.book_store_server.controller;

import com.vn.book_store_server.config.JwtUtils;
import com.vn.book_store_server.dto.RoleDTO;
import com.vn.book_store_server.dto.UserDTO;
import com.vn.book_store_server.dto.request.SignupRequest;
import com.vn.book_store_server.dto.response.JwtResponse;
import com.vn.book_store_server.dto.response.MessageResponse;
import com.vn.book_store_server.service.RoleService;
import com.vn.book_store_server.service.UserService;
import com.vn.book_store_server.service.impl.UserDetailsImpl;
import com.vn.book_store_server.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(name = Constants.SIGN_UP_URL)
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody SignupRequest signUpRequest) {
        System.out.println("start create a user: " + signUpRequest);
        if (userService.existsByUsername(signUpRequest.getUserName()))
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Username is already in use"));
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Email is already in use"));
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(signUpRequest.getUserName());
        userDTO.setFirstName(signUpRequest.getFirstName());
        userDTO.setLastName(signUpRequest.getLastName());
        userDTO.setEmail(signUpRequest.getEmail());
        userDTO.setPassword(encoder.encode(signUpRequest.getPassword()));
        Set<RoleDTO> roles = new HashSet<>();
        RoleDTO roleDTO;
        for (String r : signUpRequest.getRoles()) {
            roleDTO = roleService.findByName(r);
            if (roleDTO != null) {
                roles.add(roleDTO);
            }
        }
        userDTO.setRoles(roles);
        try {
            userService.saveDto(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Validated @RequestParam String username, @Validated @RequestParam String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.genJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUsername(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getEmail(),
                roles));
    }
}
