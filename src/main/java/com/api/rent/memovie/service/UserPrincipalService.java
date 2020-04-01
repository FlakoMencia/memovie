package com.api.rent.memovie.service;

import com.api.rent.memovie.model.UserPrincipal;
import com.api.rent.memovie.repository.UserPrincipalRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MARIO MENCIA
 */
@Service
public class UserPrincipalService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserPrincipalService.class);

    @Autowired
    private UserPrincipalRepository userPrincipalRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPrincipal u = findByUsername(username);
        if (u == null) {
            logger.error("Error: Usuario " + username + " no existe en el ApiREST");
            throw new UsernameNotFoundException("Usuario " + username + " no encontrado!");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(u.getRole().getCode()));

        return new User(u.getUsername(), u.getPassword(), true, true, true, true, authorities);
    }

    public UserPrincipal findByUsername(String username) {
        return userPrincipalRepository.findByUsername(username);
    }

    @Transactional
    public UserPrincipal save(UserPrincipal user) {
        return userPrincipalRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userPrincipalRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userPrincipalRepository.existsByEmail(email);
    }

}
