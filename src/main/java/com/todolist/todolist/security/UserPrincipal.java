package com.todolist.todolist.security;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.todolist.todolist.model.RoleEntity;
import com.todolist.todolist.model.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private final Integer id;
    private final String name;
    private final String username;
    @JsonIgnore
    private final String email;
    @JsonIgnore
    private final String password;
    @JsonIgnore
    private final LocalDate expirationDate;
    @JsonIgnore
    private final Boolean locked;
    private final Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(UserEntity user) {
        Set<GrantedAuthority> authorities = user.getRoles()
            .stream()
            .map(RoleEntity::getPermissions)
            .flatMap(Collection::stream)
            .map(permission -> new SimpleGrantedAuthority(permission.getName()))
            .collect(Collectors.toSet());
        return new UserPrincipal(
            user.getId(),
            user.getPerson() == null
                ? user.getUsername()
                :  String.format("%s %s", user.getPerson().getName(), user.getPerson().getSurname()),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            user.getAccountExpirationDate(),
            user.getLocked(),
            authorities
        );
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return expirationDate == null || !expirationDate.isBefore(LocalDate.now());
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
