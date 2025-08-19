package com.example.TaskManagement;

import com.example.TaskManagement.User.Role;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // String -> Role
        modelMapper.addConverter(context -> {
            if (context.getSource() == null) return null;
            try {
                return Role.valueOf(context.getSource().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid role: " + context.getSource());
            }
        }, String.class, Role.class);

        // Role -> String
        modelMapper.addConverter(context -> {
            return context.getSource() == null ? null : context.getSource().name();
        }, Role.class, String.class);

        return modelMapper;
    }

}
