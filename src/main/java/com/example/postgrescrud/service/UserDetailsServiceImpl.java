package com.example.postgrescrud.service;

import com.example.postgrescrud.model.User;
import com.example.postgrescrud.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = User.fromOptional(userRepository.findByUsername(username));
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return (UserDetails) (UserDetails) user;
    }
}