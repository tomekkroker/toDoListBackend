package com.todolist.todolist.security;

import com.todolist.todolist.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserDetailsServiceImplementation implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, LockedException {
        var account = userRepository.findByUsernameIgnoreCase(username)
                .filter(u -> u.getPassword() != null && !u.getPassword().equals(""))
                .orElseThrow(() -> new UsernameNotFoundException("No user found with login " + username));
        if (account.getLocked()) {
            log.error("Attempt to log to a locked account with login " + username);
            throw new LockedException("Attempt to log to a locked account with login " + username);
        }

        return UserPrincipal.create(account);
    }

    @Transactional
    public UserDetails loadUserById(Integer id) throws UsernameNotFoundException, LockedException {
        var account = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("No user found with id " + id)
        );
        if (account.getLocked()) {
            log.error("Attempt to log to a locked account with login " + account.getUsername());
            throw new LockedException("Attempt to log to a locked account with login " + account.getUsername());
        }
        return UserPrincipal.create(account);
    }
}
