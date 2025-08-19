package com.example.TaskManagement.User;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepo userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // Create user
    public UserDTO createUser(UserDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        dto.setRole("USER");
        if(userRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new RuntimeException("Username already exists");
        }
        User user = modelMapper.map(dto, User.class);
        User saved = userRepository.save(user);
        return modelMapper.map(saved, UserDTO.class);
    }

    // Get all users
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    // Get user by ID
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    // Update user
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (dto.getUsername() != null) user.setUsername(dto.getUsername());
        if (dto.getPassword() != null) user.setPassword(dto.getPassword());
        if (dto.getRole() != null) {
            user = modelMapper.map(dto, User.class); // ModelMapper handles String -> Role
            user.setId(id); // preserve the existing ID
        }


        User updated = userRepository.save(user);
        return modelMapper.map(updated, UserDTO.class);
    }

    // Delete user
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }


}
