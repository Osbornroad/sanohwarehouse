package com.gmail.osbornroad.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


import java.util.Collection;

import static java.util.Objects.requireNonNull;

/**
 * GKislin
 * 06.03.2015.
 */
public class AuthorizedUser {

    public static User getAutorizedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof User) ? (User) principal : null;
    }

    public static String getAutorizedUserName() {
        User user = getAutorizedUser();
        return user != null ? user.getUsername() : "Unautorized";
    }

    public static boolean hasRequestedAuthirity (String authorityName) {
        Collection<GrantedAuthority> authorities = getAutorizedUser().getAuthorities();
        if (authorities == null)
            return false;
        for (GrantedAuthority grantedAuthority : authorities) {
            if (authorityName.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }
        return false;
    }

}
