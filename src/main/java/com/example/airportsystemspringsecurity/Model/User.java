package com.example.airportsystemspringsecurity.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;
    private String role;

    public User(Integer id, String username, String password, String role, Set<Ticket> tickets) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.tickets = tickets;
    }

    @OneToOne(cascade = CascadeType.DETACH, mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Passenger passenger;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Ticket> tickets;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Flight> flights;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}