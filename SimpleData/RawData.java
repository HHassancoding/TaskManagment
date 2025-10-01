package com.example.TaskManagement.SimpleData;

import com.example.TaskManagement.User.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class RawData {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;
    @NonNull
    private LocalDateTime startTime;
    @NonNull
    private int duration;
    @NonNull
    private Boolean completed;
    @NonNull
    private Boolean abandonedEarly;







}
