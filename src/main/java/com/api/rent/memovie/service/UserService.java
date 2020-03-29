package com.api.rent.memovie.service;

import com.api.rent.memovie.model.User;
import com.api.rent.memovie.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
public class UserService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username);
        if (u == null) {
            logger.error("Error: Usuario " + username + " no existe en el ApiREST");
            throw new UsernameNotFoundException("Usuario " + username + " no encontrado!");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(u.getRole().getCode()));

//    for (Role role: roles) {
//        authorities.add(new SimpleGrantedAuthority(role.getName()));
//        role.getPrivileges().stream()
//         .map(p -> new SimpleGrantedAuthority(p.getName()))
//         .forEach(authorities::add);
//    }
        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), true, true, true, true, authorities);
    }

}
