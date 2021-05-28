package com.dilsonjlrjr.javatrellodashboardmateus.model.entities;

import com.dilsonjlrjr.javatrellodashboardmateus.model.annotation.FieldName;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @FieldName("id")
    private Long id;

    @FieldName("name")
    private String name;

    @FieldName("email")
    private String email;

    @FieldName("username")
    private String username;

    private String password;
    private String refreshToken;
    private String hashSession;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(() -> "admin");
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
