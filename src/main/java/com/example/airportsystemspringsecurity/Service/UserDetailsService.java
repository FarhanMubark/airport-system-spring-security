package com.example.airportsystemspringsecurity.Service;

import com.example.airportsystemspringsecurity.Api.ApiException;
import com.example.airportsystemspringsecurity.Model.User;
import com.example.airportsystemspringsecurity.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user= authRepository.findUserByUsername(username);

        if (user == null){
            throw new ApiException("User Not found");
        }

        return user;
    }
}
