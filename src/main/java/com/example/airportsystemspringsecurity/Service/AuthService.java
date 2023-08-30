package com.example.airportsystemspringsecurity.Service;

import com.example.airportsystemspringsecurity.Model.User;
import com.example.airportsystemspringsecurity.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    public List<User> getAllUser(){
        return authRepository.findAll();
    }

    public void register(User user){
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("PASSENGER");
        authRepository.save(user);
    }
}
