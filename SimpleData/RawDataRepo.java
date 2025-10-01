package com.example.TaskManagement.SimpleData;

import com.example.TaskManagement.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RawDataRepo extends JpaRepository<RawData, Long> {
    Optional<RawData> findTopByUserIdAndCompletedFalseOrderByStartTimeDesc(User user);
}
