package com.example.TaskManagement.User;

import com.example.TaskManagement.Tasks.Task;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,  orphanRemoval = true)
    @JsonManagedReference
    private List<Task> task;


    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private Role role;


}
