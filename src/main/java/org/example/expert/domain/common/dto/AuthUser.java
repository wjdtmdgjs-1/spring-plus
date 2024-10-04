package org.example.expert.domain.common.dto;

import lombok.Getter;
import org.example.expert.domain.user.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class AuthUser {

    private final Long id;
    private final String email;
    private final String nickName;
    private final UserRole userRole;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthUser(Long id, String email, UserRole role, String nickName) {
        this.id = id;
        this.email = email;
        this.userRole = role;
        this.authorities = List.of(new SimpleGrantedAuthority(role.name()));
        this.nickName = nickName;
        authorities.forEach(System.out::println);
    }

}
