package com.example.myfirstspringapp.entities.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UsersAuthDBService  implements UserDetailsService {
    @Autowired
    UsersRepository users;

    @Autowired
    AuthoritiesRepository authorities;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Searching for user "+username);
        Users user = users.findByUsername(username).isPresent()?users.findByUsername(username).get():null;
        System.out.println("user "+user);
        if(user==null)
            throw new UsernameNotFoundException("User " +username +" not found !");
        else {
            UserDetails userDetails=new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    authorities.findByUsername(username)
                            .stream()
                            .map(role-> {
                                System.out.println("autorities "+ role.getAuthority());
                                return new SimpleGrantedAuthority(role.getAuthority());
                            })
                            .collect(Collectors.toSet()));

            return userDetails;

        }
    }
}


