package com.bsu.famcs.anytask.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    TEACHER, STUDENT;

    @Override
    public String getAuthority() {
        return name();
    }
}

