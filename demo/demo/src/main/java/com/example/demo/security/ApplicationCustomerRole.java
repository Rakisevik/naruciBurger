package com.example.demo.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.security.ApplicationCustomerPermission.*;

public enum ApplicationCustomerRole {
    CUSTOMER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(CUSTOMER_READ, CUSTOMER_WRITE, BURGER_READ, BURGER_WRITE));

    private final Set<ApplicationCustomerPermission> permissions;

    ApplicationCustomerRole(Set<ApplicationCustomerPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationCustomerPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
