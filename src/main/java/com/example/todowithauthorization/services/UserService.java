package com.example.todowithauthorization.services;

import com.example.todowithauthorization.models.Task;
import com.example.todowithauthorization.models.User;
import com.example.todowithauthorization.repositories.UserRepository;
import com.example.todowithauthorization.security.MyUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(user.get());
    }
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }
    public Optional<User> findById(long id){
        return userRepository.findById(id);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public void delete(User user) {
        userRepository.delete(user);
    }

}
